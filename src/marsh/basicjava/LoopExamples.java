package marsh.basicjava;

public class LoopExamples {

    public static void main(String args[]) {

        int x = 0;
        if (x == 0) {
            // do something
        }

        // while loop
        while ( x < 10 ) {
            System.out.print(x);
            x++;
        }

        x = 0;
        System.out.println("");

        // do ... while loop
        do {
            System.out.print(x);
            x++;
        } while (x < 10);

        System.out.println("");

        // for loop
        for(int i = 0; i < 10; i++) {
            System.out.print(i);
        }
    }
}

