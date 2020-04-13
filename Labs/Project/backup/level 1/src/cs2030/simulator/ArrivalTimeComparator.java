package cs2030.simulator;

/**
 * Event time comparator. 
 * Used to compare two events
 * @author Lee Yan Cheng
 */

import java.util.Comparator;

public class ArrivalTimeComparator implements Comparator<Customer> {
  
    /**
     * <p> Overridden compare method that compares customers time of arrival. </p>
     * @param c1 first customer
     * @param c2 second customer that will be compared to first customer
     * @return returns 1 if c1 is later than c2, 0 if same and -1 if earlier
     */  
    @Override
    public int compare(Customer c1, Customer c2) {
        if (c1.getTime() > c2.getTime()) {
            return 1;
        } else if (c1.getTime() < c2.getTime()) {
            return -1;
        } else { // if time is same compare ID
            if (c1.getID() > c2.getID()) {
                return 1;
            } else if (c1.getID() < c2.getID()) {
                return -1;
            } else { // if ID is same compare states
                if (c1.getStateID() > c2.getStateID()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
    }
}