package com.example.mylyceum;

public class User {
    public String login, password, name, surname, Class;

    public User() {
    }
    public User(String login, String password, String name, String surname, String Class){
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.Class = Class;
    }
}
