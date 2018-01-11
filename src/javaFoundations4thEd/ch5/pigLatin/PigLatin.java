package javaFoundations4thEd.ch5.pigLatin;//********************************************************************
//  PigLatin.java       Java Foundations
//
//  Demonstrates the concept of method decomposition.
//********************************************************************

import java.util.Scanner;

public class PigLatin
{
    //-----------------------------------------------------------------
    //  Reads sentences and translates them into Pig Latin. 
    //-----------------------------------------------------------------
    public static void main(String[] args)
    {
        String sentence, result, another;

        Scanner scan = new Scanner(System.in);

        do
        {
            System.out.println();
            System.out.println("Enter a sentence (no punctuation):");
            sentence = scan.nextLine();

            System.out.println();
            result = PigLatinTranslator.translate(sentence);
            System.out.println("That sentence in Pig Latin is:");
            System.out.println(result);

            System.out.println();
            System.out.print("Translate another sentence (y/n)? ");
            another = scan.nextLine();
        }
        while (another.equalsIgnoreCase("y"));
    }
}
