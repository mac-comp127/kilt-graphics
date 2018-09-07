package cantrell.classExamples;

public class StaticTypes {

    public static void main(String[] args) {
        // In Java, we have to _declare_ a variable before we can use it.
        // The declaration includes both a type and a name:

        String argleflargle;

        // The type declaration means “argleflargle will ALWAYS be a String.”
        // Java will tell us if it isn’t — even before our code runs!
        //
        // Java is a ••statically typed•• language.

        argleflargle = "I can just make up names";
        argleflargle = "It can change";
        //argleflargle = 7;  // uncomment this line to cause a compile error

        // By contrast, in a ••dynamically typed•• language, the type of a variable
        // _can_ change. For example, this is legal in Python:

        /*
        argleflargle = "I can just make up names"
        argleflargle = 7
        */

        // Furthermore, in Java, the second call the method below will fail. Java refuses to even
        // try run the program at all, because it knows the argument passed to onePlus() must
        // _always_ be an int, and a String is not an int:

        System.out.println(onePlus(3));
        //System.out.println(onePlus("fish"));  // uncomment this line to cause a compile error

        // However, Python is happy to run the equivalent program! The error caused by the
        // int/string mismatch doesn't happen until the running program actually gets to the line
        // where it tries to do the invalid addition. If you asked Python to run the following code,
        // you would see it print 4 from the first call to one_plus, _then_ print the error after:

        /*
        def one_plus(x):
            return 1 + x

        print(one_plus(3))
        print(one_plus("fish"))
        */
    }

    static int onePlus(int x) {
        return 1 + x;
    }

}
