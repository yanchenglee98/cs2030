/**
 * Shop class. 
 * Manages influx of customers and keeps an event log. 
 * 
 * @author Lee Yan Cheng
 */

import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;

public class Shop {
    
    /**
     * Opens the shop for business, allowing customers to enter. 
     * Prints out events in chronological order.
     */
    public static void open() {
        Scanner sc = new Scanner(System.in);

        int numOfServers = sc.nextInt();

        ArrayList<Server> servers = new ArrayList<>(numOfServers); // list of servers

        // initialise list of servers
        for (int i = 1; i <= numOfServers; i++) {
            servers.add(new Server(i));
        }
        
        // initialise priorityqueue with arrival time comparator object
        PriorityQueue<Customer> queue = new PriorityQueue<>(new ArrivalTimeComparator());
        
        int idCounter = 0; // ID to track number of customers
        while (sc.hasNext()) { // while terminal has input, enter input into queue
            double timeOfArrival = sc.nextDouble();
            queue.add(new Customer(++idCounter, timeOfArrival));
        }

        Iterator<Customer> it = queue.iterator(); // create iterator object to iterate through queue

        // create an event log to store all events
        PriorityQueue<Event> eventLog = new PriorityQueue<>(new EventTimeComparator());

        // add arrival to eventLog 
        for (Customer c: queue) {
            Event log = new Event(c, c.getTimeOfArrival(), States.ARRIVES); // add arrive log
            eventLog.add(log);
        }

        while (it.hasNext()) {
            Customer currentCustomer = queue.poll();
            double currentTime = currentCustomer.getTimeOfArrival();

            boolean served = false;
            boolean wait = false;
            boolean leaves = true;

            Server server = new Server(-1); // fake server

            for (int i = 0; i < numOfServers; i++) { // update all servers with the current time
                Server s  = servers.get(i).updateServer(currentTime, eventLog);
                servers.set(i, s);
            }

            for (int i = 0; i < numOfServers; i++) {
                Server s = servers.get(i);

                if (s.isIdle()) {
                    server = s;
                    served = true;
                    leaves = false;
                    break;
                }
            }

            if (served) { // if a server available to server customer
                // add served log
                eventLog.add(new
                        Event(currentCustomer, currentTime, States.SERVED, server.getServerID()));
                // update server, assuming service time is 1.0
                servers.set(server.getServerID() - 1, new
                        Server(currentCustomer, 1.0, server.getServerID()));
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
                    // simply add wait log
                    eventLog.add(new
                            Event(currentCustomer, currentTime,
                            States.WAITS, server.getServerID()));
                    // update server
                    Server updated = new
                            Server(server.getServing(), currentCustomer,
                            server.getNextServiceTime(), server.getServerID());
                    servers.set(server.getServerID() - 1, updated);
                }
            }
            
            if (leaves) {
                // simply add leaves log
                eventLog.add(new Event(currentCustomer, currentTime, States.LEAVES));
            }
        }

        for (Server s: servers) { // check if servers have leftover customers
            s.updateServer(Double.MAX_VALUE, eventLog);
        }

        int served = 0;
        int leave = 0;

        while (!eventLog.isEmpty()) {
            Event event = eventLog.poll();

            if (event.getState() == "served") {
                served++;
            } else if (event.getState() == "leaves") {
                leave++;
            }

            System.out.println(event);
        }

        double averageWaitingTime = (Event.totalWaitingTime / (double) served);

        String stats = String.format("[%.3f %d %d]", averageWaitingTime, served, leave);

        System.out.println(stats);

        sc.close();
    }
}