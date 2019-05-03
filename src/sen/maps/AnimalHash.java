package sen.maps;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class AnimalHash {
    public static void main(String [] args) {
        System.out.println("fido's hash is " + ("fido".hashCode()));

        Map<String, Animal> pets = new HashMap<>();
        pets.put("fido", new Dog("fido", "brown"));
        pets.put("tom", new Cat("tom", "gray"));
        pets.put("clifford", new Dog("clifford", "red"));
        pets.put("max", new Cat("max", "orange"));
        pets.put("fido", new Cat("fido", "black"));
        pets.put("clifford", new Cat("clifford", "gray"));

        System.out.println("size of pets: " + pets.size());

    }
}
