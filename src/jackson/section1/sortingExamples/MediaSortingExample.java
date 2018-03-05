package jackson.section1.sortingExamples;

import java.util.Arrays;

public class MediaSortingExample {

    public static void main(String[] args){
        Media[] medias = new Media[3];

        medias[0] = new Media("somefilepath1", "song1", 34);
        medias[1] = new Media("somefilepath2", "song2", 10);
        medias[2] = new Media("somefilepath3", "song3", 15);
        Arrays.sort(medias);
        System.out.println(Arrays.toString(medias));
    }
}
