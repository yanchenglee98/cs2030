import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.function.Supplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.BinaryOperator;

public interface InfiniteList<T> {
    public Optional<T> get();
    
    public static <T> InfiniteList<T> generate(Supplier<? extends T> supplier) {
        return InfiniteListImpl.generate(supplier);
    }
    
    public static <T> InfiniteList<T> iterate(T seed, UnaryOperator<T> f) {
        return InfiniteListImpl.iterate(seed, f);
    }

    public InfiniteList<T> limit(long maxSize);

    public void forEach(Consumer<? super T> action);

    public Object[] toArray();

    public <R> InfiniteList<R> map(Function<? super T, ? extends R> mapper);

    public InfiniteList<T> filter(Predicate<? super T> pred);

    public InfiniteList<T> takeWhile(Predicate<? super T> pred);

    public long count();

    public T reduce(T identity, BinaryOperator<T> accumulator);

    public Optional<T> reduce(BinaryOperator<T> accumulator);
}
