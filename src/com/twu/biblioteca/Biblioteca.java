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
    List<Movie> movies;

    Map<String, Callable<Integer>> mainMenu = new HashMap<String, Callable<Integer>>() {
        {
            put("List Books", new Callable<Integer>() {
                public Integer call() {
                    listBooks(false);
                    return 1;
                }
            });
            put("List Movies", new Callable<Integer>() {
                public Integer call() {
                    listMovies(false);
                    return 1;
                }
            });
            put("Checkout Book", new Callable<Integer>() {
                public Integer call() {
                    continuousRunMenuItem("checkout", 'b');
                    return 1;
                }
            });
            put("Return Book", new Callable<Integer>() {
                public Integer call() {
                    continuousRunMenuItem("return", 'b');
                    return 1;
                }
            });
            put("Checkout Movie", new Callable<Integer>() {
                public Integer call() {
                    continuousRunMenuItem("checkout", 'm');
                    return 1;
                }
            });
            put("Return Movie", new Callable<Integer>() {
                public Integer call() {
                    continuousRunMenuItem("return", 'm');
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

        movies = new ArrayList<Movie>() {
            {
                add(new Movie("Shawshank Redemption", 1994, "Frank Darabont", 9.2));
                add(new Movie("The Godfather", 1972, "Francis Ford Coppola", 9.2));
                add(new Movie("The Dark Knight", 2008, "Christopher Nolan", 8.9));
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
            if ((books.get(i).isCheckedout() && checkedout) ||
                    (!books.get(i).isCheckedout() && !checkedout)) {
                System.out.print(i + " - " + books.get(i).toString());
            }
        }
    }

    public void listMovies(boolean checkedout) {
        for (int i = 0; i < movies.size(); i++) {
            if ((movies.get(i).isCheckedout() && checkedout) ||
                    (!movies.get(i).isCheckedout() && !checkedout)) {
                System.out.print(i + " - " + movies.get(i).toString());
            }
        }
    }


    public Inventory checkoutInv(char type, int index) {
        try {
            Inventory inv;
            if      (type == 'b')    inv = books.get(index);
            else if (type == 'm')    inv = movies.get(index);
            else                     return null;

            if (!inv.isCheckedout()) {
                inv.checkoutBook();
                System.out.println("Thank you! Enjoy the inventory\n");
            } else {
                throw new IndexOutOfBoundsException();
            }
            return inv;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That inventory is not available.\n");
            return null;
        }
    }

    public Inventory returnInv(char type, int index) {
        try {
            Inventory inv;
            if      (type == 'b')    inv = books.get(index);
            else if (type == 'm')    inv = movies.get(index);
            else                     return null;

            if (inv.isCheckedout()) {
                inv.returnBook();
                System.out.println("Thank you for returning the inventory.\n");
            } else {
                throw new IndexOutOfBoundsException();
            }
            return inv;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That is not a valid inventory to return.\n");
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

    public void continuousRunMenuItem(String mode, char type) {
        boolean rerun;


        System.out.println("Please type in the number corresponding to the inventory you would like to " + mode + ", or type in -1 to go back to the main menu:");
        if (type == 'b')        listBooks((mode == "return"));
        else if (type == 'm')   listMovies((mode == "return"));

        int index = Integer.parseInt(in.nextLine());

        if (index == -1) return;
        if (mode == "checkout") rerun = (checkoutInv(type, index) == null);
        else if (mode == "return") rerun = (returnInv(type, index) == null);
        else return;

        while (rerun) {
            System.out.println("Please type in the number corresponding to the inventory you would like to " + mode + ", or type in -1 to go back to the main menu:");
            index = Integer.parseInt(in.nextLine());

            if (index == -1) return;
            if (mode == "checkout") rerun = (checkoutInv(type, index) == null);
            else if (mode == "return") rerun = (returnInv(type, index) == null);
            else return;
        }
    }


    /* #GETTER METHODS  */

    public List<Book> getBooks() {
        return books;
    }
    public List<Movie> getMovies() {
        return movies;
    }
}
