import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.BiFunction;

public class EmptyList<T> extends InfiniteListImpl<T> {

    public EmptyList() {
        super(Lazy.ofNullable(null), () -> InfiniteListImpl.generate(() -> null));
    }
    @Override
    public EmptyList<T> peek() {
        return this;
    }
 
    @Override
    public <R> EmptyList<R> map(Function<? super T, ? extends R> mapper) {
        return new EmptyList<R>();
    }
    
    @Override
    public EmptyList<T> filter(Predicate<? super T> pred) {
        return this;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public EmptyList<T> limit(long n) {
        return this;
    }
  
    @Override
    public EmptyList<T> takeWhile(Predicate<? super T> pred) {
        return this;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override 
    public long count() {
        return 0;
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator) {
        return identity;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        
    }
}
