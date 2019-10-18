package marsh.inheritance2;

public class A {

    private int x;

    public A(int x) {
        this.x = x;
    }

    public int foo() {
        return this.x - 2;
    }

    public int getX() {
        return this.x;
    }

    public static int add2(int x) {
        return x + 2;
    }
}
