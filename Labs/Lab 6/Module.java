public class Module extends KeyableMap<String, String, Assessment> implements Keyable<String> {
    public Module(String string) {
        super(string); 
    }

    @Override 
    public Module put(Assessment assessment) {
        super.put(assessment);
        return this;
    }

    @Override 
    public String getKey() {
        return super.name;
    }

}
