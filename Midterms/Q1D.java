import java.util.List;
import java.util.ArrayList;

public class D {
    public static <T> List<T> add(List<T> newList, T t) {
        newList.add(t);
        return newList;
    }
    public static <T> List<T> join(List<T> list1, List<? extends T> list2) {
        
        List<T> newList = new ArrayList<>();

        if (list1 == list2) {
            for (T t: list1) {
                newList.add(t);
            }
            return newList;
        }
        for (T t: list1) {
            newList.add(t);
        }
        for (T t: list2) {
            newList.add(t);
        }
        return newList;
    }
}
