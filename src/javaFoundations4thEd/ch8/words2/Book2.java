package javaFoundations4thEd.ch8.words2;//********************************************************************
//  Book2.java       Java Foundations
//
//  Represents a book. Used as the parent of a derived class to
//  demonstrate inheritance and the use of the super reference.
//********************************************************************

public class Book2
{
    protected int pages;

    //----------------------------------------------------------------
    //  Constructor: Sets up the book with the specified number of
    //  pages.
    //----------------------------------------------------------------
    public Book2(int numPages)
    {
        pages = numPages;
    }

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
