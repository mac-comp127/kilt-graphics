package sen.animals2;

import java.util.Arrays;

public class AnimalMain {
    public static void main(String [] args) {
        Animal [] pets = new Animal[6];
        pets[0] = new Dog("fido", "brown");;
        pets[1] = new Cat("tom", "gray");
        pets[2] = new Dog("clifford", "red");;
        pets[3] = new Cat("max", "orange");
        pets[4] = new Cat("fido", "black");;
        pets[5] = new Cat("clifford", "gray");

        Arrays.sort(pets);
        for (Animal p : pets) {
            System.out.println("pet  " + p.getName() + " says " + p.vocalize());
        }
    }
}
