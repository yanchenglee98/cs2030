public class BoxIt<T> implements Transformer<T, Box<T>> {
    @Override
    public  Box<T> transform(T t) {
        //Box<T> box = Box.ofNullable(t);
        return Box.of(t); 
    }
}
