package sen.animals3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AnimalMain {
    public static void main(String [] args) {
        List<Animal> pets = new ArrayList<>();
        pets.add(new Dog("fido", "brown"));
        pets.add(new Cat("tom", "gray"));
        pets.add(new Dog("clifford", "red"));
        pets.add(new Cat("max", "orange"));
        pets.add(new Cat("fido", "black"));
        pets.add(new Cat("clifford", "gray"));

        Collections.sort(pets);
        for (Animal p : pets) {
            System.out.println("pet  " + p.getName() + " says " + p.vocalize());
        }
    }
}
