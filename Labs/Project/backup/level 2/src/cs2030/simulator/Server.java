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
    private double nextServiceTime;
    private final int serverID;
    private static int maxQueueLength;

    /**
     * Constructor for Server.
     * @param serverID unique ID of server
     */
    public Server(int serverID) {
        this.serving = null;
        this.waiting = new LinkedList<>();
        this.nextServiceTime = 0.0;
        this.serverID = serverID;
    }

    public Server(Customer serving, int serverID) {
        this.serving = serving;
        this.waiting = new LinkedList<>();
        this.nextServiceTime = 0.0;
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
    public Server(Customer serving, LinkedList<Customer> waiting, double nextServiceTime, int serverID) {
        this.serving = serving;
        this.waiting = waiting;
        this.nextServiceTime = nextServiceTime; 
        this.serverID = serverID;
    }

    public Server(Customer serving, LinkedList<Customer> waiting, int serverID) {
        this.serving = serving;
        this.waiting = waiting;
        this.nextServiceTime = 0.0;
        this.serverID = serverID;
    }

    public static void setMaxQueueLength(int maxQueueLength) {
        Server.maxQueueLength = maxQueueLength;
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
        return "server " + this.serverID;
    }

    public Server serve(Customer customer) {
        return new Server(customer, this.serverID);
    }

    public Server addToWait(Customer customer) {
        LinkedList<Customer> newQueue = this.waiting;
        newQueue.add(customer);
        return new Server(this.serving, newQueue, this.serverID);
    }

    public Server serveNext() {
        LinkedList<Customer> newQueue = this.waiting;
        Customer newServing = newQueue.poll();
        return new Server(newServing, newQueue, this.nextServiceTime, this.serverID);
    }

    public void setServingTime(double servingTime) {
        this.nextServiceTime = servingTime;
    }
}