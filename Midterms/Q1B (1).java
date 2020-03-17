   import java.util.List;
   import java.util.ArrayList;

   public class A {
      private int a;
      private A next;
      public A(int a) {
          this.a = a;
      }
      private A(int a, A next) {
         this.a = a;
         this.next = next;
      }
      public A next(int a) {
         A newA = new A(a);
         return new A(this.a, newA);
      }
      public String toString() {
         String s = String.format("[A:%d]", this.a);
         if (next != null) {
            s + next;
         }
         return s;
      }
   }
