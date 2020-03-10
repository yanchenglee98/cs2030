import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfRecords = sc.nextInt();

        Roster roster = new Roster("AY1920");

        for (int i = 0; i < numberOfRecords; i++) {
            Student student = new Student(sc.next());
            Module module = new Module(sc.next());
            String assessmentName = sc.next();
            String grade = sc.next();
            Assessment assessment = new Assessment(assessmentName, grade);

            if (roster.get(student.getKey()) == null) {
                roster.put(student);
            } else {
                student = roster.get(student.getKey());
            }

            if (student.get(module.getKey()) == null) {
                student.put(module);
            } else {
                module = student.get(module.getKey());
            }

            if (module.get(assessment.getKey()) == null) {
                module.put(assessment);
            } else {
                module.get(assessment.getKey());
            }

            module.put(assessment);

        }

        ArrayList<String> grades = new ArrayList<>();

        while(sc.hasNext()) {
            String student = sc.next();
            String module = sc.next();
            String assessment = sc.next();

            try {
                String string = roster.getGrade(student, module, assessment);
                grades.add(string);
            } catch (NoSuchRecordException e) {
                grades.add(e.toString());
            }
        }

        for (String s: grades) {
            System.out.println(s);
        }

    }
}
