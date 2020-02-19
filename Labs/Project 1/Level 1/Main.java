import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.PriorityQueue;

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
        
        // print queue
        while (it.hasNext()) {
            System.out.println(queue.poll());
        }

        System.out.println("Number of customers: " + (IDcounter));

        sc.close(); // close scanner
    }
}