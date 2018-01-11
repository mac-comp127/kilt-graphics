package javaFoundations4thEd.ch8.words;//********************************************************************
//  Book.java       Java Foundations
//
//  Represents a book. Used as the parent of a derived class to
//  demonstrate inheritance.
//********************************************************************

public class Book
{
    protected int pages = 1500;

    //----------------------------------------------------------------
    //  Pages mutator.
    //----------------------------------------------------------------
    public void setPages(int numPages)
    {
        pages = numPages;
    }

    //----------------------------------------------------------------
    //  Pages accessor.
    //----------------------------------------------------------------
    public int getPages()
    {
        return pages;
    }
}
