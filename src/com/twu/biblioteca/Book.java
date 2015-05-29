package com.twu.biblioteca;

/**
 * Created by marisatjoe on 27/05/15.
 */
public class Book {
    private String title;
    private String author;
    private int year;
    public boolean checkedout;

    public Book(String name, String author, int year) {
        this.title = name;
        this.author = author;
        this.year = year;
        this.checkedout = false;
    }

    @Override
    public String toString() {
        return title + " | " + author + " | " + year + "\n";
    }

    /* GETTER METHODS */
    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getYear() {
        return this.year;
    }
}
