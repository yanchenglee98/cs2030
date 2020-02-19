import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

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
        ArrayList<Customer> customerArray = new ArrayList<>(); // temporary arrayList to store Customer
        
        int IDcounter = 1; // ID to track number of customers and to assign unique ID to each new customer
        while (sc.hasNext()) { // while terminal has input, enter input into arraylist
            double timeOfArrival = sc.nextDouble();
            customerArray.add(new Customer(IDcounter, timeOfArrival));
            IDcounter++;
        }

        // initialist priorityqueue with arraylist size and arrival time comparator object
        PriorityQueue<Customer> queue = new PriorityQueue<>(customerArray.size(), new ArrivalTimeComparator());

        // iterate through placeholde CustomerArray and input into priorityQueue
        for (Customer c: customerArray) {
            queue.offer(c);
        }

        Iterator<Customer> it = queue.iterator(); // create iterator object to iterate through queue
        
        Server server = new Server(); //  instantiate an unused server

        States[] arr = States.values(); // create states array

        LinkedList<Event> eventLog = new LinkedList<>(); // create an event log to store all events

        System.out.println("# Adding arrivals"); // print our number of arrivals
        for (Customer c: customerArray) {
            Event log = new Event(c, c.getTimeOfArrival(), arr[0].getState());
            System.out.println(log);
            eventLog.add(log);
        }

        System.out.println();

        while (it.hasNext()) {
            Customer currentCustomer = queue.poll();

            if (server.canServe(currentCustomer)) {
                server = server.serve(currentCustomer);
                
                if (eventLog.peek().getState() == "done") { // corner case if server just finished serving previous customer
                    
                    System.out.println("# Get next event: " + eventLog.poll()); // remove first event from event log
                    Collections.sort(eventLog, new EventTimeComparator());

                    for (Event event: eventLog) { // print out log
                        System.out.println(event);
                    }

                    System.out.println();
                }
                // customer arrives and can be served
                System.out.println("# Get next event: " + eventLog.poll()); // remove first event from event log
                eventLog.add(new Event(currentCustomer, currentCustomer.getTimeOfArrival(), arr[1].getState())); // update eventLog on when serving is completed
                
                Collections.sort(eventLog, new EventTimeComparator());

                for (Event event: eventLog) { // print out log
                    System.out.println(event);
                }

                System.out.println();

                // customer is served
                // update event log after serving
                System.out.println("# Get next event: " + eventLog.poll()); // remove first event from event log
                eventLog.add(new Event(currentCustomer, currentCustomer.getTimeOfArrival()+1.0, arr[3].getState())); // update eventLog on when serving is completed
                
                Collections.sort(eventLog, new EventTimeComparator());

                for (Event event: eventLog) { // print out log
                    System.out.println(event);
                }

                System.out.println();


            } else {

                // customer arrives and cannot be served
                System.out.println("# Get next event: " + eventLog.poll()); // remove first event from event log
                eventLog.add(new Event(currentCustomer, currentCustomer.getTimeOfArrival(), arr[2].getState())); // update eventLog on when serving is completed
                
                Collections.sort(eventLog, new EventTimeComparator());

                for (Event event: eventLog) { // print out log
                    System.out.println(event);
                }

                System.out.println();

                // update after customer leaves
                System.out.println("# Get next event: " + eventLog.poll()); // remove first event from event log

                Collections.sort(eventLog, new EventTimeComparator());

                for (Event event: eventLog) { // print out log
                System.out.println(event);
                }

                System.out.println();

            
            }
        }

        System.out.println("# Get next event: " + eventLog.poll());
        System.out.println();

        System.out.println("Number of customers: " + customerArray.size());

    }
}