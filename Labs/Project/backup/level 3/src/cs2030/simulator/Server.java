package cs2030.simulator;

import java.util.LinkedList;

/**
 * Server class.
 * Contains serving customer, waiting customer ,next service time, unique ID
 * @author Lee Yan Cheng
 */

public abstract class Server {
    protected final Customer serving;
    protected final int serverID;
    protected static int maxQueueLength;
    public boolean isHuman;
    public boolean isResting = false;

    public Server(Customer serving, int serverID, boolean isHuman) {
        this.serving = serving;
        this.serverID = serverID;
        this.isHuman = isHuman;
    }

    public static void setMaxQueueLength(int maxQueueLength) {
        Server.maxQueueLength = maxQueueLength;
    }

    public boolean isResting() {
        return isResting;
    }

    public abstract void rest();
    public abstract void back();

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
     * <p> checks if customer can go to server's waiting place. </p>
     * @return returns true if customer can go onto waiting spot and false otherwise
     */
    public abstract boolean isWaitingAvail();

    /**
     * <p> checks if server has no current job. </p>
     * @return returns true if server has no current job
     */
    public abstract boolean isIdle();

    /**
     * <p> returns a string representation of a server with its instance fields. </p>
     * @return displays serving, waiting customer and the next service time
     */
    @Override
    public String toString() {
        return "server " + this.serverID;
    }

    public abstract Server serve(Customer customer);

    public abstract Server addToWait(Customer customer);

    public abstract Server serveNext();

}