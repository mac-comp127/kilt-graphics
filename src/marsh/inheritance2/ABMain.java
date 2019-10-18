package marsh.inheritance2;

public class ABMain {

    public static void main(String[] args) {
        A a = new A(5);
        B b = new B(10);

        System.out.println(a.getX());
        System.out.println(a.foo());
        System.out.println(b.foo());
        System.out.println(b.bar());
        System.out.println(b.getX());

        System.out.println(Math.PI);
    }
}
