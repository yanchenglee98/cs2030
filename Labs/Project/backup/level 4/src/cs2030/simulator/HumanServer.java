package cs2030.simulator;

import java.util.LinkedList;

/**
 * Server class.
 * Contains serving customer, waiting customer ,next service time, unique ID
 * @author Lee Yan Cheng
 */

public class HumanServer extends Server{
    private LinkedList<Customer> waiting;

    /**
     * Constructor for Server.
     * @param serverID unique ID of server
     */
    public HumanServer(int serverID) {
        super(null, serverID, true);
        this.waiting = new LinkedList<>();
    }

    public HumanServer(Customer serving, int serverID) {
        super(serving, serverID, true);
        this.waiting = new LinkedList<>();
    }

    /**
     * Constructor for Server.
     * NOTE for this constructor, nextServiceTime is set by user
     * @param serving customer that is being served
     * @param waiting customer that is waiting to be served
     * @param serverID unique ID of server
     */

    public HumanServer(Customer serving, LinkedList<Customer> waiting, int serverID) {
        super(serving, serverID, true);
        this.waiting = waiting;
    }

    @Override
    public void rest() {
        super.isResting = true;
    }

    @Override
    public void back() {
        super.isResting = false;
    }

    @Override
    public int getQueueSize() {
        return this.waiting.size();
    }

    /**
     * <p> checks if customer can go to server's waiting place. </p>
     * @return returns true if customer can go onto waiting spot and false otherwise
     */
    @Override
    public boolean isWaitingAvail() {
        return waiting.size() < Server.maxQueueLength;
    }

    /**
     * <p> checks if server has no current job. </p>
     * @return returns true if server has no current job
     */
    @Override
    public boolean isIdle() {
        return serving == null && waiting.isEmpty();
    }

    /**
     * <p> returns a string representation of a server with its instance fields. </p>
     * @return displays serving, waiting customer and the next service time
     */
    @Override
    public String toString() {
        return "server " + this.serverID;
    }

    @Override
    public HumanServer serve(Customer customer) {
        return new HumanServer(customer, this.serverID);
    }

    @Override
    public HumanServer addToWait(Customer customer) {
        LinkedList<Customer> newQueue = this.waiting;
        newQueue.add(customer);
        return new HumanServer(this.serving, newQueue, this.serverID);
    }

    @Override
    public HumanServer serveNext() {
        LinkedList<Customer> newQueue = this.waiting;
        Customer newServing = newQueue.poll();
        return new HumanServer(newServing, newQueue, this.serverID);
    }

}