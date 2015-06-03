package com.twu.biblioteca;

/**
 * Created by marisatjoe on 4/06/15.
 */
public class Inventory {
    String title;
    int year;
    boolean checkedout;

    public Inventory(String name, int year) {
        this.title = name;
        this.year = year;
        this.checkedout = false;
    }

    @Override
    public String toString() {
        return title + " | " + year + "\n";
    }

    /* GETTER METHODS */
    public String getTitle() {
        return this.title;
    }

    public int getYear() {
        return this.year;
    }

    public boolean isCheckedout() {
        return checkedout;
    }

    public void checkoutBook() {
        this.checkedout = true;
    }

    public void returnBook() {
        this.checkedout = false;
    }
}
