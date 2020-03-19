/**
 * Customer class. 
 * Contains an ID string and a time of arrival int.
 * @author Lee Yan Cheng
 */

public class Customer {
    private final int iD;
    private final double timeOfArrival;

    /**
     * Constructor for Customer.
     * @param iD the customer's ID
     * @param timeOfArrival the customers time of arrival
     */
    public Customer(int iD, double timeOfArrival) {
        this.iD = iD;
        this.timeOfArrival = timeOfArrival;
    }

    /**
     * <p> Returns customer's time of arrival as a double. </p>
     * @return customer's time of arrival
     */
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
        return String.format("%.3f %d", timeOfArrival, iD);        
    }
}