package cantrell.classExamples.overrides;

public class UltraFancyDuck extends FancyDuck {
    public void quack() {
        super.quack();
        System.out.println("...and I repeat:");
        super.quack();
    }
}
