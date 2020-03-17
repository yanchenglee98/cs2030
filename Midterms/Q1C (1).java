   class B {
      static final String b = "B";
      String currentString;
      public B() {
         currentString = "B";
      }
      private B(String s) {
         currentString = s;
      }
      public B add(B newB) {
         String newString = this.currentString + newB.currentString;
         return new B(newString);
      }

      public B add(C newC) {
         String newString = this.currentString + newC.currentString;
         return new B(newString);
      }
      public String toString() {
         return currentString;
      }
   }
   class C {
      static final String c = "C";
      String currentString;
      public C() {
         currentString = "C";
      }
      private B(String s) {
         currentString = s;
      }
      public C add(B newB) {
         String newString = this.currentString + newB.currentString;
         return new C(newString);
      }

      public C add(C newC) {
         String newString = this.currentString + newC.currentString;
         return new C(newString);
      }
      public String toString() {
         return currentString;
      }
   }
   public class Main {
   }
