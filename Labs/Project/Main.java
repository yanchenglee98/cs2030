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
    DONE("done"),
    WAITS("waits");

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

        // initialise priorityqueue with arraylist size and arrival time comparator object
        PriorityQueue<Customer> queue = new PriorityQueue<>(customerArray.size(), new ArrivalTimeComparator());

        // iterate through placeholde CustomerArray and input into priorityQueue
        for (Customer c: customerArray) {
            queue.offer(c);
        }

        Iterator<Customer> it = queue.iterator(); // create iterator object to iterate through queue
        
        Server server = new Server(); //  instantiate an unused server

        States[] arr = States.values(); // create states array

        PriorityQueue<Event> eventLog = new PriorityQueue<>(customerArray.size(), new EventTimeComparator()); // create an event log to store all events

        // add arrival to eventLog 
        for (Customer c: customerArray) {
            Event log = new Event(c, c.getTimeOfArrival(), arr[0].getState());
            // System.out.println(log);
            eventLog.add(log);
        }

        double totalWaitingTime = 0.0;

        while (it.hasNext()) {
            Customer currentCustomer = queue.poll();

            if (server.getServing()!=null && currentCustomer.getTimeOfArrival() >= (server.getNextServiceTime())) { // update server when currentCustomer time of arrival is more than the next service time
                if (server.getWaiting() == null) { // 
                    server = new Server(currentCustomer);
                    eventLog.add(new Event(currentCustomer, currentCustomer.getTimeOfArrival(), arr[1].getState())); // add served log
                    eventLog.add(new Event(currentCustomer, currentCustomer.getTimeOfArrival() + 1.0, arr[3].getState())); // add done log

                } else {
                    if (currentCustomer.getTimeOfArrival() >= server.getNextServiceTime() + 1.0) { // if server has served both serving and waiting customers when currentCustomer arrives
                        server = new Server(currentCustomer);
                        Event served = new Event(currentCustomer, currentCustomer.getTimeOfArrival(), arr[1].getState()); // serve immediately
                        eventLog.add(served); // add when it will be served log
                        eventLog.add(new Event(currentCustomer, server.getNextServiceTime(), arr[3].getState())); // add when it will be done log
                    } else { // if server still serving waiting customer when current customer arrives
                        server = new Server(server.getWaiting(), currentCustomer, server.getNextServiceTime() );
                        Event wait = new Event(currentCustomer, currentCustomer.getTimeOfArrival(), arr[4].getState());
                        Event served = new Event(currentCustomer, server.getNextServiceTime(), arr[1].getState());
                        eventLog.add(wait); // add waits log
                        eventLog.add(served); // add when it will be served log
                        eventLog.add(new Event(currentCustomer, server.getNextServiceTime() + 1.0, arr[3].getState())); // add when it will be done log

                        double waitTime = served.getTime() - wait.getTime();
                        totalWaitingTime += waitTime;
                    }
                }
            } else {
                if (server.canServe(currentCustomer)) { // if can serve
                    server = server.serve(currentCustomer);
                    eventLog.add(new Event(currentCustomer, currentCustomer.getTimeOfArrival(), arr[1].getState())); // add served log
                    eventLog.add(new Event(currentCustomer, currentCustomer.getTimeOfArrival() + 1.0, arr[3].getState())); // add done log
                    
                  
                } else if (server.isWaitingAvail()) { // if can go to waiting list
                    server = new Server(server.getServing(), currentCustomer);
                    Event wait = new Event(currentCustomer, currentCustomer.getTimeOfArrival(), arr[4].getState());
                    Event served = new Event(currentCustomer, server.getNextServiceTime(), arr[1].getState());
                    eventLog.add(wait); // add waits log
                    eventLog.add(served); // add when it will be served log
                    eventLog.add(new Event(currentCustomer, server.getNextServiceTime() + 1.0, arr[3].getState())); // add when it will be done log
                    
    
                    double waitTime = served.getTime() - wait.getTime();
                    totalWaitingTime += waitTime;
                } else { // leave
                    eventLog.add(new Event(currentCustomer, currentCustomer.getTimeOfArrival(), arr[2].getState())); // add leave log
                }
            }
        }

        // Collections.sort(eventLog, new EventTimeComparator());

        int served = 0;
        int leave = 0;

        for (Event event: eventLog) {
            
            if (event.getState() == "served") {
                served++;
            } else if (event.getState() == "leaves") {
                leave++;
            }

            System.out.println(event);
        }

        double averageWaitingTime = (totalWaitingTime/(double)served);

        String stats = String.format("[%.3f %d %d]", averageWaitingTime, served, leave);

        System.out.println(stats);

    }
}