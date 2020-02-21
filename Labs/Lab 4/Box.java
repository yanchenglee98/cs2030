public class Box<T> {
    private final T t;
    private static final Box<?> EMPTY_BOX = new Box<>(null); 

    private Box(T t) {
        this.t = t;
    }

    public static <T> Box<T> of(T t) {
        if(t==null) {
            return null;
        } else {
            return new Box<>(t);
        }
    }

    public T get() {
        return this.t;
    }

    @SuppressWarnings("unchecked")
    public static <T> Box<T> empty() {
        return (Box<T>) EMPTY_BOX;
    }

    public boolean isPresent() {
        if(this.t==null) {
            return false;
        } else {
            return true;
        }
    }

   // @SuppressWarnings("unchecked")
    public static <T> Box<T> ofNullable(T t) {
        if(t==null) { 
            return Box.<T>empty();
        } else {
            return new Box<>(t);
        }
    }

    @Override 
    public boolean equals(Object o) {
        if(this==o) {
            return true;
        } else if (this.t == null) {
            return false;
        } else if (o instanceof Box) {
            Box<?> B = (Box<?>) o;
            return t.equals(B.get());
        } else {
            return false;
        }
    }

    //@SuppressWarnings("unchecked")
    public Box<T> filter(BooleanCondition<? super T> bool) {
        if(bool.test(this.t)) {
            return this;
        } else {
            return Box.<T>empty();
        }
    }

    //@SuppressWarnings("unchecked")
    public <U> Box<U> map(Transformer<? super T, U> transformer) {
        if (this.t == null ) {
            return Box.<U>empty();
        } else {
            return Box.<U>ofNullable(transformer.transform(t));
        }
    }

    @Override
    public String toString() {
        if (this.t==null) {
            return "[" + "]";
        } else {
            return "[" + this.t + "]";
        }
    }
}
