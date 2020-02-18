public class AlwaysFalse<T> implements BooleanCondition<T> {
    @Override
    public boolean test(T t) {return false;}
}
