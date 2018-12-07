package cantrell.classExamples.hashTables;

import java.util.Objects;

public class Person {
    private String firstName, lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) &&
            Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {

        // GOLDEN RULE OF HASHCODE:
        //
        // 💖🧚🦄 If a.equals(b), then a.hashCode() == b.hashCode() 🦄🧚💖
        //
        // COROLLARY:
        //
        // 🦄🧚💖 If you override equals(), you MUST override hashCode(). 💖🧚🦄
        //
        // Note that the converse does NOT apply: some objects can — must! — have the same hash code,
        // but still not be equal. Why? Because hashCode() returns an int, so there are only so many
        // hash codes to go around. (Pigeonhole Principle FTW!)

        // This is WRONG because it is inconsistent with equals():
        //
        // return super.hashCode();
        //
        // The default hashCode() — what you get if you don't override it — gives different hashCodes
        // to different objects, even if they are equal. Yikes!

        // This is technically correct, but SLOW because everything ends up in the same bucket:
        //
        // return 3;

        // Correct and better! Even though ppl with the same first name end up in the same bucket,
        // at least they're not ALL going to end up in one bucket anymore:
        //
        // return firstName.hashCode();

        // Pretty good! Now we're taking first and last name into account:
        //
        // return firstName.hashCode() + lastName.hashCode();

        // Even better! Now we're spreading the values around more:
        //
        //return firstName.hashCode() + 37 * lastName.hashCode();

        // And guess what? Java gives us a helper method that does something very much like
        // that last line of code. This is the one to use if you just need a quick hash function:

        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}






