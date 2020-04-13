package cs2030.simulator;

/**
 * States class.
 * Used to tag state to an Event.
 * Contains 5 states: arrives, waits, served, leaves, done.
 * Each state has an ID used for comparison: 1, 2, 3, 4, 5 respectively.
 * @author Lee Yan Cheng
 */

public enum States {

    /**
    * Signify customer arrives; has ID of 1.
    */
    ARRIVES("arrives", 1), // 0
    
    /**
    * Signify customer waits; has ID of 2.
    */
    WAITS("waits", 2), // 1
    
    /**
    * Signify customer is served; has ID of 3.
    */
    SERVED("served", 3), // 2
    
    /**
    * Signify customer leaves; has ID of 4.
    */
    LEAVES("leaves", 4), // 3
    
    /**
    * Signify customer is done; has ID of 5.
    */
    DONE("done", 5), // 4

    /**
     * Signify server is resting; has ID of 6.
     */
    SERVER_REST("rest", 6),

    /**
     * Signify server is back; has ID of 7.
     */
    SERVER_BACK("back", 7);
   
    private final String state;
    private final int stateID;

    /**
     * Constructor for States.
     * @param state the state in string format
     * @param stateID ID of the state
     */
    States(String state, int stateID) {
        this.state = state;
        this.stateID = stateID;
    }

    /**
     * <p> Returns state in string format. </p>
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * <p> Returns state ID. </p>
     * @return state ID
     */
    public int getStateID() {
        return stateID;
    }
}