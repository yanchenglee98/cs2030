public class Student extends KeyableMap<String, String, Module> implements Keyable<String> {
    public Student(String string) {
        super(string); 
    }

    @Override 
    public String getKey() {
        return super.name;
    }
    @Override
    public Student put(Module module) {
        super.put(module);
        return this;
    }
}
