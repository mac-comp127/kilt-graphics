package javaFoundations4thEd.ch5.bank;//********************************************************************
//  Account.java       Java Foundations
//
//  Represents a bank account with basic services such as deposit
//  and withdraw.
//********************************************************************

import java.text.NumberFormat;

public class Account
{
    private final double RATE = 0.035;  // interest rate of 3.5%

    private long acctNumber;
    private double balance;
    private String name;

    //-----------------------------------------------------------------
    //  Sets up the account by defining its owner, account number,
    //  and initial balance.
    //-----------------------------------------------------------------
    public Account(String owner, long account, double initial)
    {
        name = owner;
        acctNumber = account;
        balance = initial;
    }

    //-----------------------------------------------------------------
    //  Deposits the specified amount into the account and returns
    //  the new balance. The balance is not modified if the deposit
    //  amount is invalid.
    //-----------------------------------------------------------------
    public double deposit(double amount)
    {
    	if (amount > 0)
            balance = balance + amount;
    	
        return balance;
    }

    //-----------------------------------------------------------------
    //  Withdraws the specified amount and fee from this account and
    //  returns the new balance. The balance is not modified if the
    //  withdraw amount is invalid or the balance is insufficient.
    //-----------------------------------------------------------------
    public double withdraw(double amount, double fee)
    {
    	if (amount + fee > 0 && amount + fee < balance)
            balance = balance - amount - fee;
    	
        return balance;
    }

    //-----------------------------------------------------------------
    //  Adds interest to the account and returns the new balance.
    //-----------------------------------------------------------------
    public double addInterest()
    {
        balance += (balance * RATE);
        return balance;
    }

    //-----------------------------------------------------------------
    //  Returns the current balance of this account.
    //-----------------------------------------------------------------
    public double getBalance()
    {
        return balance;
    }

    //-----------------------------------------------------------------
    //  Returns a one-line description of this account as a string.
    //-----------------------------------------------------------------
    public String toString()
    {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        
        return (acctNumber + "\t" + name + "\t" + fmt.format(balance));
    }
}
