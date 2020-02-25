/**
 * Event class.
 * Used to keep track of events. 
 * Contains a customer, time and state.
 * @author Lee Yan Cheng
 */

public class Event {
    private final Customer customer;
    private final double time;
    private final States state;

    /**
     * Constructer for Event.
     * @param customer the customer
     * @param time time of event
     * @param states the state of event
     */
    public Event(Customer customer, double time, States states) {
        this.customer = customer;
        this.time = time;
        this.state = states;
    }

    /**
     * <p> Returns event time. </p>
     * @return double event time
     */
    public double getTime() {
        return time;
    }

    /**
     * <p> Returns ID of customer linked to the event. </p>
     * @return int cusomter's ID
     */
    public int getID() {
        return customer.getID();
    }

    /**
     * <p> Returns the state of the event. </p>
     * @return String event state
     */
    public String getState() {
        return state.getState();
    }

    /**
     * <p> Returns event's state ID, used for comparison. </p>
     * @return int state ID
     */
    public int getStateID() {
        return state.getStateID();
    }

    /**
     * <p> Returns a string representaion of an event. </p>
     * @return String contains the time, customer ID and its state
     */
    @Override 
    public String toString() {
        return String.format("%.3f %d %s", time, customer.getID(), state.getState());
    }
}