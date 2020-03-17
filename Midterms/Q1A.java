   class X {
      private int x;
      public X(int x) {
         this.x = x;
      }
     public String toString() {
         return String.format("X:%d", x);
      }
   }
   class Y {
      private X x;
      public Y(X x) {
         this.x = x;
      }
      public String toString() {
         return "Y->" + this.x;
      }
   }
   public class Main {
       public static void main(String[] args) {
          X x = new X(1);
         Y y = new Y(x);
      }
   }
