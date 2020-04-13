package cs2030.simulator;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Server class.
 * Contains serving customer, waiting customer ,next service time, unique ID
 * @author Lee Yan Cheng
 */

public class Server {
    private final Customer serving;
    private final LinkedList<Customer> waiting;
    private final double nextServiceTime;
    private final int serverID;
    private int maxQueueLength;

    /**
     * Constructor for Server.
     * @param serverID unique ID of server
     */
    public Server(int serverID, int maxQueueLength) {
        this.serving = null;
        this.waiting = new LinkedList<>();
        this.nextServiceTime = 0.0;
        this.serverID = serverID;
        this.maxQueueLength = maxQueueLength;
    }

    public Server(Customer serving, int serverID, int maxQueueLength) {
        this.serving = serving;
        this.waiting = new LinkedList<>();
        this.nextServiceTime = 0.0;
        this.serverID = serverID;
        this.maxQueueLength = maxQueueLength;
    }

    /**
     * Constructor for Server.
     * NOTE for this constructor, nextServiceTime is set automatically according to serviceTime
     * @param serving customer that is being served
     * @param serviceTime time required to serve the customer
     * @param serverID unique ID of server
     */

    public Server(Customer serving, double serviceTime, int serverID, int maxQueueLength) {
        this.serving = serving;
        this.waiting = new LinkedList<>();
        this.nextServiceTime = serving.getTime() + serviceTime;
        this.serverID = serverID;
        this.maxQueueLength = maxQueueLength;
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
    public Server(Customer serving, LinkedList<Customer> waiting, double nextServiceTime, int serverID, int maxQueueLength) {
        this.serving = serving;
        this.waiting = waiting;
        this.nextServiceTime = nextServiceTime; 
        this.serverID = serverID;
        this.maxQueueLength = maxQueueLength;
    }

    public Server(Customer serving, LinkedList<Customer> waiting, int serverID, int maxQueueLength) {
        this.serving = serving;
        this.waiting = waiting;
        this.nextServiceTime = 0.0;
        this.serverID = serverID;
        this.maxQueueLength = maxQueueLength;
    }

    /**
     * Updates the server with the current time.
     * @param currentTime the current time
     * @param eventLog event log to be updated when the server is updated
     * @return returns an updated server
     */
    public Server updateServer(double currentTime, PriorityQueue<Event> eventLog, RandomGenerator rand) {
        if (this.isIdle()) {
            return this;
        } else if (currentTime >= nextServiceTime) {
            // when current time exceeds next service time, update server

            // 1st case: when there is no waiting customer
            // previous customer is served finished
            if (waiting.isEmpty()) {
                return new Server(this.serverID, maxQueueLength); // server is now idle
            } else {
                // 2nd case: when there is a waiting customer
                // previous customer is served finish
                // thus waiting customer will be served

                Server update = this;

                while (currentTime >= update.getNextServiceTime()) {
                    // if at current time, waiting customer is also served finish
                    // need to update server again

                    if (update.waiting.isEmpty()) {
                        update = new Server(this.serverID, maxQueueLength);
                        break;
                    } else {
                        // add served log of next waiting customer
                        Customer next = update.waiting.poll();
                        eventLog.add(new Event(next, update.nextServiceTime, States.SERVED, this.serverID));
                        // add done log of next waiting customer
                        double nextServiceTime = update.nextServiceTime + rand.genServiceTime();
                        eventLog.add(new Event(next, nextServiceTime, States.DONE, this.serverID));
                        // update total waiting time
                        Event.setTotalWaitingTime(update.nextServiceTime - next.getTime());
                        update = new Server(next, update.waiting, nextServiceTime, this.serverID, maxQueueLength);
                    }
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
        return waiting.size() < maxQueueLength;
    }

    /**
     * <p> checks if server has no current job. </p>
     * @return returns true if server has no current job
     */
    public boolean isIdle() {
        return serving == null && waiting.isEmpty();
    }

    /**
     * <p> returns the waiting customer if there is one. </p>
     * @return returns customer on waiting list
     */
    public LinkedList<Customer> getWaiting() {
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
            return "serving: " + serving + " waiting: " + waiting + " nextServingTime: " + nextServiceTime;
        } else {
            return "Server available";
        }
    }

    public Server serve(Customer customer) {
        return new Server(customer, this.serverID, this.maxQueueLength);
    }

    public Server addToWait(Customer customer) {
        LinkedList<Customer> newQueue = this.waiting;
        newQueue.add(customer);
        return new Server(this.serving, newQueue, this.serverID, this.maxQueueLength);
    }

    public Server serveFinish() {
        LinkedList<Customer> newQueue = this.waiting;
        Customer newServing = newQueue.poll();
        return new Server(newServing, newQueue, this.serverID, this.maxQueueLength);
    }
}