package com.twu.biblioteca;

/**
 * Created by marisatjoe on 4/06/15.
 */
public class Movie extends Inventory {
    String director;
    double rating;

    public Movie(String name, int year, String director, double rating) {
        super(name, year);
        this.director = director;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return this.title + " | " + year + " | " + director + " | " + rating + "\n";
    }
}
