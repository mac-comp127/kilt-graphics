package cantrell.classExamples.polymorphism;

public class Mug implements HandheldItem, LiquidReceptacle {
    @Override
    public void pourIn(Liquid liquid) {
        //....
    }

    @Override
    public boolean isSafeForTransport() {
        return false;//isEmpty();
    }
}
