package kluver.section04.GreeterExample;

import java.util.Scanner;

/**
 * Created by dkluver on 2/13/17.
 */
public class EnglishGreeter extends Greeter {
    public EnglishGreeter(String name) {
        super(name);
    }

    @Override
    public String getUserName(Scanner scanner) {
        System.out.println("Hi, what is your name?");
        return scanner.nextLine();
    }

    @Override
    public void greet(String userName) {
        System.out.println("Hello, "+userName+" my name is "+ getMyName());
    }
}


















