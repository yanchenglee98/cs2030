   import java.util.List;

   public class D<T> {
      List<? super T> list;
      public static List<T> add(List<T> newList, T t) {
          this.list = newList;
          this.list.add(t);
      }
      public static List<T> join(List<T> list1, List<T> list2) {
         for (T t: list1) {
            this.list.add(t)
         }
         for (T t: list2) {
            this.list.add(t)
         }
         return this.list;
      }
   }
