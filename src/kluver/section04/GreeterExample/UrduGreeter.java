package kluver.section04.GreeterExample;

import java.util.Scanner;

/**
 * Created by dkluver on 2/13/17.
 */
public class UrduGreeter extends Greeter {
    public UrduGreeter(String name) {
        super(name);
    }

    @Override
    public String getUserName(Scanner scanner) {
        System.out.println("Aslam-o-alaikum! Aap ka kya naam hai?");
        return scanner.nextLine();
    }

    @Override
    public void greet(String userName) {
        System.out.println("Aslam-o-alaikum "+userName+" mera naam hai "+ getMyName());
    }
}
















