package javaFoundations4thEd.ch8.messages;//********************************************************************
//  Messages.java       Java Foundations
//
//  Demonstrates the use of an overridden method.
//********************************************************************

public class Messages
{
    //-----------------------------------------------------------------
    //  Creates two objects and invokes the message method in each.
    //-----------------------------------------------------------------
    public static void main(String[] args)
    {
        Thought parked = new Thought();
        Advice dates = new Advice();

        parked.message();

        dates.message();  // overridden
    }
}
