import java.lang.Thread.State;

public class Event {
    private final Customer customer;
    private final double time;
    private final States state;

    public Event(Customer customer, double time, States states) {
        this.customer = customer;
        this.time = time;
        this.state = states;
    }

    public double getTime() {
        return time;
    }

    public int getID() {
        return customer.getID();
    }

    public String getState() {
        return state.getState();
    }

    public int getStateID() {
        return state.getStateID();
    }

    @Override 
    public String toString() {
        return String.format("%.3f %d %s", time, customer.getID(), state.getState());
    }
}