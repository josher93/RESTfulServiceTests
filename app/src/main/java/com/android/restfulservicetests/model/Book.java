package com.android.restfulservicetests.model;

/**
 * Created by Geovanni on 03/12/2015.
 */
public class Book
{

    public int BookId;


    public String Title;


    public String ISBN;

    public Book()
    {   }

    public Book(int BookId, String Title, String ISBN) {
        this.BookId = BookId;
        this.Title = Title;
        this.ISBN = ISBN;
    }

    public int getBookID() {
        return BookId;
    }

    public void setBookID(int bookId) {
        this.BookId = bookId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getISBN() {
        return ISBN;
    }


    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
}
