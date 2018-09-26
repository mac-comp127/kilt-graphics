package kluver.section05;

import kluver.section04.Dog;

import java.util.Random;
import java.util.Scanner;

public class Scratch {
    public static void celebrateBirth(Dog birthdayAnimal, int newAge) {
        newAge = 17;
        birthdayAnimal.setAge(newAge);

        birthdayAnimal.feed();

    }

    public static void main(String[] args) {

        Dog gregie = new Dog("greg", 21);
        Dog theBestBoy = gregie;
        gregie.feed();

        theBestBoy = new Dog("herman", 2);
        int gregAge = 22;
        celebrateBirth(gregie, gregAge);
        // this line

    }
}
