package kluver.section04;

import java.io.InputStream;

public class DiceTester {
    private String fileName;

    public DiceTester(String fileName) {
        this.fileName = fileName;
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
     * @param fileName - the name of the file <b> in the res folder</b> that we
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

    public static void main(String[] args) {
        DiceTester fair = new DiceTester("/knownFairDice.txt");
        DiceTester unknown = new DiceTester("/unknownDice.txt");

        // show the counts for each file.
        System.out.println(fair);
        System.out.println(unknown);

        // plot the counts
    }
}
