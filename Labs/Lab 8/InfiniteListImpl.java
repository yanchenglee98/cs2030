import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.BinaryOperator;

public abstract class InfiniteListImpl<T> implements InfiniteList<T> {

    public static <T> InfiniteListImpl<T> generate(Supplier<? extends T> supplier) {
        return new InfiniteListImpl<T>() {
            @Override
            public Optional<T> get() {
                return Optional.of(supplier.get());
            }
        };
    }

    public static <T> InfiniteListImpl<T> iterate(T seed, UnaryOperator<T> next) {
        return new InfiniteListImpl<T>() {
            private T element = seed;
            private boolean firstElement = true;

            @Override
            public Optional<T> get() {
                if (firstElement) {
                    firstElement = false;
                } else {
                    element = next.apply(element);  
                }
                return Optional.of(element);
            }
        };
    }

    @Override 
    public InfiniteList<T> limit(long maxSize) {
        if (maxSize < 0) {
            String s = maxSize + "";
            throw new IllegalArgumentException(s);
        } else {
            return new InfiniteListImpl<T>() {
                private long limit = maxSize;
                private long count = 0;

                @Override
                public Optional<T> get() {
                    if (count >= limit) {
                        return Optional.empty();
                    } else {
                        count++;
                        return InfiniteListImpl.this.get();
                    }
                }          
            };
        }
    }

    @Override 
    public Object[] toArray() {
        ArrayList<T> list = new ArrayList<>();

        Optional<T> curr = get();

        while (!curr.isEmpty()) {
            T element = curr.get();
            list.add(element);   
            curr = this.get();
        }

        return list.toArray();
    }

    @Override 
    public void forEach(Consumer<? super T> action) {
        Optional<T> curr = this.get();

        while (!curr.isEmpty()) {
            T element = curr.get();
            action.accept(element);
            curr = this.get();
        }
    }

    @Override
    public <R> InfiniteList<R> map(Function<? super T, ? extends R> mapper) {
        return new InfiniteListImpl<R>() {
            @Override 
            public Optional<R> get() {
                return InfiniteListImpl.this.get().map(mapper);
            }
        };
    }

    @Override
    public InfiniteList<T> filter(Predicate<? super T> pred) {
        return new InfiniteListImpl<T>() {
            @Override
            public Optional<T> get() {
                Optional<T> curr = InfiniteListImpl.this.get();
                while (curr.isPresent() && !pred.test(curr.get())) {
                    curr = InfiniteListImpl.this.get();
                }
                return curr;
            }
        };
    }

    @Override
    public InfiniteList<T> takeWhile(Predicate<? super T> pred) {
        return new InfiniteListImpl<T>() {

            @Override
            public Optional<T> get() {
                Optional<T> elem = InfiniteListImpl.this.get();

                if (pred.test(elem.get())) {
                    return elem;
                } else {
                    return Optional.empty();
                }
            }
        };
    }

    @Override 
    public long count() {
        long count = 0;
        Optional<T> curr = this.get();

        while (!curr.isEmpty()) {
            count++;
            curr = this.get();
        }
        return count;
    }

    @Override
    public T reduce(T identity, BinaryOperator<T> accumulator) {
        T t = identity;

        Optional<T> curr = this.get();

        while (!curr.isEmpty()) {
            T element = curr.get();
            t = accumulator.apply(t, element);
            curr = this.get();
        }

        return t;
    }

    @Override
    public Optional<T> reduce(BinaryOperator<T> accumulator) {

        Optional<T> first = this.get();
        if (first.isEmpty()) {
            return Optional.empty();
        } else {

            T t = first.get();
            Optional<T> curr = this.get();

            while (curr.isPresent() && !curr.isEmpty()) {
                T element = curr.get();
                t = accumulator.apply(t, element);
                curr = this.get();
            }

            return Optional.of(t);
        }
    }
}
