public class Event {
    private final Customer customer;
    private final double time;
    private final String state;
    private final int stateID; // used in comparator for comparing states

    public Event(Customer customer, double time, String state, int stateID) {
        this.customer = customer;
        this.time = time;
        this.state = state;
        this.stateID = stateID;
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

    public int getStateID() {
        return stateID;
    }

    @Override 
    public String toString() {
        return String.format("%.3f %d %s", time, customer.getID(), state);
    }
}