import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Arrays;

enum States {
    ARRIVES("arrives"),
    SERVED("served"),
    LEAVES("leaves"),
    DONE("done");

    private final String state;

    States(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // initialise priorityqueue with arrival time comparator object
        PriorityQueue<Customer> queue = new PriorityQueue<>(new ArrivalTimeComparator());
        
        int IDcounter = 0; // ID to track number of customers and to assign unique ID to each new customer
        while (sc.hasNext()) { // while terminal has input, enter input into arraylist
            double timeOfArrival = sc.nextDouble();
            queue.add(new Customer(++IDcounter, timeOfArrival));
        }

        Iterator<Customer> it = queue.iterator(); // create iterator object to iterate through queue
        
        Server server = new Server(); //  instantiate an unused server

        States[] arr = States.values(); // create states array

        PriorityQueue<Event> eventLog = new PriorityQueue<>(new EventTimeComparator()); // create an event log to store all events

        System.out.println("# Adding arrivals"); // print out number of arrivals
        for (Customer c: queue) {
            Event log = new Event(c, c.getTimeOfArrival(), arr[0].getState());
            System.out.println(log);
            eventLog.add(log);
        }

        System.out.println();

        while (it.hasNext()) {
            Customer currentCustomer = queue.poll();

            if (server.canServe(currentCustomer)) { // if server can serve current customer, serve
                server = server.serve(currentCustomer);
                
                if (eventLog.peek().getState() == "done") { // corner case if server just finished serving previous customer
                    
                    System.out.println("# Get next event: " + eventLog.poll()); // remove first event from event log
                    
                    // print array
                    Event[] events = eventLog.toArray(new Event[eventLog.size()]);

                    Arrays.sort(events, new EventTimeComparator());

                    for (Event e: events) {
                        System.out.println(e);
                    }

                    System.out.println();
                }
                // customer arrives and can be served
                System.out.println("# Get next event: " + eventLog.poll()); // remove first event from event log
                eventLog.add(new Event(currentCustomer, currentCustomer.getTimeOfArrival(), arr[1].getState())); // update eventLog on when serving is completed
                
                // print array
                Event[] events = eventLog.toArray(new Event[eventLog.size()]);

                Arrays.sort(events, new EventTimeComparator());

                for (Event e: events) {
                    System.out.println(e);
                }

                System.out.println();

                // customer is served
                // update event log after serving
                System.out.println("# Get next event: " + eventLog.poll()); // remove first event from event log
                eventLog.add(new Event(currentCustomer, currentCustomer.getTimeOfArrival()+1.0, arr[3].getState())); // update eventLog on when serving is completed
                
                // print array
                events = eventLog.toArray(new Event[eventLog.size()]);

                Arrays.sort(events, new EventTimeComparator());

                for (Event e: events) {
                    System.out.println(e);
                }

                System.out.println();


            } else {

                // customer arrives and cannot be served
                System.out.println("# Get next event: " + eventLog.poll()); // remove first event from event log
                eventLog.add(new Event(currentCustomer, currentCustomer.getTimeOfArrival(), arr[2].getState())); // update eventLog on when serving is completed
                
                // print array
                Event[] events = eventLog.toArray(new Event[eventLog.size()]);

                Arrays.sort(events, new EventTimeComparator());

                for (Event e: events) {
                    System.out.println(e);
                }

                System.out.println();

                // update after customer leaves
                System.out.println("# Get next event: " + eventLog.poll()); // remove first event from event log

                // print array
                events = eventLog.toArray(new Event[eventLog.size()]);

                Arrays.sort(events, new EventTimeComparator());

                for (Event e: events) {
                    System.out.println(e);
                }

                System.out.println();

            
            }
        }

        System.out.println("# Get next event: " + eventLog.poll());
        System.out.println();

        System.out.println("Number of customers: " + IDcounter);

        sc.close();

    }
}