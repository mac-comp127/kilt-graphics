package javaFoundations4thEd.ch5.coin;//********************************************************************
//  Coin.java       Java Foundation
//
//  Represents a coin with two sides that can be flipped.
//********************************************************************

public class Coin
{
    private final int HEADS = 0;  // tails is 1

    private int face;  // current side showing

    //-----------------------------------------------------------------
    //  Sets up the coin by flipping it initially.
    //-----------------------------------------------------------------
    public Coin()
    {
        flip();
    }

    //-----------------------------------------------------------------
    //  Flips this coin by randomly choosing a face value.
    //-----------------------------------------------------------------
    public void flip()
    {
        face = (int) (Math.random() * 2);
    }

    //-----------------------------------------------------------------
    //  Returns true if the current face of the coin is heads.
    //-----------------------------------------------------------------
    public boolean isHeads()
    {
        return (face == HEADS);
    }

    //-----------------------------------------------------------------
    //  Returns the current face of the coin as a string.
    //-----------------------------------------------------------------
    public String toString()
    {
        return (face == HEADS) ? "Heads" : "Tails";
    }
}
