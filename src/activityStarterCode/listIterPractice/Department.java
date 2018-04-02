package activityStarterCode.listIterPractice;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a department on campus. It holds a list of enrolled students who have declared the corresponding major.
 * Created by bjackson on 3/9/2016.
 */
public class Department implements Comparable<Department> {

    private List<Student> enrolledStudents;
    private String departmentName;

    public Department(String departmentName){
        this.departmentName = departmentName;
        enrolledStudents = new ArrayList<>();
    }

    /**
     * Enrolls a student in the department. I.e. the student has declared a major.
     * @param student
     */
    public void enroll(Student student){
        enrolledStudents.add(student);
    }

    /**
     * Creates a student object with name and id and enrolls them in the department.
     * @param studentName
     * @param id
     */
    public void enroll(String studentName, int id){
        enrolledStudents.add(new Student(studentName, id));
    }

    /**
     * Getter for the list of enrolled students.
     * @return enrolled students.
     */
    public List<Student> getEnrolledStudents(){
        return enrolledStudents;
    }

    /**
     * Removes the senior students from the list of enrolled students.
     */
    public void removeSeniors(){
        //TODO: remove all the seniors form the list of enrolled students
    }

    /**
     * Order departments alphabetically based on their name.
     * @param dept
     * @return
     */
    @Override
    public int compareTo(Department dept) {
        return departmentName.compareTo(dept.departmentName);
    }

    /**
     * Overridden string representation the department.
     * @return
     */
    @Override
    public String toString() {
        return "Department: "+departmentName+"\nEnrolled students: "+enrolledStudents;
    }

    /**
     * Used to test whether two Department objects are equal.
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj != null && obj instanceof Department){
            Department dept = (Department)obj;
            result = departmentName.equals(dept.departmentName) && enrolledStudents.equals(dept.enrolledStudents);
        }
        return result;
    }
}

