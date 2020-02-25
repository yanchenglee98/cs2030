/**
 * Customer class. 
 * Contains an ID string and a time of arrival int.
 * @author Lee Yan Cheng
 */

public class Customer {
    private final int iD;
    private final double timeOfArrival;

    /**
     * Constructer for Customer.
     * @param iD the customer's ID
     * @param timeOfArrival the customers time of ar
     */
    public Customer(int iD, double timeOfArrival) {
        this.iD = iD;
        this.timeOfArrival = timeOfArrival;
    }

    /**
     * <p> Returns customer's time of arrival. </p>
     * @return double the timeOfArrival
     */
    public double getTimeOfArrival() {
        return timeOfArrival;
    }

    /**
     * <p> Returns customer's ID. </p>
     * @return int cusomter's ID
     */
    public int getID() {
        return iD;
    }
    
    /**
     * <p> Returns a string representation of a customer. </p>
     * @return String contains the customer's ID and time of arrival
     */
    @Override
    public String toString() {
        return String.format("%.3f %d", timeOfArrival, iD);        
    }
}