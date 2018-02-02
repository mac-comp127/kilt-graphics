package jackson.section1.basicClasses;

/**
 * This represents a student
 * @author bjackson
 */
public class Student {
    private String name;
    private int id;
    private int numCredits;
    private boolean graduated;

    public static final int CREDITS_TO_GRAD = 42;

    /**
     * Constructs a student object
     * @param name
     * @param id a positive number
     * @param numCredits a number between 0 and 42
     */
    public Student(String name, int id, int numCredits){
        this.name = name;
        this.id = id;
        this.numCredits = numCredits;
        graduated = false;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }


    public int getNumCredits(){
        return numCredits;
    }

    /**
     * Sets the number of credits and also updates whether the student h
     * @param newValue
     */
    public void setNumCredits(int newValue){
        if (newValue < 0){
            System.out.println("Credits must be positive");
            return;
        }
        numCredits = newValue;
        if (numCredits > CREDITS_TO_GRAD){
            graduated = true;
        }
    }




}
