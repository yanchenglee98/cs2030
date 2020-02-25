/**
 * Customer class. 
 * Contains an ID string and a time of arrival int.
 * @author Lee Yan Cheng
 */

public class Customer {
    private final int ID;
    private final double timeOfArrival;

    /**
     * Constructer for Customer
     * @param ID the customer's ID
     * @param timeOfArrival the customers time of ar
     */
    public Customer(int ID, double timeOfArrival) {
        this.ID = ID;
        this.timeOfArrival = timeOfArrival;
    }

    /**
     * <p> Returns customer's time of arrival </p>
     * @return the timeOfArrival
     */
    public double getTimeOfArrival() {
        return timeOfArrival;
    }

    /**
     * <p> Returns customer's ID </p>
     * @return cusomter's ID
     */
    public int getID() {
        return ID;
    }
    
    /**
     * <p> Returns a string representation of a customer </p>
     * @return String containg the customer's ID and time of arrival
     */
    @Override
    public String toString() {
        return String.format("%.3f %d", timeOfArrival, ID);        
    }
}