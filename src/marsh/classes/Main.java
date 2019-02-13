package marsh.classes;

public class Main {

    public static void main(String[] args) {


        Person Abby = new Person();
        Abby.setName("Abby");

        Student Dat = new Student();
        Dat.setName("Dat");

        System.out.println("Object's name is " + Dat.getName());
        System.out.println("Object's name is " + Abby.getName());

    }
}
