package sen.expr;

public class ExpressionExamples {
    public static void main(String [] args) {
        int x = 5;
        int y = 4;
        double z = 4.0;
        System.out.println(x + y);
        System.out.println(x + z);
        System.out.println(x / y);
        System.out.println(x / z);

        int age0 = 18;
        int age1 = 21;

        System.out.println("Ages are " + age0 + " and " + age1);
        System.out.println("Sum of ages is " + age0 + age1);
        System.out.println("Sum of ages is " + (age0 + age1));
    }
}
