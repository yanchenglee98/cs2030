public class BoxIt<T> implements Transformer<T, Box<? extends Object>> {
    @Override
    public  Box<? extends Object> transform(T t) {
        //Box<T> box = Box.ofNullable(t);
        return Box.of(t); 
    }
}
