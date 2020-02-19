import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Iterator;

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

        System.out.println("Number of customers: " + customerArray.size());

    }
}