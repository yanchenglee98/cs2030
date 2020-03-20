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
    private final int server;
    private static double totalWaitingTime = 0.0;

    /**
     * Constructor for Event.
     * @param customer the customer
     * @param time time of event
     * @param states the state of event
     */
    public Event(Customer customer, double time, States states) {
        this.customer = customer;
        this.time = time;
        this.state = states;
        this.server = 0;
    }

    /**
     * Constructor for Event.
     * @param customer the customer related to the event
     * @param time time of event
     * @param states the state of event
     * @param server server related to the event
     */
    public Event(Customer customer, double time, States states, int server) {
        this.customer = customer;
        this.time = time;
        this.state = states;
        this.server = server;
    }

    /**
     * <p> Returns event time. </p>
     * @return time of event
     */
    public double getTime() {
        return time;
    }

    /**
     * <p> Returns ID of customer linked to the event. </p>
     * @return customer's ID that is linked to the event
     */
    public int getID() {
        return customer.getID();
    }

    /**
     * <p> Returns the state of the event. </p>
     * @return state of event
     */
    public String getState() {
        return state.getState();
    }

    /**
     * <p> Returns event's state ID, used for comparison. </p>
     * @return state ID linked to event
     */
    public int getStateID() {
        return state.getStateID();
    }

    /**
     * <p> Returns total waiting time accumulated. </p>
     * @return total waiting time
     */
    public static double getTotalWaitingTime() {
        return totalWaitingTime;
    }

    /**
     * <p> Sets total waiting time accumulated. </p>
     * @param waitingTime the new waiting time to be set
     */
    public static void setTotalWaitingTime(double waitingTime) {
        totalWaitingTime += waitingTime;
    }

    /**
     * <p> Returns a string representation of an event. </p>
     * @return displays the time, customer ID and its state
     */
    @Override 
    public String toString() {
       
        if (state.getState().equals("served")) {
            return String.format("%.3f %d %s by %d",
                    time, customer.getID(), state.getState(), server);
        } else if (state.getState().equals("waits")) {
            return String.format("%.3f %d %s to be served by %d",
                    time, customer.getID(), state.getState(), server);
        } else if (state.getState().equals("done")) {
            return String.format("%.3f %d %s serving by %d",
                    time, customer.getID(), state.getState(), server);
        }

        return String.format("%.3f %d %s", time, customer.getID(), state.getState());
    }
}