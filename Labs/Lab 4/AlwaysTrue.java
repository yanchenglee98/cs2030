public class AlwaysTrue<T> implements BooleanCondition<T> {
    @Override
    public boolean test(T t) { return true; }
}
