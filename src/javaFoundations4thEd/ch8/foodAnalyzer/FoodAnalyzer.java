package javaFoundations4thEd.ch8.foodAnalyzer;//********************************************************************
//  FoodAnalyzer.java       Java Foundations
//
//  Demonstrates indirect access to inherited private members.
//********************************************************************

public class FoodAnalyzer
{
    //-----------------------------------------------------------------
    //  Instantiates a Pizza object and prints its calories per
    //  serving.
    //-----------------------------------------------------------------
    public static void main(String[] args)
    {
        Pizza special = new Pizza(275);

        System.out.println("Calories per serving: " +
                           special.caloriesPerServing());
    }
}
