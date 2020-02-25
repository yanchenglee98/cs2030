/**
 * Shop class. 
 * Manages influx of customers and keeps an event log. 
 * 
 * @author Lee Yan Cheng
 */

import java.util.PriorityQueue;
import java.util.Iterator;

public class Shop {
    
    /**
     * Opens the shop for business, allowing customers to enter. 
     * Prints out events in chronological order.
     * @param queue An priority queue of customers in chronological order
     */
    public static void open(PriorityQueue<Customer> queue) {
        Iterator<Customer> it = queue.iterator(); // create iterator object to iterate through queue
        
        Server server = new Server(); //  instantiate an unused server

        States[] arr = States.values(); // create states array

        PriorityQueue<Event> eventLog = new PriorityQueue<>(new EventTimeComparator()); 
        // create an event log to store all events

        // add arrival to eventLog 
        for (Customer c: queue) {
            Event log = new Event(c, c.getTimeOfArrival(), arr[0]); // add arrive log
            eventLog.add(log);
        }

        double totalWaitingTime = 0.0;

        while (it.hasNext()) {
            Customer currentCustomer = queue.poll();
            //update server when currentCustomer timeofarrival > the server nextservicetime
            if (server.getServing() != null && currentCustomer.getTimeOfArrival() >= (server.getNextServiceTime())) { 
                
                if (server.getWaiting() == null) { 
                    //if server has finished serving customer, immediately serve currentCustomer
                    server = new Server(currentCustomer);
                    eventLog.add(new Event(currentCustomer, currentCustomer.getTimeOfArrival(), arr[2])); 
                    // add served log
                    eventLog.add(new Event(currentCustomer, currentCustomer.getTimeOfArrival() + 1.0, arr[4]));
                    // add done log

                } else {
                    if (currentCustomer.getTimeOfArrival() >= server.getNextServiceTime() + 1.0) { 
                        // if server has served both serving and waiting customers
                        // when currentCustomer arrives
                        server = new Server(currentCustomer);
                        Event served = new Event(currentCustomer, currentCustomer.getTimeOfArrival(), arr[2]); 
                        // serve immediately
                        eventLog.add(served); // add when it will be served log
                        eventLog.add(new Event(currentCustomer, server.getNextServiceTime(), arr[4])); 
                        // add when it will be done log
                    } else { 
                        // if server still serving waiting customer when current customer arrives
                        server = new Server(server.getWaiting(), currentCustomer, server.getNextServiceTime());
                        Event wait = new Event(currentCustomer, currentCustomer.getTimeOfArrival(), arr[1]); // wait
                        Event served = new Event(currentCustomer, server.getNextServiceTime(), arr[2]); // serve
                        eventLog.add(wait); // add waits log
                        eventLog.add(served); // add when it will be served log
                        eventLog.add(new Event(currentCustomer, server.getNextServiceTime() + 1.0, arr[4])); 
                        // add when it will be done log
                        
                        // update waiting time
                        double waitTime = served.getTime() - wait.getTime();
                        totalWaitingTime += waitTime;
                    }
                }
            } else {
                if (server.canServe(currentCustomer)) { // if server can serve
                    server = server.serve(currentCustomer);
                    eventLog.add(new Event(currentCustomer, currentCustomer.getTimeOfArrival(), arr[2])); // add served log
                    eventLog.add(new Event(currentCustomer, currentCustomer.getTimeOfArrival() + 1.0, arr[4])); // add done log
                    
                  
                } else if (server.isWaitingAvail()) { // if waiting spot is availble
                    server = new Server(server.getServing(), currentCustomer);
                    Event wait = new Event(currentCustomer, currentCustomer.getTimeOfArrival(), arr[1]); // wait
                    Event served = new Event(currentCustomer, server.getNextServiceTime(), arr[2]); // serve
                    eventLog.add(wait); // add waits log
                    eventLog.add(served); // add when it will be served log
                    eventLog.add(new Event(currentCustomer, server.getNextServiceTime() + 1.0, arr[4])); // add when it will be done log
                    
                    // update waiting time
                    double waitTime = served.getTime() - wait.getTime();
                    totalWaitingTime += waitTime;
                } else { // leave
                    eventLog.add(new Event(currentCustomer, currentCustomer.getTimeOfArrival(), arr[3])); // add leave log
                }
            }
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

        double averageWaitingTime = (totalWaitingTime / (double)served);

        String stats = String.format("[%.3f %d %d]", averageWaitingTime, served, leave);

        System.out.println(stats);
    }
}