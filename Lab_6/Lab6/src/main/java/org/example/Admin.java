package org.example;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;

public class Admin {
    private static List<Record> records = new ArrayList<Record>();

    public static List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public static void registerBookCheckout(Library library, int recordId, Book book,
                                     Subscription subscription, LocalDate borrowedDate, LocalDate dueDate) {
        if (subscription.getReader().getBorrowedBooks().size() < Reader.getMaxNumberOfBooks()) {
            subscription.getReader().addBook(book);
            library.removeBook(book);
            records.add(new Record(recordId, book, subscription, borrowedDate, dueDate));
        }
    }

    public static void registerBookReturn(Library library, Subscription subscription,
                                   Book book, LocalDate returnedDate) {
        Optional<Record> latestRecord = records.stream()
                .filter(x -> x.getBook().equals(book))
                .max(Comparator.comparingInt(Record::getRecordId));

        latestRecord.ifPresent(record -> {
            record.setReturnedDate(returnedDate);
            library.addBook(book);
            subscription.getReader().getBorrowedBooks().remove(book);
        });
    }

    public static Record getLatestRecordByBook(Library library, Book book) {
        return records.stream()
                .filter(x -> x.getBook().equals(book))
                .max(Comparator.comparingInt(Record::getRecordId))
                .orElse(null);
    }
}
