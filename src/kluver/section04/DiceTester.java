package kluver.section04;

import comp124graphics.CanvasWindow;
import comp124graphics.GraphicsText;
import comp124graphics.Rectangle;

import java.awt.*;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class DiceTester {
    private String fileName;
    private int[] counts;

    public DiceTester(String fileName) {
        this.fileName = fileName;
        this.counts = count(fileName);
    }

    private int[] count(String fileName) {
        InputStream is = openResource(fileName);
        Scanner scan = new Scanner(is);

        int[] retVal = new int[6];
        // assume retVal is a bunch of zeros.

        while(scan.hasNextInt()) {
            int next = scan.nextInt();
            retVal[next-1] = retVal[next-1]+1;
        }
        return retVal;
    }

    private CanvasWindow plot() {
        CanvasWindow cw = new CanvasWindow("plot", 600, 600);
        for(int i = 0; i<counts.length; i++) {
            int width=100;
            int height = counts[i]/2;
            int x = i*100;
            int y = cw.getHeight()-height;
            Rectangle rect = new Rectangle(x,y,width,height);
            rect.setFillColor(Color.lightGray);
            rect.setFilled(true);
            cw.add(rect);
        }

        GraphicsText gt = new GraphicsText(this.fileName, 0, 10);
        cw.add(gt);

        return cw;
    }

    /**
     * Helper function for reading from a file. There are a lot of methods and
     * classes involved in java input/output. In particular there is the File
     * class that can be used to talk about files on the disk, which can then
     * be opened with various methods or classes like FileInputStream. However,
     * for our purposes, it makes more sense to use java's "resource" system to
     * read the file from a marked reasource folder. This let's intellj deal with
     * issues between your sytem and mine.
     *
     * @param fileName  the name of the file <b> in the res folder</b> that we
     *                 want to read.
     *
     * @return an InputStream - a low level input/output (IO) class that allows
     * reading from a file. Often, reading from a file with this class is hard
     * so we typically use this class in conjunction with a Scanner for easy
     * file parsing
     */
    private InputStream openResource(String fileName) {
        return DiceTester.class.getResourceAsStream(fileName);
    }

    @Override
    public String toString() {
        return this.fileName+": "+ Arrays.toString(this.counts);
    }

    public static void main(String[] args) {
        DiceTester fair = new DiceTester("/knownFairDice.txt");
        DiceTester unknown = new DiceTester("/unknownDice.txt");

        // show the counts for each file.
        System.out.println(fair);
        System.out.println(unknown);

        // plot the counts
        fair.plot();
        unknown.plot();
    }
}
