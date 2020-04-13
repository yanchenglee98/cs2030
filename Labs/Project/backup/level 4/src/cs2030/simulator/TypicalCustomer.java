package cs2030.simulator;

/**
 * Customer class.
 * Contains an ID string and a time of arrival int.
 * @author Lee Yan Cheng
 */

public class TypicalCustomer extends Customer {

    /**
     * Constructor for Customer.
     * @param iD the customer's ID
     * @param timeOfArrival the customers time of arrival
     */
    public TypicalCustomer(int iD, double timeOfArrival) {
        super(iD, timeOfArrival, false);
    }

    public TypicalCustomer(int iD) {
        super(iD);
    }

    /**
     * <p> Returns a string representation of a customer. </p>
     * @return displays the customer's ID and time of arrival
     */
    @Override
    public String toString() {
        return String.format("%d", super.iD);
    }
}