package cs2030.simulator;

/**
 * Customer class.
 * Contains an ID string and a time of arrival int.
 * @author Lee Yan Cheng
 */

public class GreedyCustomer extends Customer {

    /**
     * Constructor for Customer.
     * @param iD the customer's ID
     * @param timeOfArrival the customers time of arrival
     */
    public GreedyCustomer(int iD, double timeOfArrival) {
        super(iD, timeOfArrival, true);
    }

    /**
     * <p> Returns a string representation of a greedy customer. </p>
     * @return displays the customer's ID, behaviour and time of arrival
     */
    @Override
    public String toString() {
        return String.format("%d(greedy)", super.iD);
    }
}