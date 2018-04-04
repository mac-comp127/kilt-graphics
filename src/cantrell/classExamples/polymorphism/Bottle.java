package cantrell.classExamples.polymorphism;

public class Bottle implements HandheldItem {
    private boolean closed;

    public boolean isClosed() {
        return closed;
    }

    @Override
    public boolean isSafeForTransport() {
        return isClosed();
    }
}
