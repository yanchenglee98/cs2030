package cs2030.simulator;

import javax.swing.plaf.nimbus.State;
import java.util.*;

/**
 * Shop class.
 * Manages influx of customers and keeps an event log.
 *
 * @author Lee Yan Cheng
 * */

public class Shop {

    /**
     * Opens the shop for business, allowing customers to enter. 
     * Prints out events in chronological order.
     */
    public static void open() {
        Scanner sc = new Scanner(System.in);

        //base seed for RandomGenerator
        int seed = sc.nextInt();
        // number of servers as input by user
        int numOfServers = sc.nextInt();
        // maximum queue length
        int maxQueueLength = sc.nextInt();
        // number of customers
        int numberOfCustomers = sc.nextInt();
        // arrival rate lambda
        double lambda = sc.nextDouble();
        // service rate mu
        double mu = sc.nextDouble();

        RandomGenerator rand = new RandomGenerator(seed, lambda, mu, 0.0);

        ArrayList<Server> servers = new ArrayList<>(numOfServers); // list of servers

        // initialise list of servers
        for (int i = 1; i <= numOfServers; i++) {
            servers.add(new Server(i, maxQueueLength));
        }

        // initialise priority queue with arrival time comparator object
        PriorityQueue<Customer> queue = new PriorityQueue<>(new ArrivalTimeComparator());

        int idCounter = 0; // ID to track number of customers
        double time = 0.0;
        for (int i = 0; i < numberOfCustomers; i++) { // while terminal has input, enter input into queue
            Customer newCustomer = new Customer(++idCounter, time, States.ARRIVES);
            newCustomer.setTimeOfArrival(time);
            queue.add(newCustomer);
            time += rand.genInterArrivalTime();
        }

        // create an event log to store all events
        PriorityQueue<Event> eventLog = new PriorityQueue<>(new EventTimeComparator());

        // iterate through customer queue
        while (!queue.isEmpty()) {
            Customer currentCustomer = queue.poll();
            double currentTime = currentCustomer.getTime();
            String currentState = currentCustomer.getState();

            if (currentState.equals("arrives")) { // customer has just arrived
                // add arrive log
                eventLog.add(new Event(currentCustomer, currentTime, States.ARRIVES));

                boolean served = false;
                boolean wait = false;
                boolean leaves = true;

                Server server = new Server(-1, maxQueueLength); // fake server

                // check for any idle server
                for (int i = 0; i < numOfServers; i++) {
                    Server s = servers.get(i);

                    if (s.isIdle()) {
                        server = s;
                        served = true;
                        leaves = false;
                        break;
                    }
                }

                if (served) { // if a server is available to serve customer
                    // update server,
                    // add served customer
                    queue.add(currentCustomer.update(currentTime, States.SERVED, server.getServerID()));
                    servers.set(server.getServerID() - 1, server.serve(currentCustomer));
                }

                if (!served) {  // if its not served, check for waiting space
                    for (Server s: servers) {
                        if (s.isWaitingAvail()) {
                            server = s;
                            wait = true;
                            leaves = false;
                            break;
                        }
                    }

                    if (wait) { // if there is waiting space for one of the servers
                        // add wait customer
                        queue.add(currentCustomer.update(currentTime, States.WAITS, server.getServerID()));
                        servers.set(server.getServerID() - 1, server.addToWait(currentCustomer));
                    }
                }

                if (leaves) {
                    // add leave customer
                    queue.add(currentCustomer.update(currentTime, States.LEAVES, server.getServerID()));
                }
            }

            if (currentState.equals("served")) { // served
                // update wait time
                Event.setTotalWaitingTime(currentTime - currentCustomer.getTimeOfArrival());
                // add served log
                double serviceTime = rand.genServiceTime();
                Server server = servers.get(currentCustomer.getServerID() - 1);
                eventLog.add(new Event(currentCustomer, currentTime, States.SERVED, server.getServerID()));
                // add done customer
                queue.add(currentCustomer.update(currentTime + serviceTime, States.DONE, server.getServerID()));
            }

            if (currentState.equals("done")) {
                // add done log
                int serverID = currentCustomer.getServerID();
                Server server = servers.get(serverID - 1);
                eventLog.add(new Event(currentCustomer, currentTime, States.DONE, serverID));
                // update the server
                Server updatedServer = server.serveFinish();
                Customer nextCustomer = updatedServer.getServing();
                // if server still have customers, serve customer
                if (!updatedServer.isIdle()) {
                    queue.add(nextCustomer.update(currentTime, States.SERVED, updatedServer.getServerID()));
                }
                servers.set(serverID - 1, updatedServer);
            }

            if (currentState.equals("waits")) {
                eventLog.add(new Event(currentCustomer, currentTime, States.WAITS, currentCustomer.getServerID()));
            }

            if (currentState.equals("leaves")) {
                eventLog.add(new Event(currentCustomer, currentTime, States.LEAVES));
            }
        }

        int served = 0;
        int leave = 0;

        while (!eventLog.isEmpty()) {
            Event event = eventLog.poll();

            if (event.getState().equals("served")) {
                served++;
            } else if (event.getState().equals("leaves")) {
                leave++;
            }

            System.out.println(event);
        }

        double averageWaitingTime = (served == 0) ? 0.0 : (Event.getTotalWaitingTime() / (double) served);

        String stats = String.format("[%.3f %d %d]", averageWaitingTime, served, leave);

        System.out.println(stats);

        sc.close();
    }
}