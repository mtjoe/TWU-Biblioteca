package com.twu.biblioteca;

/**
 * Created by marisatjoe on 4/06/15.
 */
public class Inventory {
    String title;
    int year;
    boolean checkedout;
    User lastBorrowedBy;

    public Inventory(String name, int year) {
        this.title = name;
        this.year = year;
        this.checkedout = false;
        this.lastBorrowedBy = null;
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

    public void checkoutBook(User user) {
        this.checkedout = true;
        this.lastBorrowedBy = user;
    }

    public void returnBook() {
        this.checkedout = false;
    }

    public User getLastBorrowedBy() {
        return this.lastBorrowedBy;
    }
}
