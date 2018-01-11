package javaFoundations4thEd.ch5.parameterModify;//********************************************************************
//  Num.java       Java Foundations
//
//  Represents a single integer as an object.
//********************************************************************

public class Num
{
    private int value;

    //-----------------------------------------------------------------
    //  Sets up the new Num object, storing an initial value.
    //-----------------------------------------------------------------
    public Num(int update)
    {
        value = update;
    }

    //-----------------------------------------------------------------
    //  Sets the stored value to the newly specified value.
    //-----------------------------------------------------------------
    public void setValue(int update)
    {
        value = update;
    }

    //-----------------------------------------------------------------
    //  Returns the stored integer value as a string.
    //-----------------------------------------------------------------
    public String toString()
    {
        return value + "";
    }
}
