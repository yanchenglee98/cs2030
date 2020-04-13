package cs2030.simulator;

/**
 * Customer class. 
 * Contains an ID string and a time of arrival int.
 * @author Lee Yan Cheng
 */

public class Customer {
    private final int iD;
    private final double time;
    private States state;
    private int server;
    private double timeOfArrival;

    /**
     * Constructor for Customer.
     * @param iD the customer's ID
     * @param time the customers time of arrival
     */
    public Customer(int iD, double time, States state) {
        this.iD = iD;
        this.time = time;
        this.state = state;
    }

    public Customer(int iD, double time, States state, int server) {
        this.iD = iD;
        this.time = time;
        this.state = state;
        this.server = server;
    }

    public Customer(int iD, double time, States state, int server, double timeOfArrival) {
        this.iD = iD;
        this.time = time;
        this.state = state;
        this.server = server;
        this.timeOfArrival = timeOfArrival;
    }

    public void setTimeOfArrival(double timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
    }

    public double getTimeOfArrival() {
        return timeOfArrival;
    }
    /**
     * <p> Returns customer's time of arrival as a double. </p>
     * @return customer's time of arrival
     */
    public double getTime() {
        return time;
    }

    /**
     * <p> Returns customer's ID as an int. </p>
     * @return customer's ID
     */
    public int getID() {
        return iD;
    }

    public int getStateID() {
        return this.state.getStateID();
    }

    public String getState() {
        return state.getState();
    }

    public int getServerID() {
        return this.server;
    }

    public Customer update(double time, States state, int server) {
        return new Customer(this.iD, time, state, server, this.timeOfArrival);
    }
    /**
     * <p> Returns a string representation of a customer. </p>
     * @return displays the customer's ID and time of arrival
     */
    @Override
    public String toString() {
        return String.format("%.3f %d", time, iD);
    }
}