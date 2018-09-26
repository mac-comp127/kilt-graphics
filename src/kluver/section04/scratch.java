package kluver.section04;

import java.util.Random;
import java.util.Scanner;

public class scratch {
    public static void celebrateBirth(Dog theBestDog, int newAge) {
        theBestDog.setAge(newAge);
        System.out.println("lets have a party "+theBestDog.getName());

    }

    public static void main(String[] args) {
        Dog dougie = new Dog("Doug", 21);
        Dog goodGirl = dougie;
        dougie.feed();
        goodGirl = new Dog("freddie", 3);
        dougie = new Dog ("Max", 30);

        int newAge = 31;
        celebrateBirth(dougie, newAge);

    }
}
