import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Iterator;

enum States {
    ARRIVES("arrives"),
    SERVED("served"),
    LEAVES("leaves");

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

        // initialise priorityqueue with arraylist size and arrival time comparator object
        PriorityQueue<Customer> queue = new PriorityQueue<>(new ArrivalTimeComparator());
        
        int IDcounter = 0; // ID to track number of customers and to assign unique ID to each new customer
        while (sc.hasNext()) { // while terminal has input, enter input into arraylist
            double timeOfArrival = sc.nextDouble();
            queue.add(new Customer(++IDcounter, timeOfArrival));
        }

        Iterator<Customer> it = queue.iterator(); // create iterator object to iterate through queue
        
        Server server = new Server(); //  instantiate an unused server

        States[] arr = States.values(); // create states array

        while (it.hasNext()) {
            Customer c = queue.poll();
            System.out.println(c + " " + arr[0].getState());
            if (server.canServe(c)) {
                server = server.serve(c);
                System.out.println(c + " " + arr[1].getState());
            } else {
                System.out.println(c + " " + arr[2].getState());
            }

        }

        System.out.println("Number of customers: " + IDcounter);

        sc.close();
    }
}