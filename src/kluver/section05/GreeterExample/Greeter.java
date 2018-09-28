package kluver.section05.GreeterExample;

import java.util.Scanner;

/**
 * A Class representing a basic greeting.
 */
public abstract class Greeter {

    /**
     * The name of the person greeting you
     */
    private String myName;

    public Greeter(String myName) {
        this.myName = myName;
    }

    public String getMyName() {
        return myName;
    }

    public abstract String getUserName(Scanner scanner);

    public abstract void greet(String userName);

    public void doGreeting() {
        Scanner scanner = new Scanner(System.in);
        String userName = getUserName(scanner);
        greet(userName);
    }
}













