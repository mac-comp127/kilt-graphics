package sen.basicjava;

public class HelloWorld {
    public static void main(String args[]) {
        String name = "Stella";
        System.out.println("Hello, " + name + "!");
        double age = 6.0;
        System.out.println("Your age is " + age);
        age++;
        System.out.println("Next year you will be " + age);
        age += 3;
        System.out.println("Three years after that, you will be " + age);
    }
}
