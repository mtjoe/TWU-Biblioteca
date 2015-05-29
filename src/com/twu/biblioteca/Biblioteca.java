package com.twu.biblioteca;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by marisatjoe on 27/05/15.
 */
public class Biblioteca {
    List<Book> books;
    String[] mainMenu = new String[] {"List Books", "Checkout", "Quit"};
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

    public void listBooks() {
        for (int i=0; i<books.size(); i++) {
            System.out.print(i + " - " + books.get(i).toString());
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
                    listBooks();
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
            books.get(n).checkedout = true;
            Book book = books.remove(n);
            System.out.println("Thank you! Enjoy the book");
            return book;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That book is not available.");
            return null;
        }
    }

    /* GETTER METHODS  */

    public List<Book> getBooks() {
        return books;
    }

}
