package org.example;
import java.util.List;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Library library = LibraryManager.fillLibrarywithBooks();

        LibrarySerialize.serialize(library);

        Subscription subscription1 = new Subscription("Ivan", "Ivanenko", "i.ivanenko@gmail.com", new Reader());
        Subscription subscription2 = new Subscription("Taras", "Tararsenko", "taras.tarasenko@gmail.com", new Reader());
        Subscription subscription3 = new Subscription("Stepan", "Hiha", "shiha.com", new Reader());
        library.addSubscription(subscription1);
        library.addSubscription(subscription2);
        library.addSubscription(subscription3);

        System.out.println("\nSorting books by year of publication by descending:\n");
        List<Book> sortedListBooks = LibraryManager.booksSortingByPublicationYear(library);
        for (Book book : sortedListBooks) {
            System.out.println(book);
        }

        Admin.registerBookCheckout(library, 1, library.getAllBooks().get(0), subscription1, LocalDate.of(2023, 11, 20), LocalDate.of(2023, 11, 28));
        Admin.registerBookCheckout(library, 2, library.getAllBooks().get(0), subscription1, LocalDate.of(2023, 11, 25), LocalDate.of(2023, 12, 2));
        Admin.registerBookCheckout(library, 3, library.getAllBooks().get(0), subscription2, LocalDate.of(2023, 10, 28), LocalDate.of(2023, 11, 10));
        Admin.registerBookCheckout(library, 4, library.getAllBooks().get(0), subscription3, LocalDate.of(2023, 11, 15), LocalDate.of(2023, 12, 10));
        Admin.registerBookCheckout(library, 5, library.getAllBooks().get(0), subscription2, LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 30));
        Admin.registerBookCheckout(library, 6, library.getAllBooks().get(0), subscription1, LocalDate.of(2023, 11, 14), LocalDate.of(2023, 11, 29));
        Admin.registerBookCheckout(library, 7, library.getAllBooks().get(0), subscription2, LocalDate.of(2023, 11, 20), LocalDate.of(2023, 12, 20));
        Admin.registerBookCheckout(library, 8, library.getAllBooks().get(0), subscription3, LocalDate.of(2023, 11, 11), LocalDate.of(2023, 12, 1));
        Admin.registerBookCheckout(library, 8, library.getAllBooks().get(0), subscription1, LocalDate.of(2023, 11, 20), LocalDate.of(2023, 11, 29));

        System.out.println("\n\nList of addresses for sending messages to readers who have taken more than 2 books\n");

        LibraryManager.printReadersWithMultipleBooks(library);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the author's last name: ");
        String authorSurname = scanner.nextLine();

        LibraryManager.countBooksTakenByAuthor(library,authorSurname);

        System.out.println("\nThe largest number of books taken by the library reader: ");
        LibraryManager.findMaxBooksTakenByReader(library);

        System.out.println("\nNotifications for every type readers\n");
        LibraryManager.sendNotifications(library);


        System.out.println("\nNotification debtors for returning taken books: \n");
        LibraryManager.informDebtors(library);
    }
}