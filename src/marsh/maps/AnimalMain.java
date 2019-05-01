package marsh.maps;

import java.util.HashMap;
import java.util.Map;

public class AnimalMain {
    public static void main(String [] args) {

        // TreeMap backing for the pet Map
        //Map<String, Animal> pet = new TreeMap<>(new StringLengthComparator());

//        Map<String, Animal> pet = new HashMap<>();
//
//        pet.put("fido", new Dog("fido", "brown"));
//        pet.put("tom", new Cat("tom", "grey"));
//        pet.put("theophilus", new Dog("theophilus", "red"));
//        pet.put("frank", new Cat("frank", "black"));
//
//        for(String name: pet.keySet()) {
//            System.out.println(name + " is a: " + pet.get(name));
//
//        }


//         Experimenting with a Animals as keys
        Map<Animal, Integer> reversePets = new HashMap<>();

        Animal fido = new Dog("fido", "brown");
        reversePets.put(new Cat("hank", "grey"), 3);
        reversePets.put(new Cat("snowball II", "black"), 5);
        reversePets.put(fido, 15);
        reversePets.put(new Cat("daisy", "white"), 9);
        reversePets.put(new Cat("harry", "orange"), 12);

        fido.setName("FIDO!!");

        System.out.println("Iterating through Keys: ");
        for(Animal a: reversePets.keySet()) {
            System.out.println(a + "'s age: " + reversePets.get(a));
        }

        System.out.println("\nIterating through Entries: ");
        for(Map.Entry<Animal, Integer> e : reversePets.entrySet() ) {
            System.out.println(e.getKey() + "'s age: " + e.getValue());
        }

    }
}
