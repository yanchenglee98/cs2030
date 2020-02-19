public class Server {
    private final Customer serving;
    private final Customer waiting;
    private final double nextServiceTime;

    public Server() {
        this.serving = null;
        this.waiting = null;
        this.nextServiceTime = 0.0;
    }

    public Server(Customer serving) {
        this.serving = serving;
        this.waiting = null;
        this.nextServiceTime = serving.getTimeOfArrival() + 1.0; // assuming a service time of 1.0
    }

    public boolean canServe(Customer c) {
        if (serving==null) {
            return true;
        } else if(c.getTimeOfArrival() - this.nextServiceTime >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public Server serve(Customer c) {
        if (this.canServe(c)) {
            Server server = new Server(c);
            return server;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        if (serving != null) {
            return String.format("Customer served; next service @ %.3f", this.nextServiceTime);
        } else {
            return "Server availble";
        }
    }
}