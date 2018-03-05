package activityStarterCode.sortingStudents;

import java.util.Arrays;

/**
 * Implementation of the first / most boring step of this activity.
 */
public class StudentSortingPractice {
    public static void main(String[] args) {
        Student[] students = new Student[10];
        students[0] = new Student("Draven Reyer", 609377);
        students[1] = new Student("Slavica Teke", 160610);
        students[2] = new Student("Alexandra Reis", 901211);
        students[3] = new Student("Draven Reyer", 235054);
        students[4] = new Student("Pallav Ahearn", 319131);
        students[5] = new Student("Pallav McQueen", 531242);
        students[6] = new Student("Victorius Wortham", 902373);
        students[7] = new Student("Alexandra Reis", 234628);
        students[8] = new Student("Gaios Best", 131537);
        students[9] = new Student("Wigbrand Spalding", 704970);

        System.out.println(Arrays.toString(students));
    }
}
