import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.BiFunction;

public interface InfiniteList<T> {
    public static <T> InfiniteList<T> generate(Supplier<? extends T> supplier) {
        return InfiniteListImpl.generate(supplier);
    }
    
    public static <T> InfiniteList<T> iterate(T seed, UnaryOperator<T> next) {
        return InfiniteListImpl.iterate(seed, next);
    }

    public InfiniteList<T> peek();
 
    public <R> InfiniteList<R> map(Function<? super T, ? extends R> mapper);
    
    public InfiniteList<T> filter(Predicate<? super T> pred);

    public boolean isEmpty();

    public InfiniteList<T> limit(long n);

    public InfiniteList<T> takeWhile(Predicate<? super T> pred);

    public Object[] toArray();

    public long count();

    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator);

    public void forEach(Consumer<? super T> action);
}
