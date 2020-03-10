public class Roster extends KeyableMap<String, String, Student> {
    public Roster(String string) {
        super(string);
    }

    @Override
    public Roster put(Student student) {
        super.put(student);
        return this;
    }

    public String getGrade(String student, String module, String assessment) throws NoSuchRecordException {
        try {
            return super.get(student).get(module).get(assessment).getGrade();
        } catch (NullPointerException e) {
            throw new NoSuchRecordException(String.format("No such record: %s %s %s", student, module, assessment));
        }
    }
}
