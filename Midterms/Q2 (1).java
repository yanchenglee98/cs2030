   import java.util.List;
   interface TypeCaster<S, T> {
      public T cast(S s) {
         return (T) s;
      }
   }

   class ToString implements TypeCaster<Object, String> {
      @Override
      public String cast(Object obj) {
         return (String) obj;
      }
   }
   class Round implements TypeCaster<Double, Integer> {
      @Override
      public Integer cast(Double number) {
         return (Integer) Math.round(number);
      }
   }
   class ToList implements TypeCaster<T[], List<T>> {
      @Override
      public List<T> cast(T[] arr) {
         List<T> list = new List<>();
         for (T t: arr) {
            list.add(t);
         }
         return list;
      }
   }
   public ListCaster {
         public <S,T> List<T> castList(List<S> list, TypeCaster<S, T>
   caster) {
            List<T> newList = new List<>();
            for (S s: list) {
                  newList.add(caster.cast(s));
            }
            return newList;
         }
   }
   public class Main {
   }
