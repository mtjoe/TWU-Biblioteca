package com.twu.biblioteca;

/**
 * Created by marisatjoe on 27/05/15.
 */
public class Book extends Inventory {
    String author;

    public Book(String name, String author, int year) {
        super(name, year);
        this.author = author;
    }

    public String getAuthor() {
        return this.author;
    }

    @Override
    public String toString() {
        return this.title + " | " + author + " | " + year + "\n";
    }
}
