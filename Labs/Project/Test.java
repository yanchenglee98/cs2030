import java.util.Comparator;
import java.util.PriorityQueue;

class Student {
    public double GPA;

    public Student(double GPA) {
        this.GPA = GPA;
    }

    public String toString() {
        return String.format("%.3f", GPA);
    }
}

class GPAComparator implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        if (s1.GPA > s2.GPA) {
            return 1;
        } else if (s1.GPA < s2.GPA) {
            return -1;
        }
        return 0;
    }
}
public class Test {
    public static void main(String[] args) {
        PriorityQueue<Student> student = new PriorityQueue<>(new GPAComparator());

        student.add(new Student(3.0));
        student.add(new Student(3.5));
        student.add(new Student(3.8));
        student.add(new Student(2.5));
        student.add(new Student(3.6));
        student.add(new Student(1.5));

        for (Student s: student) {
            System.out.println(s);
        }
    }
}