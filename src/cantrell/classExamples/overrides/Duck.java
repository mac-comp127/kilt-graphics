package cantrell.classExamples.overrides;

public class Duck {

    public static Duck giveMeADuck() {
        return new Duck();
    }

    public void quack() {
        System.out.println("Quack.");
    }

    public void waddle() {
        System.out.println("waddle waddle waddle");
    }

}

