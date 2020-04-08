import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lazy<T> {
    private Optional<T> t;
    private Supplier<? extends Optional<T>> supplier;
    private boolean cached;

    private Lazy(Optional<T> t) {
        this.t = t;
        cached = true;
        this.supplier = () -> t;
    }

    private Lazy(Supplier<? extends Optional<T>> supplier) {
        this.supplier = supplier;
    }

    public static <T> Lazy<T> ofNullable(T t) {
        return new Lazy<>(Optional.ofNullable(t));
    }

    public static <T> Lazy<T> generate(Supplier<? extends T> supplier) {
        Supplier<? extends Optional<T>> s = () -> Optional.ofNullable(supplier.get());
        return new Lazy<T>(s);
    }

    public Optional<T> get() {
        if (!cached) {
            this.t = supplier.get();
            cached = true;
        } 
        return this.t;
    }

    public <R> Lazy<R> map(Function<? super T, ? extends R> mapper) {
        return new Lazy<R>(() -> this.get().map(mapper));
    }

    public Lazy<T> filter(Predicate<? super T> predicate) {
        return new Lazy<T>(() -> this.get().filter(predicate));
    }

    @Override
    public String toString() {
        if (cached) {
            if (this.t.isEmpty()) {
                return "null";
            } else {
                return t.get() + "";
            }
        } else {
            return "?";
        }
    }

}
