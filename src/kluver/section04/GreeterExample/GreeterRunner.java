package kluver.section04.GreeterExample;

/**
 * A simple class to run the greeter objects
 */
public class GreeterRunner {
    public static void main(String[] args) {
        Greeter g = new EnglishGreeter("Douglas, the Helpful Robot");
        g.doGreeting();
    }
}
