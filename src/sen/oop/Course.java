package sen.oop;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private String dept;
    private int number;
    private int section;
    private Person instructor;
    private List<Person> enrolled = new ArrayList<>();

    public Course(String dept, int number, int section, Person instructor) {
        this.dept = dept;
        this.number = number;
        this.section = section;
        this.instructor = instructor;
    }

    public void enroll(Person student) {
        enrolled.add(student);
    }

    public void printClassList() {
        System.out.println("Course: " + this);
        System.out.println("\tInstructor: " + this.instructor);
        for (Person student : this.enrolled) {
            System.out.println("\tStudent: " + student);
        }
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("00");
        return dept.toUpperCase() + " " + number + " " + df.format(section);
    }
}
