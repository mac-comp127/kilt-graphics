package kluver.section04;

public class MultiplicationTable {
    /**
     * Creates a string with length 3 representing a number
     * @param number a number between 0 and 999
     * @return a string of the input number, padded with spaces
     */
    public static String formatString(int number) {
        if (number < 10) {
            return "  "+number;
        } else if (number < 100) {
            return " "+number;
        } else {
            return Integer.toString(number);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i<32; i++) {
            for (int j = 0; j<32; j++) {
                System.out.print(formatString(i*j));
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
