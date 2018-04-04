package cantrell.classExamples.polymorphism;

public class Backpack {
    public void add(HandheldItem item) {
        if(!item.isSafeForTransport()) {
            // BIG ERROR HERE
        }
        //.....
    }
}
