import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.LinkedList;



public class Main {

    /**
     * Main method which takes in user input and creates customers.
     * @param args Unused.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // initialise priorityqueue with arrival time comparator object
        PriorityQueue<Customer> queue = new PriorityQueue<>(new ArrivalTimeComparator());
        
        int idCounter = 0; // ID to track number of customers
        while (sc.hasNext()) { // while terminal has input, enter input into arraylist
            double timeOfArrival = sc.nextDouble();
            queue.add(new Customer(++idCounter, timeOfArrival));
        }

        Shop.open(queue);
        sc.close();

    }
}