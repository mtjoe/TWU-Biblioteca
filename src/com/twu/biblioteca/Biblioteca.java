package com.twu.biblioteca;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.Map;


/**
 * Created by marisatjoe on 27/05/15.
 */
public class Biblioteca {
    List<Book> books;
    // String[] mainMenu = new String[] {"List Books", "Checkout Book", "Return Book", "Quit"};

    Map<String, Callable<Integer>> mainMenu = new HashMap<String, Callable<Integer>>() {
        {
            put("List", new Callable<Integer>() {
                public Integer call() {
                    listBooks(false);
                    return 1;
                }
            });
            put("Checkout", new Callable<Integer>() {
                public Integer call() {
                    continuousRunMenuItem("checkout");
                    return 1;
                }
            });
            put("Return", new Callable<Integer>() {
                public Integer call() {
                    continuousRunMenuItem("return");
                    return 1;
                }
            });
            put("Quit", new Callable<Integer>() {
                public Integer call() {
                    quit();
                    return 1;
                }
            });
        }
    };

    Scanner in;

    public Biblioteca() {
        in = new Scanner(System.in);

        books = new ArrayList<Book>() {
            {
                add(new Book("1984", "George Orwell", 1948));
                add(new Book("Pride & Prejudice", "Jane Austen", 1948));
            }
        };
    }

    public void printGreetings() {
        System.out.println("Hello, Welcome to the Biblioteca!");
        System.out.println();
    }


    public void run() {
        while (true) {
            try {
                printMainMenu();
                String input = in.nextLine();
                if (!mainMenu.containsKey(input)) throw new Exception();
                mainMenu.get(input).call();
            } catch (Exception e) {
                System.out.println("Select a valid option!");
            }
        }
    }


    /* #MENU OPTIONS */

    public void listBooks(boolean checkedout) {
        for (int i = 0; i < books.size(); i++) {
            if ((books.get(i).checkedout && checkedout) ||
                    (!books.get(i).checkedout && !checkedout)) {
                System.out.print(i + " - " + books.get(i).toString());
            }
        }
    }

    public Book checkoutBook(int index) {
        try {
            Book book = books.get(index);

            if (!book.checkedout) {
                book.checkedout = true;
                System.out.println("Thank you! Enjoy the book");
            } else {
                throw new IndexOutOfBoundsException();
            }
            return book;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That book is not available.");
            return null;
        }
    }

    public Book returnBook(int index) {
        try {
            Book book = books.get(index);

            if (book.checkedout) {
                book.checkedout = false;
                System.out.println("Thank you for returning the book.");
            } else {
                throw new IndexOutOfBoundsException();
            }
            return book;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That is not a valid book to return.");
            return null;
        }
    }

    public void quit() {
        System.exit(0);
    }

    /* #HELPER FUNCTIONS */

    private void printMainMenu() {
        System.out.println("MAIN MENU");
        System.out.println("---------");
        System.out.println("Please type in your selection:");

        for (String menuItem : mainMenu.keySet()) {
            System.out.println(menuItem);
        }
        System.out.println();
    }

    public void continuousRunMenuItem(String mode) {
        boolean rerun;
        int index = Integer.parseInt(in.nextLine());

        listBooks((mode == "return"));
        System.out.println("Please type in the number corresponding to the book you would like to " + mode + ", or type in -1 to go back to the main menu:");


        if (index == -1) return;
        if (mode == "checkout") rerun = (checkoutBook(index) == null);
        else if (mode == "return") rerun = (returnBook(index) == null);
        else return;

        while (rerun) {
            System.out.println("Please type in the number corresponding to the book you would like to " + mode + ", or type in -1 to go back to the main menu:");
            index = Integer.parseInt(in.nextLine());

            if (index == -1) return;
            if (mode == "checkout") rerun = (checkoutBook(index) == null);
            else if (mode == "return") rerun = (returnBook(index) == null);
            else return;
        }
    }


    /* #GETTER METHODS  */

    public List<Book> getBooks() {
        return books;
    }
}
