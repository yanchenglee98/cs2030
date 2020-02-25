/**
 * Server class.
 * Contains serving customer, waiting customer and next service time.
 * @author Lee Yan Cheng
 */

public class Server {
    private final Customer serving;
    private final Customer waiting;
    private final double nextServiceTime;

    public Server() {
        this.serving = null;
        this.waiting = null;
        this.nextServiceTime = 0.0;
    }

    public Server(Customer serving, double serviceTime) {
        this.serving = serving;
        this.waiting = null;
        this.nextServiceTime = serviceTime + 1.0;
    }

    public Server(Customer serving) {
        this.serving = serving;
        this.waiting = null;
        this.nextServiceTime = serving.getTimeOfArrival() + 1.0; // assuming a service time of 1.0
    }

    public Server(Customer serving, Customer waiting) {
        this.serving = serving;
        this.waiting = waiting;
        this.nextServiceTime = serving.getTimeOfArrival() + 1.0; // assuming a service time of 1.0
    }

    public Server(Customer serving, Customer waiting, double serviceTime) {
        this.serving = serving;
        this.waiting = waiting;
        this.nextServiceTime = serviceTime + 1.0; // assuming a service time of 1.0
    }

    /**
     * <p> checks if server is availble to serve a customer </p>
     * @param Customer
     * @return returns true if server can serve customer and false otherwise
     */
    public boolean canServe(Customer c) {
        if (serving == null) {
            return true;
        } else if(c.getTimeOfArrival() - this.nextServiceTime >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * <p> checks if waiting customer can be served by server </p>
     * @return returns true if server can serve waiting customer and false otherwise
     */
    public boolean canServeWaiting() {
        if (waiting == null || serving == null) {
            return false;
        } else if (serving == null && waiting != null) {
            return true;
        } else if (waiting.getTimeOfArrival() - this.nextServiceTime >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * <p> checks if customer can go to server's waiting place </p>
     * @return returns true if customer can go onto waiting spot and false otherwise
     */
    public boolean isWaitingAvail() {
        return waiting == null;
    }

    /**
     * <p> serves the customer and returns a new server which is serving the current customer </p>
     * @param Customer
     * @return returns a new server which is serving the current customer
     */
    public Server serve(Customer c) {
        if (this.canServe(c)) {
            Server server = new Server(c);
            return server;
        } else {
            return null;
        }
    }

    /**
     * <p> returns the waiting cusomter is there is one </p>
     * @return returns customer on waiting list
     */
    public Customer getWaiting() {
        return waiting;
    }

    /**
     * <p> returns the served cusomter is there is one </p>
     * @return returns customer that is currently being served
     */
    public Customer getServing() {
        return serving;
    }

    /**
     * <p> returns the server's next service time </p>
     * @return returns server's next service time
     */
    public double getNextServiceTime() {
        return nextServiceTime;
    }

    /**
     * <p> returns a string representation of a server with its instance fields </p>
     * @return displays the customer being served, the customer waiting and the next service time
     */
    @Override
    public String toString() {
        if (serving != null) {
            return "serving:" + serving + " waiting: " + waiting + " next: " + nextServiceTime;
        } else {
            return "Server availble";
        }
    }
}