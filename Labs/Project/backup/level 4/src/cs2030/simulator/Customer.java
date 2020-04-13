package cs2030.simulator;

/**
 * Customer class. 
 * Contains an ID string and a time of arrival int.
 * @author Lee Yan Cheng
 */

public abstract class Customer {
    protected final int iD;
    protected double timeOfArrival;
    public boolean isGreedy;

    /**
     * Constructor for Customer.
     * @param iD the customer's ID
     * @param timeOfArrival the customers time of arrival
     */

    public Customer(int iD, double timeOfArrival, boolean isGreedy) {
        this.iD = iD;
        this.timeOfArrival = timeOfArrival;
        this.isGreedy = isGreedy;
    }

    public Customer(int iD) {
        this.iD = iD;
        this.timeOfArrival = 0.0;
        this.isGreedy = false;
    }

    public double getTimeOfArrival() {
        return timeOfArrival;
    }

    public boolean isGreedy() {
        return isGreedy;
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

}