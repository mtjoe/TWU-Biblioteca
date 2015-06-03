package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;


public class BibliotecaTest {
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
    public void testMenuListBooks() throws IOException {
        library.listBooks(false);
        String printed = new String(out.toByteArray());

        String[] output = printed.split("\n");
        assertEquals(library.getBooks().size(), output.length);
    }

    @Test
    public void testBookDetails() throws IOException {
        library.listBooks(false);
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
    public void testCheckOutBook() {
        if (library.getBooks().size() > 0) {
            Inventory book = library.checkoutInv('b', 0);
            String printed = new String(out.toByteArray());

            assertEquals(printed, "Thank you! Enjoy the inventory\n\n");
            assertTrue(book.isCheckedout());
        }
    }

    @Test
    public void testCheckOutBookFail() {
        Inventory book = library.checkoutInv('b', library.getBooks().size());
        String printed = new String(out.toByteArray());

        assertEquals(printed, "That inventory is not available.\n\n");
    }

    @Test
    public void testReturnBook() throws IOException {
        Inventory book = library.checkoutInv('b', 0);

        library.returnInv('b', 0);
        String printed = new String(out.toByteArray());

        assertEquals(printed, "Thank you! Enjoy the inventory\n\nThank you for returning the inventory.\n\n");
        assertFalse(book.isCheckedout());
    }

    @Test
    public void testReturnBookFail() throws IOException {
        Inventory book = library.returnInv('b', library.getBooks().size());
        String printed = new String(out.toByteArray());

        assertEquals(printed, "That is not a valid inventory to return.\n\n");
    }

    @Test
    public void testReturnBookStillInLibraryFail() throws IOException {
        if (library.getBooks().size() > 0) {
            library.returnInv('b', 0);
            String printed = new String(out.toByteArray());

            assertEquals(printed, "That is not a valid inventory to return.\n\n");
        }
    }

    @Test
    public void testCheckoutMovie() {
        if (library.getMovies().size() > 0) {
            Inventory movie = library.checkoutInv('b', 0);
            String printed = new String(out.toByteArray());

            assertEquals(printed, "Thank you! Enjoy the inventory\n\n");
            assertTrue(movie.isCheckedout());
        }
    }

    @Test
    public void testCheckOutMovieFail() {
        Inventory movie = library.checkoutInv('m', library.getMovies().size());
        String printed = new String(out.toByteArray());

        assertEquals(printed, "That inventory is not available.\n\n");
    }

    @Test
    public void testReturnMovie() throws IOException {
        Inventory movie = library.checkoutInv('m', 0);

        library.returnInv('m', 0);
        String printed = new String(out.toByteArray());

        assertEquals(printed, "Thank you! Enjoy the inventory\n\nThank you for returning the inventory.\n\n");
        assertFalse(movie.isCheckedout());
    }

    @Test
    public void testReturnMovieFail() throws IOException {
        Inventory movie = library.returnInv('m', library.getBooks().size());
        String printed = new String(out.toByteArray());

        assertEquals(printed, "That is not a valid inventory to return.\n\n");
    }

    @Test
    public void testReturnMovieStillInLibraryFail() throws IOException {
        if (library.getMovies().size() > 0) {
            library.returnInv('b', 0);
            String printed = new String(out.toByteArray());

            assertEquals(printed, "That is not a valid inventory to return.\n\n");
        }
    }

}
