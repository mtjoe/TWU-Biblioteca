package com.twu.biblioteca;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by marisatjoe on 27/05/15.
 */
public class Biblioteca {
    List<Book> books;
    String[] mainMenu = new String[] {"List Books", "Quit"};
    Scanner in;


    public Biblioteca() {
        in = new Scanner(System.in);

        // Initialize books
        books = new ArrayList<Book>() {
            {
                add(new Book("1984", "George Orwell", 1948));
                add(new Book("1984", "George Orwell", 1948));
                add(new Book("1984", "George Orwell", 1948));
            }
        };
    }

    public void printGreetings() {
        System.out.println("Hello, Welcome to the Biblioteca!");
    }

    public void listBooks() {
        for (Book book: books) {
            System.out.println(book.toString());
        }
    }

    public List<Book> getBooks() {
        return books;
    }
}
