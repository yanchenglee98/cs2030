public interface Transformer<T extends Object, U> {
    public U transform(T t);
}
