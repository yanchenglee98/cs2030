/**
 * States class.
 * Used to tag state to an Event .
 * Contains 5 states: arrives, waits, served, leaves, done.
 * Each state has an ID used for comparison: 1, 2, 3, 4, 5 respectively.
 * @author Lee Yan Cheng
 */

public enum States {

    ARRIVES("arrives", 1), // 0
    WAITS("waits", 2), // 1
    SERVED("served", 3), // 2
    LEAVES("leaves", 4), // 3
    DONE("done", 5); // 4
   
    private final String state;
    private final int stateID;

    /**
     * Constructer for cEvent
     * @param String the state in string format
     * @param StateID ID of state
     */
    States(String state, int stateID) {
        this.state = state;
        this.stateID = stateID;
    }

    /**
     * <p> Returns state in string format </p>
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * <p> Returns state ID </p>
     * @return state ID
     */
    public int getStateID() {
        return stateID;
    }
}