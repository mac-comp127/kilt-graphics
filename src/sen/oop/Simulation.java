package sen.oop;

public class Simulation {
    public static void main(String[] args) {
        Person p1 = new Person("Shilad");
        Person p2 = new Person("b");
        Person p3 = new Person("c");
        Person p4 = new Person("d");

        Course c1 = new Course("COMP", 127, 2, p1);
        c1.enroll(p2);
        c1.enroll(p3);
        c1.enroll(p4);

        c1.printClassList();
    }
}
