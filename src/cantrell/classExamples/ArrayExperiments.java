package cantrell.classExamples;

import java.awt.Color;
import java.util.Arrays;

import comp124graphics.Ellipse;

/**
 * HEY CLASS
 *
 * Why did the programmer quit their job?
 *
 * .
 *
 * .
 *
 * .
 *
 * Because they just couldn't get arrays (a raise).
 */
public class ArrayExperiments {

    public static int largestInt(int[] numbers) {
        int max = numbers[0];
        for(int number : numbers) {
            if(number > max) {
                max = number;
            }
        }
        return max;
    }

    public static int largestIntUsingIndices(int[] numbers) {
        int max = numbers[0];
        for(int n = 0; n < numbers.length; n++) {
            if(numbers[n] > max) {
                max = numbers[n];
            }
        }
        return max;
    }

    public static void doubleEverything(int[] numbers) {
        for(int n = 0; n < numbers.length; n++) {
            numbers[n] *= 2;
        }
    }

    public static void doubleEverythingBrokenVersion(int[] numbers) {
        for(int number : numbers) {
            number *= 2;   // This doesn't work
        }
    }

    public static void recolorEverything(Ellipse[] ellipses) {
        for(Ellipse ellipse : ellipses) {
            ellipse.setFillColor(Color.RED);  // This DOES work. Why?
        }
    }

    public static void main(String[] args) {
        int[] fred = new int[] { -3, 5, 12 };
        doubleEverything(fred);
        System.out.println(
            Arrays.toString(fred));
    }
}











