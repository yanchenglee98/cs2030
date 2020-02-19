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

    public boolean canServe(Customer c) {
        if (serving==null) {
            return true;
        } else if(c.getTimeOfArrival() - this.nextServiceTime >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean canServeWaiting() {
        if (waiting==null || serving == null) {
            return false;
        } if (serving==null && waiting!=null) {
            return true;
        } else if(waiting.getTimeOfArrival() - this.nextServiceTime >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isWaitingAvail() {
        return waiting == null;
    }

    public Server serve(Customer c) {
        if (this.canServe(c)) {
            Server server = new Server(c);
            return server;
        } else {
            return null;
        }
    }

    public Customer getWaiting() {
        return waiting;
    }

    public Customer getServing() {
        return serving;
    }

    public double getNextServiceTime() {
        return nextServiceTime;
    }

    @Override
    public String toString() {
        if (serving != null) {
            return "crurrently serving:" + serving + " waiting: " + waiting + " nextservicetime: " + nextServiceTime;
        } else {
            return "Server availble";
        }
    }
}