package kluver.section04.GreeterExample;

import java.util.Scanner;

/**
 * Created by dkluver on 2/13/17.
 */
public class FrenchGreeter extends Greeter {
    public FrenchGreeter(String name) {
        super(name);
    }

    @Override
    public String getUserName(Scanner scanner) {
        System.out.println("Bonjour! Comment tu t'appelle?");
        return scanner.nextLine();
    }

    @Override
    public void greet(String userName) {
        System.out.println("Bonjour, "+userName+" je m'appelle "+ getMyName());
    }
}
















