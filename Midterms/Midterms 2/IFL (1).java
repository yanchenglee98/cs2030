import java.util.*
import java.util.*
import java.util.*
import java.util.function.Supplier;
import java.util.function.Predicate;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

class IFL<T> {
    Supplier<T> head;
    Supplier<IFL<T>> tail;
    /* FIELDS AND METHODS START: DO NOT REMOVE */
boolean isEmpty;
    /* FIELDS AND METHODS END: DO NOT REMOVE */

    IFL(Supplier<T> head, Supplier<IFL<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    static <T> IFL<T> of(List<? extends T> list) {
        /* OF START: DO NOT REMOVE!!! */
 
 {
   Supplier<T> head = () -> list.get(0);
   Supplier<T> tail = () -> IFL.of(list.subList(1, list.size());
   return new IFL(head, tail);
}
        /* OF END: DO NOT REMOVE!!! */
    }

    Optional<T> findMatch(Predicate<? super T> predicate) {
        /* FINDMATCH START: DO NOT REMOVE!!! */
 
 {
   IFL<T> curr = this;
   while(!isEmpty() && !predicate.test(curr.head.get())) {
      curr = curr.tail.get();
   }
   if (this.isEmpty()) {
      return Optional.empty()
   } else {
      return Optional.ofNullable(curr.head.get());
   }
}
        /* FINDMATCH END: DO NOT REMOVE!!! */
    }
}

/* ADDITIONAL CODE START: DO NOT REMOVE */
 
public T get() {
   return head.get();
}
 
public IFL<T> peek() {
   if (head != null ) {
      System.out.println(head.get);
      return tail.get();
   }   
}
 
public boolean isEmpty() {
   return head == null;
}
/* ADDITIONAL CODE END: DO NOT REMOVE */
