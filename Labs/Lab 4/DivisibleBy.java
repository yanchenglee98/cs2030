public class DivisibleBy implements BooleanCondition<Integer> {
    private final Integer x;
    
    public DivisibleBy(Integer x) {
        this.x = x;
    }

    //bridging
    // check if y is divisible by x

    @Override
    public boolean test(Integer y) {
        if (y == null) {
            return false;
        } else {
            return (y%this.x)==0;
        }    
    }
}
