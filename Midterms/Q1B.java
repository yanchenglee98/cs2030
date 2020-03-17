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
        A current = this;
        A returned = new A(this.a);
        A pointer = returned;
        while (current.next != null) {
            current = current.next;
            A temp = new A(current.a);
            pointer.next = temp;
            pointer = pointer.next;
        }

        pointer.next = newA;
        return returned;
    }
    public String toString() {
        String s = String.format("[A:%d]", this.a);
        
        if (next != null) {
            s += next.toString();
        }

        return s;
    }
}