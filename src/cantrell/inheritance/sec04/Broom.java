package cantrell.inheritance.sec04;

public class Broom {
    public static void main(String[] args) {
        Broom a = new Broom();
        Broom b = new Stove();
        a.fly();

        b.fly();
//        b.jump();  // compile error
    }

    public void fly() {
        System.out.println("Woot I'm a broom");
    }
}

class Stove extends Broom {
    public void fly() {
        super.fly();
        System.out.println("Just kidding. I'm a stove.");
    }

    public void jump() {
        System.out.println("Hoppin’ stove here");
    }
}
