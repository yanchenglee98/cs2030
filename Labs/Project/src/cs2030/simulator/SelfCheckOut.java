package cs2030.simulator;

import java.util.LinkedList;

public class SelfCheckOut extends Server {
    public static LinkedList<Customer> waiting = new LinkedList<>();

    public SelfCheckOut(int serverID) {
        super(null, serverID, false);
    }

    private SelfCheckOut(Customer serving, int serverID) {
        super(serving, serverID, false);
    }

    @Override
    public void rest() {
        super.isResting = false;
    }

    @Override
    public void back() {
        super.isResting = false;
    }

    @Override
    public int getQueueSize() {
        return SelfCheckOut.waiting.size();
    }

    @Override
    public boolean isWaitingAvail() {
        return SelfCheckOut.waiting.size() < Server.maxQueueLength;
    }

    @Override
    public boolean isIdle() {
        return super.serving == null && waiting.isEmpty();
    }

    @Override
    public SelfCheckOut serve(Customer customer) {
        return new SelfCheckOut(customer, this.serverID);
    }

    @Override
    public SelfCheckOut addToWait(Customer customer) {
        SelfCheckOut.waiting.add(customer);
        return this;
    }

    @Override
    public SelfCheckOut serveNext() {
        Customer nextCustomer = SelfCheckOut.waiting.poll();
        return new SelfCheckOut(nextCustomer, this.serverID);
    }

    @Override
    public String toString() {
        return "self-check " + this.serverID;
    }
}
