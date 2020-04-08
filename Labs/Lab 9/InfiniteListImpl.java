import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.Optional;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.BiFunction;

public class InfiniteListImpl<T> implements InfiniteList<T> {
    Lazy<T> head;
    Supplier<InfiniteListImpl<T>> tail;

    protected InfiniteListImpl(Lazy<T> head, Supplier<InfiniteListImpl<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    //protected InfiniteListImpl(Lazy<T> head, InfiniteListImpl<T> tail) {
    //    this.head = () -> head;
    //    this.tail = () -> tail;
    //}

    public static <T> InfiniteListImpl<T> generate(Supplier<? extends T> supplier) {
        Lazy<T> head = Lazy.generate(supplier);
        Supplier<InfiniteListImpl<T>> tail = () -> InfiniteListImpl.generate(supplier);
        return new InfiniteListImpl<>(head, tail);
    }

    public static <T> InfiniteListImpl<T> iterate(T seed, UnaryOperator<T> next) {
        Lazy<T> head = Lazy.ofNullable(seed);
        Supplier<InfiniteListImpl<T>> tail = () -> InfiniteListImpl.iterate(next.apply(seed), next);
        return new InfiniteListImpl<>(head, tail);
    }

    @Override
    public InfiniteListImpl<T> peek() {
        Optional<T> headItem = head.get();

        if (headItem.isPresent()) {
            System.out.println(headItem.get());
        } 

        return this.tail.get();
    }

    @Override
    public <R> InfiniteListImpl<R> map(Function<? super T, ? extends R> mapper) {
        Supplier<InfiniteListImpl<R>> tail = () -> this.tail.get().map(mapper);
        return new InfiniteListImpl<R>(this.head.map(mapper), tail);
    }

    @Override
    public InfiniteListImpl<T> filter(Predicate<? super T> pred) {
        Supplier<InfiniteListImpl<T>> tail = () -> this.tail.get().filter(pred);
        return new InfiniteListImpl<>(this.head.filter(pred), tail);
    }

    @Override 
    public boolean isEmpty() {
        return false;
    }

    @Override
    public InfiniteListImpl<T> limit(long n) {
        if (n <= 0) {
            return new EmptyList<>();
        } else {
            return new InfiniteListImpl<T>(head, 
                () -> head.get().isPresent() 
                    ? (((n-1) == 0) 
                        ? new EmptyList<>() 
                        : this.tail.get().limit(n - 1))
                    : this.tail.get().limit(n));
        }
    }

    @Override
    public InfiniteListImpl<T> takeWhile(Predicate<? super T> pred) {
        Supplier<InfiniteListImpl<T>> tail = () -> this.tail.get().takeWhile(pred);
        Lazy<T> newHead = this.head.filter(pred);
        return new InfiniteListImpl<>(newHead, 
                () -> this.head.get().isPresent() && (newHead.get().isEmpty()) 
                ? new EmptyList<>()
                : this.tail.get().takeWhile(pred));
    }

    @Override
    public Object[] toArray() {
        ArrayList<? super Object> arr = new ArrayList<>();

        InfiniteListImpl<T> curr = this;

        while (!curr.isEmpty()) {
            Optional<T> headItem = curr.head.get();
            if (headItem.isPresent()) {
                arr.add(headItem.get());
            }
            curr = curr.tail.get();
        }

        return arr.toArray();
    }

    @Override
    public long count() {
        long count = 0;

        InfiniteListImpl<T> curr = this;

        while (!curr.isEmpty()) {
            Optional<T> headItem = curr.head.get();
            if (headItem.isPresent()) {
                count++;
            }
            curr = curr.tail.get();
        }
        return count;
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator) {
        U value = identity;

        InfiniteListImpl<T> curr = this;

        while (!curr.isEmpty()) {
            Optional<T> headItem = curr.head.get();
            if (headItem.isPresent()) {
                value = accumulator.apply(value, headItem.get());
            }
            curr = curr.tail.get();
        }

        return value;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        InfiniteListImpl<T> curr = this;

        while (!curr.isEmpty()) {
            Optional<T> headItem = curr.head.get();

            if (headItem.isPresent()) {
                action.accept(headItem.get());
            }

            curr = curr.tail.get();
        }
    }

}
