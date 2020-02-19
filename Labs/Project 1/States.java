public enum States {

    ARRIVES("arrives", 1), // 0
    WAITS("waits", 2), // 1
    SERVED("served", 3), // 2
    LEAVES("leaves", 4), // 3
    DONE("done", 5); // 4
   
    private final String state;
    private final int stateID;

    States(String state, int stateID) {
        this.state = state;
        this.stateID = stateID;
    }

    public String getState() {
        return state;
    }

    public int getStateID() {
        return stateID;
    }
}