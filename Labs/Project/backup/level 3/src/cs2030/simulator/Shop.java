package cs2030.simulator;

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
        // number of self check out
        int numOfSelfCheckOut = sc.nextInt();
        // maximum queue length
        int maxQueueLength = sc.nextInt();
        Server.setMaxQueueLength(maxQueueLength);
        // number of customers
        int numberOfCustomers = sc.nextInt();
        // arrival rate lambda
        double lambda = sc.nextDouble();
        // service rate mu
        double mu = sc.nextDouble();
        // resting rate
        double rho = sc.nextDouble();
        // resting probability
        double restingProb = sc.nextDouble();

//        int seed = 1;
//        int numOfServers = 2;
//        int numOfSelfCheckOut = 1;
//        int maxQueueLength = 2;
//        Server.setMaxQueueLength(maxQueueLength);
//        int numberOfCustomers = 20;
//        double lambda = 1.0;
//        double mu = 1.0;
//        double rho = 0.1;
//        double restingProb = 0.5;

        RandomGenerator rand = new RandomGenerator(seed, lambda, mu, rho);

        ArrayList<Server> servers = new ArrayList<>(numOfServers); // list of servers

        int uniqueServerID = 1;
        // initialise list of servers
        for (int i = 1; i <= numOfServers; i++) {
            servers.add(new HumanServer(uniqueServerID++));
        }

        for (int i = 0; i < numOfSelfCheckOut; i++) {
            servers.add(new SelfCheckOut(uniqueServerID++));
        }

        int totalNumOfServers = numOfServers + numOfSelfCheckOut;

//        System.out.println(servers);

        // create an event log to store all events
        PriorityQueue<Event> eventLog = new PriorityQueue<>(new EventTimeComparator());

        int idCounter = 0; // ID to track number of customers
        double time = 0.0;
        for (int i = 0; i < numberOfCustomers; i++) { // while terminal has input, enter input into queue
            Customer newCustomer = new Customer(++idCounter, time);
            // add arrive log
            eventLog.add(new Event(newCustomer, time, States.ARRIVES));
            time += rand.genInterArrivalTime();
        }

        int served = 0;
        int leave = 0;

        // iterate through event log
        while (!eventLog.isEmpty()) {
            Event currentEvent = eventLog.poll();
            Customer currentCustomer = currentEvent.getCustomer();
            currentEvent.print();
            double currentTime = currentEvent.getTime();
            States currentState = currentEvent.getState();

            if (currentState == States.ARRIVES) { // customer has just arrived

//                System.out.println("currentCustomer: " + currentCustomer);

                boolean serve = false;
                boolean wait = false;
                boolean leaves = true;

                Server server = new HumanServer(-1); // fake server

                // check for any idle server
                for (int i = 0; i < totalNumOfServers; i++) {
                    Server s = servers.get(i);

//                    System.out.println(String.format("[%s, idle: %s, rest: %s]", s , s.isIdle(), s.isResting()));
                    if (s.isIdle() && !s.isResting()) {
                        server = s;
                        serve = true;
                        leaves = false;
                        break;
                    }
                }

                if (serve) { // if a server is available to serve customer
                    // update server,
                    // add served log
                    served++;
                    eventLog.add(new Event(currentCustomer, currentTime, States.SERVED, server));
                    servers.set(server.getServerID() - 1, server.serve(currentCustomer));
                }

                if (!serve) {  // if its not served, check for waiting space
                    for (Server s: servers) {
                        if (s.isWaitingAvail()) {
                            server = s;
                            wait = true;
                            leaves = false;
                            break;
                        }
                    }

                    if (wait) { // if there is waiting space for one of the servers
                        // add wait log
                        eventLog.add(new Event(currentCustomer, currentTime, States.WAITS, server));
                        servers.set(server.getServerID() - 1, server.addToWait(currentCustomer));
                    }
                }

                if (leaves) {
                    // add leave log
                    leave++;
                    eventLog.add(new Event(currentCustomer, currentTime, States.LEAVES));
                }
            }

            if (currentState == States.SERVED) { // served
                // update wait time
                Event.updateTotalWaitingTime(currentTime - currentCustomer.getTimeOfArrival());
                // add served log
                double serviceTime = rand.genServiceTime();
                int serverID = currentEvent.getServerID();
                Server server = servers.get(serverID - 1);
                // add done log
                eventLog.add(new Event(currentCustomer, currentTime + serviceTime, States.DONE, server));
            }

            if (currentState == States.DONE) { // done
                // add done log
                int serverID = currentEvent.getServerID();
                Server server = servers.get(serverID - 1);

                if (server.isHuman()) { // if server is human check for resting
                    double randomRest = rand.genRandomRest();
                    if (randomRest < restingProb) { // server rest
//                        System.out.println(server + " is resting");
                        eventLog.add(new Event(new Customer(-1), currentTime, States.SERVER_REST, server));
                        server.rest();
                        servers.set(serverID - 1, server); // update human server with rest state
                    } else { // continue to work
                        // update the server
                        Server updatedServer = server.serveNext();
                        Customer nextCustomer = updatedServer.getServing();
                        // if server still have customers, serve customer
                        if (!updatedServer.isIdle()) {
                            served++;
                            eventLog.add(new Event(nextCustomer, currentTime, States.SERVED, updatedServer));
                        }
                        servers.set(serverID - 1, updatedServer);
                    }
                } else { // if server is self check, continue working
                    // update the server
                    Server updatedServer = server.serveNext();
                    Customer nextCustomer = updatedServer.getServing();
                    // if server still have customers, serve customer
                    if (!updatedServer.isIdle()) {
                        served++;
                        eventLog.add(new Event(nextCustomer, currentTime, States.SERVED, updatedServer));
                    }
                    servers.set(serverID - 1, updatedServer);
                }
            }

            if (currentState == States.SERVER_BACK) { // server is back
                // serve next customer
                int serverID = currentEvent.getServerID();
                Server server = servers.get(serverID - 1);

                // update the server
                Server updatedServer = server.serveNext();
                Customer nextCustomer = updatedServer.getServing();

                if (!updatedServer.isIdle()) {
                    served++;
                    eventLog.add(new Event(nextCustomer, currentTime, States.SERVED, updatedServer));
                }
                servers.set(serverID - 1, updatedServer);
            }

            if (currentState == States.SERVER_REST) { // server is resting
                // generate rest period
                int serverID = currentEvent.getServerID();
                Server server = servers.get(serverID - 1);
//                System.out.println(server + " resting: " + server.isResting());
                double restPeriod = rand.genRestPeriod();
                double p = (currentTime + restPeriod);
//                System.out.println(server + " rest til: " + p);
                server.back();
                eventLog.add(new Event(new Customer(-1), currentTime + restPeriod, States.SERVER_BACK, server));
            }

            if (currentState == States.WAITS) { // waits

            }

            if (currentState == States.LEAVES) { // leaves

            }
        }

        double averageWaitingTime = (served == 0) ? 0.0 : (Event.getTotalWaitingTime() / (double) served);

        String stats = String.format("[%.3f %d %d]", averageWaitingTime, served, leave);

        System.out.println(stats);

        sc.close();
    }
}