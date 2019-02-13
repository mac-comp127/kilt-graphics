package marsh.classes;

public class Main {

    public static void main(String[] args) {

        // Creating instances of a class and its subclass
        Person Abby = new Person();
        Abby.setName("Abby");

        Student Dat = new Student();
        Dat.setName("Dat");

//        Scanner scan = new Scanner(System.in);
//        System.out.print("Enter Abby's phone number: ");
//        String phoneNumber = scan.next();

        //Abby.setPhoneNumber(phoneNumber);
        //Abby.phoneNumber = phoneNumber;
        //System.out.println("Abby's area code is " + Abby.getAreaCode());

        System.out.println("Object's name is " + Abby.getName());
        System.out.println("Object's name is " + Dat.getName());

    }
}
