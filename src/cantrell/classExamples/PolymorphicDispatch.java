package cantrell.classExamples;

public class PolymorphicDispatch {
    public static void main(String[] args) {
        A a = new A();
        a.sayHello();

        A bob = new C();
        bob.sayHello();
    }
}

class A {
    void sayHello() {
        System.out.println("I am A!");
    }
}

class B extends A {
    void sayHello() {
        System.out.println("I am B!");
    }
}

class C extends A {
    void sayHello() {
        System.out.println("I am totally an A. No, really. Don't believe your lying eyes.");
    }
}
