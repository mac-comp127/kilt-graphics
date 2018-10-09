package cantrell.classExamples.overrides;

public class DuckTester {
    public static void main(String[] args) {

        Duck someRandomDuck = Duck.giveMeADuck();

        Duck sally = new UltraFancyDuck();
        Duck billy = new AwkwardDuck();

        sally.waddle();
        sally.quack();
        sally.quack();
        billy.waddle();
        billy.quack();
    }
}
