package org.example;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

public class Library implements Serializable {
    public List<Book> allBooks;
    private List<Subscription> allSubscriptions;

    public Library(List<Book> allBooks, List<Subscription> allSubscriptions) {
        this.allBooks = new ArrayList<>(allBooks);
        this.allSubscriptions = new ArrayList<>(allSubscriptions);
    }

    public List<Book> getAllBooks() {
        return allBooks;
    }

    public void setAllBooks(List<Book> allBooks) {
        this.allBooks = allBooks;
    }

    public List<Subscription> getAllSubscriptions() {
        return allSubscriptions;
    }

    public void setAllSubscriptions(List<Subscription> allSubscriptions) {
        this.allSubscriptions = allSubscriptions;
    }

    public void addBook(Book book) {
        if (allBooks == null) {
            allBooks = new ArrayList<>();
        }
        allBooks.add(book);
    }

    public void removeBook(Book book) {
        if (allBooks != null) {
            allBooks.remove(book);
        }
    }

    public void addSubscription(Subscription subscription) {
        if (allSubscriptions == null) {
            allSubscriptions = new ArrayList<>();
        }
        allSubscriptions.add(subscription);
    }

    public void removeSubscription(Subscription subscription) {
        if (allSubscriptions != null) {
            allSubscriptions.remove(subscription);
        }
    }
}
