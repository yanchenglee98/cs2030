public class Customer {
    private final int ID;
    private final double timeOfArrival;

    public Customer(int ID, double timeOfArrival) {
        this.ID = ID;
        this.timeOfArrival = timeOfArrival;
    }

    /**
     * @return the timeOfArrival
     */
    public double getTimeOfArrival() {
        return timeOfArrival;
    }
    
    @Override
    public String toString() {
        return String.format("%d arrives at %.3f", ID, timeOfArrival);        
    }
}