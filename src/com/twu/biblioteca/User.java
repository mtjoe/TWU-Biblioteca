package com.twu.biblioteca;

/**
 * Created by marisatjoe on 4/06/15.
 */
public class User {
    private String number;
    private String password;
    private String name;
    private String email;
    private String phone;

    public User(String number, String password, String name, String email, String phone) {
        this.number = number;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    /* GETTER METHODS */

    public String getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return ("Name: " + this.name + "\nEmail: " + this.email + "\nPhone: " + this.phone + "\n");
    }
}
