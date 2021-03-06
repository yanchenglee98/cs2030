import java.util.PriorityQueue;

/**
 * Server class.
 * Contains serving customer, waiting customer ,next service time, unique ID
 * @author Lee Yan Cheng
 */

public class Server {
    private final Customer serving;
    private final Customer waiting;
    private final double nextServiceTime;
    private final int serverID;

    /**
     * Constructor for Server.
     * @param serverID unique ID of server
     */
    public Server(int serverID) {
        this.serving = null;
        this.waiting = null;
        this.nextServiceTime = 0.0;
        this.serverID = serverID;
    }

    /**
     * Constructor for Server.
     * NOTE for this constructor, nextServiceTime is set automatically according to serviceTime
     * @param serving customer that is being served
     * @param serviceTime time required to serve the customer
     * @param serverID unique ID of server
     */

    public Server(Customer serving, double serviceTime, int serverID) {
        this.serving = serving;
        this.waiting = null;
        this.nextServiceTime = serving.getTimeOfArrival() + serviceTime;
        this.serverID = serverID;
    }

    /**
     * Constructor for Server.
     * NOTE for this constructor, nextServiceTime is set by user
     * @param serving customer that is being served
     * @param waiting customer that is waiting to be served
     * @param nextServiceTime next service time of server,
     *                        do note this sets the nextServiceTime of server
     * @param serverID unique ID of server
     */
    public Server(Customer serving, Customer waiting, double nextServiceTime, int serverID) {
        this.serving = serving;
        this.waiting = waiting;
        this.nextServiceTime = nextServiceTime; 
        this.serverID = serverID;
    }

    /**
     * Updates the server with the current time.
     * @param currentTime the current time
     * @param eventLog event log to be updated when the server is updated
     * @return returns an updated server
     */
    public Server updateServer(double currentTime, PriorityQueue<Event> eventLog) {
        if (this.isIdle()) {
            return this;
        } else if (currentTime >= nextServiceTime) {
            // when current time exceeds next service time, update server

            // 1st case: when there is no waiting customer
            // previous customer is served finished
            if (waiting == null) {
                // add done log
                eventLog.add(new
                        Event(this.serving, this.nextServiceTime, States.DONE, this.serverID));
                return new Server(this.serverID); // server is now idle
            } else {
                // 2nd case: when there is a waiting customer
                // previous customer is served finish
                // thus waiting customer will be served

                // add done log of previous customer
                eventLog.add(new
                        Event(this.serving, this.nextServiceTime, States.DONE, this.serverID));
                // add served log of waiting customer
                eventLog.add(new
                        Event(this.waiting, this.nextServiceTime, States.SERVED, this.serverID));
                // update total waiting time
                Event.setTotalWaitingTime(this.nextServiceTime - this.waiting.getTimeOfArrival());
                // server now serving waiting; assume service time of 1.0
                Server update = new
                        Server(this.waiting, null, this.nextServiceTime + 1.0, this.serverID);

                // corner case where at current time both serving and waiting are served finished
                // need to check that after 1st update if server needs to be updated again
                if (currentTime >= update.getNextServiceTime()) {
                    // if at current time, waiting customer is also served finish
                    // need to update server again

                    // add done log of waiting customer
                    eventLog.add(new
                            Event(update.serving, update.nextServiceTime,
                            States.DONE, this.serverID));
                    update = new Server(this.serverID);
                }

                return update;
            }
        } else { // current time < next service time then don't update
            return this;
        }
    }

    /**
     * <p> checks if customer can go to server's waiting place. </p>
     * @return returns true if customer can go onto waiting spot and false otherwise
     */
    public boolean isWaitingAvail() {
        return waiting == null;
    }

    /**
     * <p> checks if server has no current job. </p>
     * @return returns true if server has no current job
     */
    public boolean isIdle() {
        return serving == null && waiting == null;
    }

    /**
     * <p> returns the waiting customer if there is one. </p>
     * @return returns customer on waiting list
     */
    public Customer getWaiting() {
        return waiting;
    }

    /**
     * <p> returns the served customer if there is one. </p>
     * @return returns customer that is currently being served
     */
    public Customer getServing() {
        return serving;
    }

    /**
     * <p> returns the server's next service time. </p>
     * @return returns server's next service time
     */
    public double getNextServiceTime() {
        return nextServiceTime;
    }

    /**
     * <p> returns the server's unique ID. </p>
     * @return the serverID
     */
    public int getServerID() {
        return serverID;
    }

    /**
     * <p> returns a string representation of a server with its instance fields. </p>
     * @return displays serving, waiting customer and the next service time
     */
    @Override
    public String toString() {
        if (serving != null) {
            return "serving:" + serving + " waiting: " + waiting + " next: " + nextServiceTime;
        } else {
            return "Server available";
        }
    }
}