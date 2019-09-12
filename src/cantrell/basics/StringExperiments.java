package cantrell.basics;

public class StringExperiments {
    public static void greetEveryone() {
        String salutation = "Greetings ";
        System.out.println(salutation + "everyone!".toUpperCase());
    }

    public static void main(String[] args) {
        greetEveryone();
        greetEveryone();
        greetEveryone();

        "hello".toUpperCase();
        // toUpperCase("hello");  // Java doesn't do this

        Math.sqrt(2);
        // 2.sqrt();  // Java doesn't do this
    }
}











