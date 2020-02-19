public class Event {
    private final Customer customer;
    private final double time;
    private final String state;

    public Event(Customer customer, double time, String state) {
        this.customer = customer;
        this.time = time;
        this.state = state;
    }

    public double getTime() {
        return time;
    }

    public int getID() {
        return customer.getID();
    }

    public String getState() {
        return state;
    }

    @Override 
    public String toString() {
        return String.format("%.3f %d %s", time, customer.getID(), state);
    }
}