public enum States {
    ARRIVES("arrives"),
    SERVED("served"),
    LEAVES("leaves"),
    DONE("done");

    private final String state;

    States(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}