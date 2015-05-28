package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class ExampleTest {
    Biblioteca library;

    @Before
    public void setUp() {
        library = new Biblioteca();
    }
}
