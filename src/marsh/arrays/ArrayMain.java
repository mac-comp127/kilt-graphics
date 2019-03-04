package marsh.arrays;

public class ArrayMain {

    public static void main(String[] args) {
        int numbers[];

        numbers = new int[5];

        numbers[0] = 1;
        numbers[1] = 3;
        numbers[2] = 3;
        numbers[3] = 3;
        numbers[4] = 5;


        for(int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }

        Animal[] livestock = new Animal[3];

        Horse harry = new Horse("Harry");
        Cow daisy = new Cow("Daisy");

        livestock[0] = harry;
        livestock[1] = daisy;

        for(Animal a : livestock) {
            //System.out.println("Animal " + a.getName() + " says " + a.vocalize());
            System.out.println("In the livestock array");
        }

        Animal[] livestock2 = new Animal[3];

        livestock2[1] = daisy;

        System.out.println(livestock2[1].getName());




    }
}
