package cantrell.classExamples.polymorphism;

public class Book implements HandheldItem {
    @Override
    public boolean isSafeForTransport() {
        return true;
    }
}
