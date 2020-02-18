public class LongerThan implements BooleanCondition<String> {
    private final int x;

    public LongerThan(int x) {
        this.x = x;
    }

    @Override
    public boolean test(String o) {
        if (o==null) {
            return false;
        } else {
            int lengthOfo = o.length();
            return lengthOfo > x;
        }    
    }
}
