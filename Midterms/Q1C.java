class A {
    String currentString;

    protected A(String s) {
        currentString = s;
    }
    public A add(A newA) {
        String newString = this.currentString + newA.currentString;
        return new A(newString);
    }

    public String toString() {
        return currentString;
    }
}

class B extends A {
    static final String b = "B";
    public B() {
        super(b);
    }
}
class C extends A {
    static final String c = "C";

    public C() {
        super(c);
    }
}