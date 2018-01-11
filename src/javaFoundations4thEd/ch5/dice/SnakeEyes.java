package javaFoundations4thEd.ch5.dice;//********************************************************************
//  SnakeEyes.java       Java Foundations
//
//  Demonstrates the use of a programmer-defined class.
//********************************************************************

public class SnakeEyes
{
	//-----------------------------------------------------------------
	//  Creates two Die objects and rolls them several times, counting
	//  the number of snake eyes that occur.
	//-----------------------------------------------------------------
	public static void main (String[] args)
	{
		final int ROLLS = 500;
		int num1, num2, count = 0;

		Die die1 = new Die();
		Die die2 = new Die();

		for (int roll = 1; roll <= ROLLS; roll++)
		{
			num1 = die1.roll();
			num2 = die2.roll();

			if (num1 == 1 && num2 == 1)  // check for snake eyes
				count++;
		}

		System.out.println ("Number of rolls: " + ROLLS);
		System.out.println ("Number of snake eyes: " + count);
		System.out.println ("Ratio: " + (float)count / ROLLS);
	}
}
