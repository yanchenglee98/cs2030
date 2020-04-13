package cs2030.simulator;

/**
 * Customer class. 
 * Contains an ID string and a time of arrival int.
 * @author Lee Yan Cheng
 */

public abstract class Customer {
    protected final int iD;
    protected double timeOfArrival;
    protected boolean isGreedy;

    /**
     * Constructor for Customer.
     * @param iD the customer's ID
     * @param timeOfArrival the customers time of arrival
     */
    protected Customer(int iD, double timeOfArrival, boolean isGreedy) {
        this.iD = iD;
        this.timeOfArrival = timeOfArrival;
        this.isGreedy = isGreedy;
    }

    protected Customer(int iD) {
        this.iD = iD;
        this.timeOfArrival = 0.0;
        this.isGreedy = false;
    }

    /**
     * <p> return the customer's time of arrival. </p>
     * @return returns customer's time of arrival
     */
    public double getTimeOfArrival() {
        return timeOfArrival;
    }

    /**
     * <p> checks if the current customer is greedy or typical. </p>
     * @return true if the customer is greedy and false if is typical
     */
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
}