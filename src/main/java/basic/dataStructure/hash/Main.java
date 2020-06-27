package basic.dataStructure.hash;

import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Student student = new Student(3, 2, "HaHa", "Zha");
        System.out.println(student.hashCode());

        Student student2 = new Student(3, 2, "HaHa", "Zha");
        System.out.println(student2.hashCode());

//        HashSet<Student> set = new HashSet<>();
//        set.add(student);

//        HashMap<Student, Integer> scores = new HashMap<>();
//        scores.put(student, 100);


    }
}
