package jackson.section1.loopExamples;

/**
 * This example shows why you should never use doubles or floats in the control line of a loop.
 * Created by bjackson on 9/7/2016.
 */
public class ForLoopWithDoubles {

    public static void main(String[] args){

        for(double x = 1.0; x <=2.0; x+= 0.1) {
            System.out.println(x);
        }
    }
}