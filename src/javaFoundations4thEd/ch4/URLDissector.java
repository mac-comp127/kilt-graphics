package javaFoundations4thEd.ch4;
//********************************************************************
//  URLDissector.java       Java Foundations
//
//  Demonstrates the use of Scanner to read file input and parse it
//  using alternative delimiters.
//
// Updated by Libby Shoop on 1/25/2017
//         to work in IntelliJ and open a file located in a folder designated as a 'resources root'
//
//********************************************************************

import java.util.Scanner;
import java.io.*;

public class URLDissector {

    //-----------------------------------------------------------------
    //  Reads urls from a file and prints their path components.
    //-----------------------------------------------------------------
    public static void main(String[] args) throws IOException
    {
        String url;
        Scanner fileScan, urlScan;

        String fileName = "websites.inp";
        URLDissector dissector = new URLDissector();

        // the convoluted way to determine the path of a file in 'res' folder,
        // which is the 'resources root' in this IntelliJ module
        String filePath = dissector.getClass().getClassLoader().getResource(fileName).getPath().replaceAll("%20", " ");
        // the replaceAll() above is needed in rare cases where there may be a space in the full path

        File file = new File(filePath);
        fileScan = new Scanner(file);

        // Read and process each line of the file
        while (fileScan.hasNext())
        {
            url = fileScan.nextLine();
            System.out.println("URL: " + url);

            urlScan = new Scanner(url);
            urlScan.useDelimiter("/");

            //  Print each part of the url
            
            while (urlScan.hasNext())
                System.out.println("   " + urlScan.next());

            System.out.println();
        }
    }
}
