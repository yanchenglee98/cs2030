/**
 * Event time comparator 
 * Used to compare two events
 * @author Lee Yan Cheng
 */

import java.util.Comparator;

public class ArrivalTimeComparator implements Comparator<Customer> {
  
    /**
     * <p> Overriden compare method that compares customers time of arrival </p>
     * @param Customer c1,
     * @param Customer c2
     * @return returns 1 if c1 is later than c2, 0 if same and -1 if earlier
     */  
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