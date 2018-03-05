package activityStarterCode.sortingStudents;

/**
 * The Student class keeps track of the following pieces of data
 * about a student: the student's name, ID number, the number of
 * credits the student has earned toward graduation, and whether
 * the student is paid up with respect to university bills.
 * All of this information is entirely private to the class.
 * Clients can obtain this data only by using the various methods
 * defined by the class.
 */

public class Student {
    /* Private instance variables */
    private String studentName;   /* The student's name           */
    private int studentID;        /* The student's ID number      */
    private double creditsEarned; /* The number of credits earned */
    private boolean paidUp;       /* Whether student is paid up   */

	/* Public constants */

    /** The number of credits required for graduation */
    public static final double CREDITS_TO_GRADUATE = 128.0;

    /**
     * Creates a new Student object with the specified name and ID.
     * @param name The student's name as a String
     * @param id The student's ID number as an int
     */
    public Student(String name, int id) {
        studentName = name;
        studentID = id;
    }

    /**
     * Gets the name of this student.
     * @return The name of this student
     */
    public String getName() {
        return studentName;
    }

    /**
     * Gets the ID number of this student.
     * @return The ID number of this student
     */
    public int getID() {
        return studentID;
    }

    /**
     * Sets the number of credits earned.
     * @param credits The new number of credits earned
     */
    public void setCredits(double credits) {
        creditsEarned = credits;
    }

    /**
     * Gets the number of credits earned.
     * @return The number of credits this student has earned
     */
    public double getCredits() {
        return creditsEarned;
    }

    /**
     * Sets whether the student is paid up.
     * @param flag The value true or false indicating paid-up status
     */
    public void setPaidUp(boolean flag) {
        paidUp = flag;
    }

    /**
     * Returns whether the student is paid up.
     * @return Whether the student is paid up
     */
    public boolean isPaidUp() {
        return paidUp;
    }

    /**
     * Creates a string identifying this student.
     * @return The string used to display this student
     */
    @Override
    public String toString() {
        return studentName + " (#" + studentID + ")";
    }

    /**
     * Instances of Student objects are considered equal if they
     * have exactly the same name string and the same student ID.
     *
     * @param other Student object instance to compare to this one
     *
     * @return true if this instance of Student has the same name
     *          and student ID as the instance called other;
     *          false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other != null && other instanceof Student) {
            Student that = (Student) other;
            result = ((studentName.equals(that.studentName)) &&
                    (studentID == that.studentID));
        }
        return result;
    }
}
