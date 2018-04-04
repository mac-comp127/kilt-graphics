package cantrell.classExamples;

public class Name {

    public static void main(String[] args) {
        Name one = new Name("Sally", "Smith");
        Name two = new Name("Sally", "Smith");
        Name three = new Name("Sally", "Smythe");
        System.out.println("should be true: " + one.equals(two));
        System.out.println("should be false: " + one.equals("Sally Smith"));
        System.out.println("should be false: " + one.equals(null));
        System.out.println("should be false: " + one.equals(three));
    }

    private String first, last;

    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    @Override
    public final boolean equals(Object obj) {
        if(!(obj instanceof Name))
            return false;

        Name that = (Name) obj;

        return this.first.equals(that.first)
            && this.last.equals(that.last);
    }

    // ************* also implement hashCode() ******************
}
