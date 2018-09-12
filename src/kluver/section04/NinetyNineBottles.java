package kluver.section04;

public class NinetyNineBottles {

    public static void main(String[] args) {
        for (int numBottles = 99; numBottles > -1; numBottles --) {
            System.out.println(numBottles+" beer on the wall");
            System.out.println(numBottles+" beer");
            System.out.println("you take one down, you pass it around");
            System.out.println((numBottles-1)+" beer on the wall");
        }
    }
}
