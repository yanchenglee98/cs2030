import java.util.*;
 
public class Voem<T> {
T v;
boolean success;
 
Voem(T v, boolean success) {
   this.v = v;
   this.success = success;
}
 
static <T> Voem<T> ok(T v) {
   return new Voem(v, true);
}
 
static Voem<String> fail(String msg) {
   return new Voem(msg, false);
}
 
public String toString() {
   if (success) {
      return "Done: " + this.v;
   } else {
      return "Oops: " + this.v;
   }
}
 
<R> Voem<R> map(Function<? super T, ? extends R> f) {
   try {
      if (success) {
      R r = f.apply(this.v);
      return Voem.of(r, true);
      } else {
         return Voem.fail("error");
      }
   } catch (Exception e) {
      String message = e.getMessage();
      return Voem.of(message, false);
   }
}
 
<R> Voem<R> flatMap(Function<? super T, ? extends Voem<? extends R>> f) {
   try {
      if (success) {
      Voem<R> r = f.apply(this.v);
      return new Voem(r.v, r.success);
      } else {
       return Voem.fail("error");
      }
   } catch (Exception e) {
      String message = e.getMessage();
      return Voem.of(message, false);
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
}
 
public boolean getSuccess() {
   return success;
}
}
