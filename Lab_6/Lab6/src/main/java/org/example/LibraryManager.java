package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class LibraryManager {
    public static Library fillLibrarywithBooks()
    {
        List<Book> books = Arrays.asList(
                new Book("Shevchenko", "Kobzar", 1840),
                new Book("Gogol", "Dead Souls", 1842),
                new Book("Hemingway", "The Old Man and the Sea", 1952),
                new Book("Shevchenko", "Haidamaky", 1843),
                new Book("Gogol", "Taras Bulba", 1835),
                new Book("Hemingway", "The Old Man and the Sea", 1952),
                new Book("Hemingway", "For Whom the Bell Tolls", 1940),
                new Book("Fitzgerald", "The Great Gatsby", 1925),
                new Book("Twain", "Adventures of Huckleberry Finn", 1884),
                new Book("Salinger", "The Catcher in the Rye", 1951),
                new Book("Steinbeck", "The Grapes of Wrath", 1939),
                new Book("Lee", "To Kill a Mockingbird", 1960)
        );

        List<Subscription> subscriptions = new ArrayList<>();
        return new Library(books, subscriptions);
    }

    public static List<Book> booksSortingByPublicationYear(Library library) {
        return library.getAllBooks().stream()
                .sorted(Comparator.comparingInt(Book::getPublished))
                .collect(Collectors.toList());
    }

    public static void printReadersWithMultipleBooks(Library library) {
        library.getAllSubscriptions().stream()
                .filter(subscription -> subscription.getReader().getBorrowedBooks().size() > 2)
                .forEach(System.out::println);
    }

    public static void countBooksTakenByAuthor(Library library, String author) {
        long count = library.getAllSubscriptions().stream()
                .flatMap(subscription -> subscription.getReader().getBorrowedBooks().stream())
                .filter(book -> book.getAuthor().equals(author))
                .count();
        System.out.println("Count of books with initial author '" + author + "': " + count);
    }

    public static void findMaxBooksTakenByReader(Library library) {
        int maxBooks = library.getAllSubscriptions().stream()
                .mapToInt(subscription -> subscription.getReader().getBorrowedBooks().size())
                .max()
                .orElse(0);
        System.out.println("Max books count taken by one reader: " + maxBooks);
    }

    public static void sendNotifications(Library library) {
        library.getAllSubscriptions().forEach(subscription -> {
            List<Record> debtorRecords = getDebtorRecords(subscription, library);

            if (debtorRecords.isEmpty()) {
                System.out.println("Send notification to " + subscription.getEmail() +
                        ": Check out the latest library additions!");

                // Вивести доступні книги
                System.out.println("View available books: ");
                library.getAllBooks().forEach(book -> {
                    System.out.println(book.getAuthor() + " - " + book.getName());
                });
            } else {
                System.out.println("Send notification to " + subscription.getEmail() +
                        ": Remember to return your borrowed books on time.");

                if (!debtorRecords.isEmpty()) {
                    System.out.println("Please return the following borrowed books by the due date:");
                    debtorRecords.forEach(record -> {
                        System.out.println(record.getBook().getName() +
                                " (Due date: " + record.getDueDate() + ")");
                    });
                }
            }
        });
    }

    private static List<Record> getDebtorRecords(Subscription subscription, Library library) {
        LocalDate currentDate = LocalDate.now();
        return subscription.getReader().getBorrowedBooks().stream()
                .flatMap(book -> Admin.getRecords().stream()
                        .filter(record -> record.getBook().equals(book)
                                && record.getSubscription().equals(subscription)
                                && record.getDueDate().isBefore(currentDate)))
                .collect(Collectors.toList());
    }


    public static void informDebtors(Library library) {
        library.getAllSubscriptions().forEach(subscription -> {
            List<Record> debtorRecords = getDebtorRecords(subscription, library);
            if (!debtorRecords.isEmpty()) {
                System.out.println("Send notification to " + subscription.getEmail() +
                        ": Please return the following borrowed books by the due date:");
                debtorRecords.forEach(record -> {
                    System.out.println(record.getBook().getName() +
                            " (Due date: " + record.getDueDate() + ")");
                });
            }
        });
    }
}
