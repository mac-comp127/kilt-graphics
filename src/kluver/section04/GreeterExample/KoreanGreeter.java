package kluver.section04.GreeterExample;

import java.util.Scanner;

/**
 * Created by dkluver on 2/13/17.
 */
public class KoreanGreeter extends Greeter {
    public KoreanGreeter(String name) {
        super(name);
    }

    @Override
    public String getUserName(Scanner scanner) {
        System.out.println("안녕하세요, 이름이 뭐에요?");
        return scanner.nextLine();
    }

    @Override
    public void greet(String userName) {
        System.out.println("안녕하세요, "+userName+" 제 이름은 "+ getMyName()+" 입니다.");
    }
}

















