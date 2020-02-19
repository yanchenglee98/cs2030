import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // initialise priorityqueue with arraylist size and arrival time comparator object
        PriorityQueue<Customer> queue = new PriorityQueue<>(new ArrivalTimeComparator());
        
        int IDcounter = 0; // ID to track number of customers and to assign unique ID to each new customer
        while (sc.hasNext()) { // while terminal has input, enter input
            double timeOfArrival = sc.nextDouble();
            queue.add(new Customer(++IDcounter, timeOfArrival));
        }

        Iterator<Customer> it = queue.iterator(); // create iterator object to iterate through queue
        
        Server server = new Server(); //  instantiate an unused server

        while (it.hasNext()) {
            Customer c = queue.poll();
            System.out.println(c);

            if (server.canServe(c)) {
                server = server.serve(c);
                System.out.println(server);
            } else {
                System.out.println("Customer leaves");
            }
        }

        System.out.println("Number of customers: " + IDcounter);

    }
}