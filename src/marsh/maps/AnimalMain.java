package marsh.maps;

import java.util.Map;
import java.util.TreeMap;

public class AnimalMain {
    public static void main(String [] args) {

        Map<String, Animal> pet = new TreeMap<>();

        pet.put("fido", new Dog("fido", "brown"));
        pet.put("tom", new Cat("tom", "grey"));
        pet.put("hank", new Dog("hank", "red"));
        pet.put("frank", new Cat("frank", "black"));

        for(String name : pet.keySet()) {
            System.out.println(name + " is a: " + pet.get(name));

        }

    }
}
