package javaFoundations4thEd.ch8.foodAnalyzer;//********************************************************************
//  FoodItem.java       Java Foundations
//
//  Represents an item of food. Used as the parent of a derived class
//  to demonstrate indirect referencing.
//********************************************************************

public class FoodItem
{
    final private int CALORIES_PER_GRAM = 9;
    private int fatGrams;
    protected int servings;

    //-----------------------------------------------------------------
    //  Sets up this food item with the specified number of fat grams
    //  and number of servings.
    //-----------------------------------------------------------------
    public FoodItem(int numFatGrams, int numServings)
    {
        fatGrams = numFatGrams;
        servings = numServings;
    }

    //-----------------------------------------------------------------
    //  Computes and returns the number of calories in this food item
    //  due to fat.
    //-----------------------------------------------------------------
    private int calories()
    {
        return fatGrams * CALORIES_PER_GRAM;
    }

    //-----------------------------------------------------------------
    //  Computes and returns the number of fat calories per serving.
    //-----------------------------------------------------------------
    public int caloriesPerServing()
    {
        return (calories() / servings);
    }
}
