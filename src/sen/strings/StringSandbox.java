package sen.strings;

public class StringSandbox {
    public static void main(String args[]) {
        String message = "Hello, world!";
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            System.out.println("Letter is " + c);
        }
        System.out.println("A minus a: " + ('A' - 'a'));
    }
}
