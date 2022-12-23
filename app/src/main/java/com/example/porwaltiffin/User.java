package com.example.porwaltiffin;

public class User {
    String name;
    String number;
    String pass;

    public User()
    {

    }

    public User(String name, String number, String password) {
        this.name = name;
        this.number = number;
        this.pass = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumbers() {
        return number;
    }

    public void setNumbers(String number) {
        this.number = number;
    }

    public String getPass() {
        return pass;
    }

    public void setPassword(String password) {
        this.pass = password;
    }

}
