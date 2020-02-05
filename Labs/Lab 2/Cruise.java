/**
 * Cruise class
 * Contains ID , time of arrival, number of loaders and service time
 * 
 * @author Yan Cheng
 * @version 1.0
 * @since 2019-02-05
 */

public class Cruise {
    protected final String ID;
    protected final int timeOfArrival;
    protected final int numberOfLoader;
    protected final int serviceTime;

    /**
     * Constructor for Cruise
     * @param ID the cruise ID
     * @param timeOfArrival the time of arrival of the cruise 
     * @param numberOfLoader the required number of loaders to service the cruise
     * @param serviceTime the service time of the cruise
     */
    Cruise(String ID, int timeOfArrival, int numberOfLoader, int serviceTime) {
        this.ID = ID;
        this.timeOfArrival = timeOfArrival;
        this.numberOfLoader = numberOfLoader;
        this.serviceTime = serviceTime;
    }

    /**
     * returns the time of arrival of the cruise
     * @return time of arrival
     */
    public int getArrivalTime() {
        return (timeOfArrival/100)*60 + timeOfArrival-((timeOfArrival/100)*100);
    }
    
    /**
     * returns the number of loaders required to service the cruise
     * @return number of loaders required to service the cruise
     */
    public int getNumOfLoadersRequired() {
        return this.numberOfLoader;
    }

    /**
     * returns the service completion time of the cruise
     * @return service completion time of the cruise
     */
    public int getServiceCompletionTime() {
        return getArrivalTime() + serviceTime;
    }

    @Override
    public String toString() {
        return String.format("%s@%04d", this.ID, this.timeOfArrival);
    }

}
