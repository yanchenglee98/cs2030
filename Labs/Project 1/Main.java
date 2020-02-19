import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.LinkedList;

enum States {

    ARRIVES("arrives", 1), // 0
    WAITS("waits", 2), // 1
    SERVED("served", 3), // 2
    LEAVES("leaves", 4), // 3
    DONE("done", 5); // 4
   
    private final String state;
    private final int stateID;

    States(String state, int stateID) {
        this.state = state;
        this.stateID = stateID;
    }

    public String getState() {
        return state;
    }

    public int getStateID() {
        return stateID;
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

        // add arrival to eventLog 
        for (Customer c: queue) {
            Event log = new Event(c, c.getTimeOfArrival(), arr[0].getState(), arr[0].getStateID()); // add arrive log
            eventLog.add(log);
        }

        double totalWaitingTime = 0.0;

        while (it.hasNext()) {
            Customer currentCustomer = queue.poll();

            if (server.getServing()!=null && currentCustomer.getTimeOfArrival() >= (server.getNextServiceTime())) { // update server when currentCustomer time of arrival is more than the server next service time
                if (server.getWaiting() == null) { // if server has served finished its serving customer, immediately serve currentCustomer
                    server = new Server(currentCustomer);
                    eventLog.add(new Event(currentCustomer, currentCustomer.getTimeOfArrival(), arr[2].getState(), arr[2].getStateID())); // add served log
                    eventLog.add(new Event(currentCustomer, currentCustomer.getTimeOfArrival() + 1.0, arr[4].getState(), arr[4].getStateID())); // add done log

                } else {
                    if (currentCustomer.getTimeOfArrival() >= server.getNextServiceTime() + 1.0) { // if server has served both serving and waiting customers when currentCustomer arrives
                        server = new Server(currentCustomer);
                        Event served = new Event(currentCustomer, currentCustomer.getTimeOfArrival(), arr[2].getState(), arr[2].getStateID()); // serve immediately
                        eventLog.add(served); // add when it will be served log
                        eventLog.add(new Event(currentCustomer, server.getNextServiceTime(), arr[4].getState(), arr[4].getStateID())); // add when it will be done log
                    } else { // if server still serving waiting customer when current customer arrives
                        server = new Server(server.getWaiting(), currentCustomer, server.getNextServiceTime() );
                        Event wait = new Event(currentCustomer, currentCustomer.getTimeOfArrival(), arr[1].getState(), arr[1].getStateID()); // wait
                        Event served = new Event(currentCustomer, server.getNextServiceTime(), arr[2].getState(), arr[2].getStateID()); // serve
                        eventLog.add(wait); // add waits log
                        eventLog.add(served); // add when it will be served log
                        eventLog.add(new Event(currentCustomer, server.getNextServiceTime() + 1.0, arr[4].getState(), arr[4].getStateID())); // add when it will be done log
                        
                        // update waiting time
                        double waitTime = served.getTime() - wait.getTime();
                        totalWaitingTime += waitTime;
                    }
                }
            } else {
                if (server.canServe(currentCustomer)) { // if server can serve
                    server = server.serve(currentCustomer);
                    eventLog.add(new Event(currentCustomer, currentCustomer.getTimeOfArrival(), arr[2].getState(), arr[2].getStateID())); // add served log
                    eventLog.add(new Event(currentCustomer, currentCustomer.getTimeOfArrival() + 1.0, arr[4].getState(), arr[4].getStateID())); // add done log
                    
                  
                } else if (server.isWaitingAvail()) { // if waiting spot is availble
                    server = new Server(server.getServing(), currentCustomer);
                    Event wait = new Event(currentCustomer, currentCustomer.getTimeOfArrival(), arr[1].getState(), arr[1].getStateID()); // wait
                    Event served = new Event(currentCustomer, server.getNextServiceTime(), arr[2].getState(), arr[2].getStateID()); // serve
                    eventLog.add(wait); // add waits log
                    eventLog.add(served); // add when it will be served log
                    eventLog.add(new Event(currentCustomer, server.getNextServiceTime() + 1.0, arr[4].getState(), arr[4].getStateID())); // add when it will be done log
                    
                    // update waiting time
                    double waitTime = served.getTime() - wait.getTime();
                    totalWaitingTime += waitTime;
                } else { // leave
                    eventLog.add(new Event(currentCustomer, currentCustomer.getTimeOfArrival(), arr[3].getState(), arr[3].getStateID())); // add leave log
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

        double averageWaitingTime = (totalWaitingTime/(double)served);

        String stats = String.format("[%.3f %d %d]", averageWaitingTime, served, leave);

        System.out.println(stats);

        sc.close();

    }
}