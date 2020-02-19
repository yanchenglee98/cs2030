import java.util.Comparator;

public class ArrivalTimeComparator implements Comparator<Customer> {
    @Override
    public int compare(Customer c1, Customer c2) {
        if (c1.getTimeOfArrival() > c2.getTimeOfArrival()) {
            return 1;
        } else if (c1.getTimeOfArrival() < c2.getTimeOfArrival()) {
            return -1;
        } else {
            return 0;
        }
    }
}