package cs2030.simulator;

/**
 * Customer class. 
 * Contains an ID string and a time of arrival int.
 * @author Lee Yan Cheng
 */

public class Customer {
    private final int iD;
    private double timeOfArrival;

    /**
     * Constructor for Customer.
     * @param iD the customer's ID
     * @param timeOfArrival the customers time of arrival
     */
    public Customer(int iD, double timeOfArrival) {
        this.iD = iD;
        this.timeOfArrival = timeOfArrival;
    }

    public Customer(int iD) {
        this.iD = iD;
        this.timeOfArrival = 0.0;
    }

    public double getTimeOfArrival() {
        return timeOfArrival;
    }

    /**
     * <p> Returns customer's ID as an int. </p>
     * @return customer's ID
     */
    public int getID() {
        return iD;
    }

    /**
     * <p> Returns a string representation of a customer. </p>
     * @return displays the customer's ID and time of arrival
     */
    @Override
    public String toString() {
        return String.format("%d", iD);
    }
}