public class Cruise {
    private final String ID;
    private final int timeOfArrival;
    private final int numberOfLoader;
    private final int serviceTime;

    Cruise(String ID, int timeOfArrival, int numberOfLoader, int serviceTime) {
        this.ID = ID;
        this.timeOfArrival = timeOfArrival;
        this.numberOfLoader = numberOfLoader;
        this.serviceTime = serviceTime;
    }

    public int getArrivalTime() {
        return (timeOfArrival/100)*60 + timeOfArrival-((timeOfArrival/100)*100);
    }
    
    public int getNumOfLoadersRequired() {
        return this.numberOfLoader;
    }

    public int getServiceCompletionTime() {
        return getArrivalTime() + serviceTime;
    }

    @Override
    public String toString() {
        return String.format("%s@%04d", this.ID, this.timeOfArrival);
    }

}
