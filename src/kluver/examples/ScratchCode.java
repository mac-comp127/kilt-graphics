package kluver.examples;

public class ScratchCode {
    private static final int TOMATO = 3;

    public static void main(String[] args) {
        if (tacoNumber(2) < 4) {
            System.out.println("too few tacos!");
        } else if (tacoNumber(2) == 6) {
            System.out.println("6 tacos");
        } else if (tacoNumber(2) <= 5) {
            System.out.println("5 tacos");
        } else {

        }
    }

    public static int tacoNumber(int lettuce) {
        System.out.println("!");
        return lettuce * TOMATO;
    }
}
