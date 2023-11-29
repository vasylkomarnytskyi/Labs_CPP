package org.example;
import java.time.LocalDate;

public class Record {
    private int recordId;
    private Book book;
    private Subscription subscription;
    private LocalDate borrowedDate;
    private LocalDate dueDate;
    private LocalDate returnedDate;

    public Record(int recordId, Book book, Subscription subscription, LocalDate borrowedDate, LocalDate dueDate) {
        this.recordId = recordId;
        this.book = book;
        this.subscription = subscription;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
    }

    public int getRecordId() {
        return this.recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Subscription getSubscription() {
        return this.subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public LocalDate getBorrowedDate() {
        return this.borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }


    public LocalDate getReturnedDate() {
        return this.returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }
}
