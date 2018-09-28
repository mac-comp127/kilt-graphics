package kluver.section04.GreeterExample;

import java.util.Scanner;

/**
 * Created by dkluver on 9/27/17.
 */
public class RussianGreeter extends Greeter {
    public RussianGreeter(String name) {
        super(name);
    }

    @Override
    public String getUserName(Scanner scanner) {
        System.out.println("Здравствуй. Как вас зовут?");
        return scanner.nextLine();
    }

    @Override
    public void greet(String userName) {
        System.out.println("Здравствуй, "+userName+" меня зовут "+ getMyName());
    }
}
