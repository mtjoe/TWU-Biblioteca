package com.twu.biblioteca;

/**
 * Created by marisatjoe on 27/05/15.
 */
public class Book {
    private String name;
    private String author;
    private int year;

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    @Override
    public String toString() {
        return name + ", by " + author + " (" + year + ")\n";
    }
}
