package sen.maps;

import java.util.Map;
import java.util.TreeMap;

public class AnimalMain {
    public static void main(String [] args) {
        Map<String, Animal> pets = new TreeMap<>();
        pets.put("fido", new Dog("fido", "brown"));
        pets.put("tom", new Cat("tom", "gray"));
        pets.put("clifford", new Dog("clifford", "red"));
        pets.put("max", new Cat("max", "orange"));
        pets.put("fido", new Cat("fido", "black"));
        pets.put("clifford", new Cat("clifford", "gray"));

        System.out.println("size of pets: " + pets.size());

        for (String name : pets.keySet()) {
            System.out.println(name + " is associated with " + pets.get(name));
        }
    }
}
