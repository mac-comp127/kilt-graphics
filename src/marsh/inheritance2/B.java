package marsh.inheritance2;

public class B extends A {

    private int y;

    public B(int y) {
        super(y * 2);
        this.y = y % 2;
    }

    public int bar() {
        return super.foo() + this.y;
    }

    public int getY() {
        return this.y;
    }
}
