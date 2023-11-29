package org.example;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Reader implements Serializable{
    private List<Book> borrowedBooks;
    public static final int maxNumberOfBooks = 5;

    public Reader() {
        this.borrowedBooks = new ArrayList<Book>();
    }

    public static int getMaxNumberOfBooks() {
        return maxNumberOfBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public List<Book> getBorrowedBooks() {
        return this.borrowedBooks;
    }

    public void addBook(Book book) {
        this.borrowedBooks.add(book);
    }

    @Override
    public String toString() {
        return "\nReader [" + "Taken Books: " + this.borrowedBooks + "]";
    }
}
