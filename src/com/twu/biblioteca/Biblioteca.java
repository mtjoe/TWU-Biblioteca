package com.twu.biblioteca;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by marisatjoe on 27/05/15.
 */
public class Biblioteca {
    List<Book> books;
    String[] mainMenu = new String[] {"List Books", "Checkout Book", "Return Book", "Quit"};
    Scanner in;

    public Biblioteca() {
        in = new Scanner(System.in);

        // Initialize books
        books = new ArrayList<Book>() {
            {
                add(new Book("1984", "George Orwell", 1948));
                add(new Book("Pride & Prejudice", "Jane Austen", 1948));
            }
        };
    }

    public void printGreetings() {
        System.out.println("Hello, Welcome to the Biblioteca!");
    }

    public void listBooks(boolean checkedout) {
        for (int i=0; i<books.size(); i++) {
            if ((books.get(i).checkedout && checkedout) ||
                    (!books.get(i).checkedout && !checkedout)) {
                System.out.print(i + " - " + books.get(i).toString());
            }
        }
    }

    public void run() {
        try {
            while (true) {
                printMainMenu();
                int input = Integer.parseInt(in.nextLine());
                runMenuItem(input);
            }
        } catch (Exception e) {
            System.out.println("Oh, well...");
        }

    }

    private void printMainMenu() {
        System.out.println("Please type in the number corresponding to the options:");

        for (int i=0; i<mainMenu.length; i++) {
            System.out.println(i + " - " + mainMenu[i]);
        }
    }

    public void runMenuItem(int n) {
        if (n >= mainMenu.length) {
            System.out.println("Select a valid option!");
        } else {
            switch (mainMenu[n]) {
                case ("List Books"):
                    listBooks(false);
                    break;
                case ("Checkout Book"):
                    listBooks(false);
                    System.out.println("Please type in the number corresponding to the book you would like to checkout, or type in -1 to go back to the main menu:");
                    int input = Integer.parseInt(in.nextLine());

                    if (input == -1) return;
                    while (checkout(input) == null) {
                        System.out.println("Please type in the number corresponding to the book you would like to checkout, or type in -1 to go back to the main menu:");
                        input = Integer.parseInt(in.nextLine());
                    }
                    break;
                case ("Return Book"):
                    listBooks(true);
                    System.out.println("Please type in the number corresponding to the book you would like to return, or type in -1 to go back to the main menu:");
                    input = Integer.parseInt(in.nextLine());

                    if (input == -1) return;
                    while (returnBook(input) == null) {
                        System.out.println("Please type in the number corresponding to the book you would like to return, or type in -1 to go back to the main menu:");
                        input = Integer.parseInt(in.nextLine());
                    }
                    break;
                case ("Quit"):
                    System.exit(0);
                    break;
            }
        }
        System.out.println();
    }

    public Book checkout(int n) {
        try {
            Book book = books.get(n);

            if (!book.checkedout) {
                book.checkedout = true;
                System.out.println("Thank you! Enjoy the book");
            } else {
                throw new IndexOutOfBoundsException();
            }
            return book;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That book is not available.");
            return null;
        }
    }

    public Book returnBook(int n) {
        try {
            Book book = books.get(n);

            if (book.checkedout) {
                book.checkedout = false;
                System.out.println("Thank you for returning the book.");
            } else {
                throw new IndexOutOfBoundsException();
            }
            return book;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That is not a valid book to return.");
            return null;
        }
    }

    /* GETTER METHODS  */

    public List<Book> getBooks() {
        return books;
    }

}
