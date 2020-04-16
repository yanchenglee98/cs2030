import java.util.*;
import java.util.function.Function;

public class Voem<T> {
   T v;
   boolean success;
   String msg;

   Voem(T v, String msg, boolean success) {
      this.v = v;
      this.msg = msg;
      this.success = success;
   }

   static <T> Voem<T> ok(T v) {
      return new Voem<>(v, null, true);
   }

   static <T> Voem<T> fail(String msg) {
      return new Voem<T>(null, msg, false);
   }

   public String toString() {
      if (success) {
         return "Done: " + this.v;
      } else {
         return "Oops: " + this.msg;
      }
   }

   <R> Voem<R> map(Function<? super T, ? extends R> f) {
      try {
         if (success) {
            R r = f.apply(this.v);
            return Voem.ok(r);
         } else {
            return Voem.fail("error");
         }
      } catch (Exception e) {
         String message = e.getMessage();
         return Voem.fail(message);
      }
   }

   <R> Voem<R> flatMap(Function<? super T, ? extends Voem<? extends R>> f) {
      try {
         if (success) {
            Voem<? extends R> r = f.apply(this.v);
            return new Voem<>(r.v, r.msg, r.success);
         } else {
            return Voem.fail("error");
         }
      } catch (Exception e) {
         String message = e.getMessage();
         return Voem.fail(message);
      }
   }

   public T orElse(T value) {
      if (success) {
         return this.v;
      } else {
         return value;
      }
   }

   public T get() {
      if (success) {
         return this.v;
      }
      return null;
   }

   public boolean getSuccess() {
      return success;
   }

}
