package cs2030.simulator;

/**
 * Server class.
 * Contains serving customer, waiting customer ,next service time, unique ID
 * @author Lee Yan Cheng
 */

public abstract class Server {
    protected final Customer serving;
    protected final int serverID;
    protected static int maxQueueLength;
    protected boolean isHuman;
    protected boolean isResting = false;

    protected Server(Customer serving, int serverID, boolean isHuman) {
        this.serving = serving;
        this.serverID = serverID;
        this.isHuman = isHuman;
    }

    /**
     * <p> set the maximum queue length of all servers. </p>
     * @param maxQueueLength the maximum queue length that is to be set
     */
    public static void setMaxQueueLength(int maxQueueLength) {
        Server.maxQueueLength = maxQueueLength;
    }

    /**
     * <p> checks if the server is resting currently. </p>
     * @return returns true if the server is resting and false otherwise
     */
    public boolean isResting() {
        return isResting;
    }

    /**
     * <p> checks if the server is a human. </p>
     * @return returns true if server is a human and false if automated
     */
    public boolean isHuman() {
        return isHuman;
    }

    /**
     * <p> returns the served customer if there is one. </p>
     * @return returns customer that is currently being served
     */
    public Customer getServing() {
        return serving;
    }

    /**
     * <p> returns the server's unique ID. </p>
     * @return the serverID
     */
    public int getServerID() {
        return serverID;
    }

    /**
     * <p> sets the server as resting. </p>
     */
    public abstract void rest();

    /**
     * <p> sets the server as back. </p>
     */
    public abstract void back();

    /**
     * <p> return the server's current waiting queue size. </p>
     * @return returns the server's queue size
     */
    public abstract int getQueueSize();

    /**
     * <p> checks if server's waiting queue has space. </p>
     * @return returns true if server's waiting queue is less than max queue size
     */
    public abstract boolean isWaitingAvail();

    /**
     * <p> checks if server has no current job. </p>
     * @return returns true if server has no current job and false otherwise
     */
    public abstract boolean isIdle();

    /**
     * <p> serves the customer. </p>
     * @param customer customer that the server will serve
     * @return returns a server that is serving the customer
     */
    public abstract Server serve(Customer customer);

    /**
     * <p> adds the customer to the server's waiting queue. </p>
     * @param customer customer that the server will serve
     * @return returns an updated server
     */
    public abstract Server addToWait(Customer customer);

    /**
     * <p> serves the next customer in the waiting queue. </p>
     * @return returns an update server that is serving the next customer
     */
    public abstract Server serveNext();

    /**
     * <p> returns a string representation of a server with its instance fields. </p>
     * @return displays serving, waiting customer and the next service time
     */
    @Override
    public String toString() {
        return "server " + this.serverID;
    }
}