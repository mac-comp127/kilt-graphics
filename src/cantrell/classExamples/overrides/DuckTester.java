package cantrell.classExamples.overrides;

public class DuckTester {
    public static void main(String[] args) {
        Duck sally = new UltraFancyDuck();
        Duck billy = new AwkwardDuck();

        sally.waddle();
        sally.quack();
        sally.quack();
        billy.waddle();
        billy.quack();
    }
}
