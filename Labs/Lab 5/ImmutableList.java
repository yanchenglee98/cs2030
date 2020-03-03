import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.Collections;
import java.util.Comparator;

public class ImmutableList<T> {
    private final List<T> list;
    
    public ImmutableList(List<T> list) {
        List<T> newList = new ArrayList<>();
        for (T t: list) {
            newList.add(t);
        }
        this.list = newList;
    }

    @SafeVarargs
    public ImmutableList(T... list) {
        List<T> newList = new ArrayList<>();
        for (T t: list) {
            newList.add(t);
        }
        this.list = newList;
    }

    public ImmutableList<T> add(T element) {
       List<T> newList = new ArrayList<>(this.list);
       newList.add(element);
       return new ImmutableList<T>(newList);
    }

    public ImmutableList<T> remove(T element) {
        List<T> newList = new ArrayList<>(this.list);
        newList.remove(element);
        return new ImmutableList<T>(newList);
    }

    public ImmutableList<T> replace(T regex, T replacement) {
        List<T> newList = new ArrayList<>(this.list);
        newList.replaceAll((t) -> (t == regex) ? replacement : t);
        return new ImmutableList<T>(newList);    
    }

    public ImmutableList<T> filter(Predicate<? super T> pred) {
        ArrayList<T> newList = new ArrayList<>();
        for (T t: this.list) {
            if (pred.test(t)) {
                newList.add(t);
            } 
        }
        return new ImmutableList<T>(newList);
    }

    public <R> ImmutableList<R> map(Function<? super T, ? extends R> function) {
        ArrayList<R> newList = new ArrayList<>();
        for (T t: this.list) {
            newList.add(function.apply(t));
        }
        return new ImmutableList<R>(newList);
    }

    public ImmutableList<T> limit(long limiter) {
        if (limiter < 0) {
            throw new IllegalArgumentException("limit size < 0");
        } else {
            ArrayList<T> newList = new ArrayList<>();

            for (int i = 0; i < (Math.min(limiter, this.list.size())); i++) {
                newList.add(this.list.get(i));
            }
        
            return new ImmutableList<T>(newList);
       }
    }

    public ImmutableList<T> sorted() {
        if (this.list.size() == 0) {
            return new ImmutableList<T>(this.list);
        } else if (this.list.get(0) instanceof Comparable) {
            @SuppressWarnings("unchecked")
            T[] arr = (T[]) this.list.toArray();
            Arrays.sort(arr);
            List<T> newList = new ArrayList<T>(Arrays.asList(arr));
            return new ImmutableList<T>(newList);
        } else {
            throw new IllegalStateException("List elements do not implement Comparable");
        }
    }


    public ImmutableList<T> sorted(Comparator<T> comparator) {
        if (comparator == null) {
            throw new NullPointerException("Comparator is null");
        } else {
            ArrayList<T> newList = new ArrayList<>();
            for (T t: this.list) {
                newList.add(t);
            }
            Collections.sort(newList, comparator);
            return new ImmutableList<T>(newList);
        }
    }

    public Object[] toArray() {
        return this.list.toArray();
    }

    public <U> U[] toArray(U[] arr) {
        if (arr == null) {
            throw new NullPointerException("Input array cannot be null");
        } else {
            ArrayList<U> newList = new ArrayList<>();
            for (T t: list) {
                try {
                    return this.list.toArray(arr);
                } catch (ArrayStoreException e) {
                    throw new ArrayStoreException("Cannot add element to array as it is the wrong type");
                }
            }
            return newList.toArray(arr);
        }
    }

    @Override
    public String toString() {
        return this.list.toString();
    }
}
