package marsh.polymorphism;

import java.util.ArrayList;
import java.util.List;

public class InheritanceMain {

    public static void main(String[] args) {

        Wolf w = new Wolf("grey", 4);
        Dog d = new Dog("black");

        System.out.println("Dog says: " + d.getNoise());
        System.out.println("Wolf says: " + w.getNoise());

        Animal a1 = new Dog("red");
        System.out.println("Animal says: " + a1.getNoise());

        List<Animal> animals = new ArrayList<>();

        animals.add(w);
        animals.add(d);
        animals.add(a1);

        for(Animal a : animals) {
            System.out.println(a.getNoise());
        }

        List<Canine> canines = new ArrayList<>();

        canines.add(d);
        canines.add(w);

        for(Canine c : canines) {
            System.out.println("Canine color: " + c.getColor());
        }
    }
}
