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
    List<User> users;

    User currUser;

    Map<String, Callable<Integer>> mainMenu = new HashMap<String, Callable<Integer>>() {
        {
            put("Login", new Callable<Integer>() {
                public Integer call() {
                    authorize();
                    return 1;
                }
            });
            put("My Info", new Callable<Integer>() {
                public Integer call() {
                    if (currUser != null) {
                        System.out.println(currUser.toString());
                    } else {
                        System.out.println("Please login first.");
                    }
                    return 1;
                }
            });
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
                    continuousRunCheckoutOrReturn("checkout", 'b');
                    return 1;
                }
            });
            put("Return Book", new Callable<Integer>() {
                public Integer call() {
                    continuousRunCheckoutOrReturn("return", 'b');
                    return 1;
                }
            });
            put("Checkout Movie", new Callable<Integer>() {
                public Integer call() {
                    continuousRunCheckoutOrReturn("checkout", 'm');
                    return 1;
                }
            });
            put("Return Movie", new Callable<Integer>() {
                public Integer call() {
                    continuousRunCheckoutOrReturn("return", 'm');
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
        currUser = null;

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

        users = new ArrayList<User>() {
            {
                add(new User("123-1234", "HelloWorld", "Marisa Tjoe", "marisatjoe@gmail.com", "+61474811963"));
                add(new User("423-1243", "foobar", "John Doe", "JohnTheDoe@gmail.com", "121849012"));
                add(new User("091-1234", "foobar", "Jane Doe", "JohnsWife@gmail.com", "1125124"));
            }
        };

    }

    public void printGreetings() {
        System.out.println("Hello, Welcome to the Biblioteca!\n");
    }


    public void run() {
        while (true) {
            try {
                printMainMenu();
                String input = in.nextLine();
                if (!mainMenu.containsKey(input)) throw new Exception();
                mainMenu.get(input).call();
            } catch (Exception e) {
                System.out.println("Select a valid option!\n");
            }
        }
    }


    /* #MENU OPTIONS */

    public void login(String number, String password) {
        User user = this.getUserWithNumber(number);

        if (user != null && user.getPassword().equals(password)) {
            System.out.println("You are now logged in.\n");
            this.currUser = user;
        } else {
            System.out.println("Wrong credentials.\n");
            return;
        }
    }

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


    public Inventory checkoutInv(char type, int index, User user) {
        try {
            Inventory inv;
            if      (type == 'b')    inv = books.get(index);
            else if (type == 'm')    inv = movies.get(index);
            else                     return null;

            if (!inv.isCheckedout()) {
                inv.checkoutBook(user);

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
            if (this.currUser != null || !menuItem.equals("My Info")) {
                System.out.println(menuItem);
            }
        }
        System.out.println();
    }

    public void continuousRunCheckoutOrReturn(String mode, char type) {
        if (this.currUser == null) {
            System.out.println("Please login to borrow or return a book.\n");
        } else {
            boolean rerun = runCheckoutOrReturn(mode, type);
            while (rerun) {
                rerun = runCheckoutOrReturn(mode, type);
            }
        }
    }

    public boolean runCheckoutOrReturn(String mode, char type) {
        boolean rerun;

        System.out.println("Please type in the number corresponding to the inventory you would like to " + mode + ", or type in -1 to go back to the main menu:");
        if (type == 'b')        listBooks((mode.equals("return")));
        else if (type == 'm')   listMovies((mode.equals("return")));

        int index = Integer.parseInt(in.nextLine());
        if (index == -1 || currUser == null) {
            return false;
        }

        if (mode.equals("checkout")) rerun = (checkoutInv(type, index, this.currUser) == null);
        else if (mode.equals("return")) rerun = (returnInv(type, index) == null);
        else return false;

        return true;
    }

    public void authorize() {
        System.out.println("Please specify library number:");
        String number = in.nextLine();

        System.out.println("Please specify password:");
        String password = in.nextLine();

        this.login(number, password);
    }


    /* #GETTER METHODS  */

    public List<Book> getBooks() {
        return books;
    }
    public List<Movie> getMovies() {
        return movies;
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUserWithNumber(String number) {
        for (User user: this.users) {
            if (user.getNumber().equals(number)) {
                return user;
            }
        }
        return null;
    }
}
