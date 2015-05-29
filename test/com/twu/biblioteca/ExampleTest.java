package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;


public class ExampleTest {
    Biblioteca library;
    ByteArrayOutputStream out;

    @Before
    public void setUp() {
        library = new Biblioteca();

        // For testing print statements
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    public void testMenuResponseError() {
        library.runMenuItem(library.mainMenu.length);
        String printed = new String(out.toByteArray());

        assertEquals(printed, "Select a valid option!\n\n");
    }

    @Test
    public void testMenuListBooks() throws IOException {
        library.listBooks();
        String printed = new String(out.toByteArray());

        String[] output = printed.split("\n");
        assertEquals(library.getBooks().size(), output.length);
    }

    @Test
    public void testBookDetails() throws IOException {
        library.listBooks();
        String printed = new String(out.toByteArray());

        String[] output = printed.split("\n");

        if (output.length > 0) {
            Book book = library.getBooks().get(0);
            String bookDetails = output[0].split(" \\- ", 2)[1];
            String[] bookOut = bookDetails.split(" \\| ");

            assertEquals(bookOut[0], book.getTitle());
            assertEquals(bookOut[1], book.getAuthor());
            assertEquals(bookOut[2], Integer.toString(book.getYear()));
        }
    }

    @Test
    public void testCheckOut() {
        Book book = library.checkout(0);
        String printed = new String(out.toByteArray());

        assertEquals(printed, "Thank you! Enjoy the book\n");
        assertFalse(library.getBooks().contains(book));
        assertTrue(book.checkedout);
    }

    @Test
    public void testCheckOutFail() {
        Book book = library.checkout(library.getBooks().size());
        String printed = new String(out.toByteArray());

        assertEquals(printed, "That book is not available.\n");
    }

    @Test
    public void testReturn() {
        
    }
}
