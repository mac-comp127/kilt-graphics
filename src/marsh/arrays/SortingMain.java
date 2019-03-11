package marsh.arrays;

import java.util.Arrays;

public class SortingMain {

    public static void main(String[] args) {

        int[] i = new int[5];

        i[0] = 1;
        i[1] = 4;
        i[2] = 3;
        i[3] = 2;
        i[4] = 5;

        System.out.println(Arrays.toString(i));

        Arrays.sort(i);

        System.out.println(Arrays.toString(i));


        Horse[] horseArray = new Horse[5];

        horseArray[0] = new Horse("Harry");
        horseArray[1] = new Horse("Harriet");
        horseArray[2] = new Horse("Henry");
        horseArray[3] = new Horse("Harold");
        horseArray[4] = new Horse("Garfield");


        System.out.println(Arrays.toString(horseArray));

        Arrays.sort(horseArray);

        System.out.println(Arrays.toString(horseArray));

    }
}
