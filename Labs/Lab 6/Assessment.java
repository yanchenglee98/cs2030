public class Assessment implements Keyable<String> {
    private final String name;
    private final String grade;

    public Assessment(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }
    
    public String getGrade() {
        return this.grade;
    }

    @Override 
    public String getKey() {
        return this.name;
    }

    @Override
    public String toString() {
        return String.format("{%s: %s}", this.name, this.grade);
    }
}
