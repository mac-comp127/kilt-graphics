package kluver.section05.GreeterExample;

/**
 * A simple class to run the greeter objects
 */
public class GreeterRunner {
    public static void main(String[] args) {
        Greeter g = new SpanishGreeter("Douglas, the Helpful Robot");
        g.doGreeting();
    }
}
