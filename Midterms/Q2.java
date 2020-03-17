   import java.util.List;
   import java.util.ArrayList;
   
   interface TypeCaster<S, T> {
      public T cast(S s);
   }

   class ToString<T> implements TypeCaster<T, String> {
      @Override
      public String cast(Object obj) {
         return obj.toString();
      }
   }
   class Round implements TypeCaster<Double, Integer> {
      @Override
      public Integer cast(Double number) {
         return (int) Math.round(number);
      }
   }
   class ToList<T> implements TypeCaster<T[], List<T>> {
      @Override
      public List<T> cast(T[] arr) {
         List<T> list = new ArrayList<>();
         for (T t: arr) {
            list.add(t);
         }
         return list;
      }
   }
   class ListCaster {
         public static <S,T> List<T> castList(List<S> list, TypeCaster<S, T> caster) {
            List<T> newList = new ArrayList<>();
            for (S s: list) {
                  newList.add(caster.cast(s));
            }
            return newList;
         }
   }
