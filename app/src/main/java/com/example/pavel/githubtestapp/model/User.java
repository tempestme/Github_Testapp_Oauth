package com.example.pavel.githubtestapp.model;

/**
 * Created by pavel on 14.02.18.
 */

public class User {
    String login;
    String avatar_url;
    String name;
    String company;
    String email;

    public User(String login, String avatar_url, String name, String company, String email) {
        this.login = login;
        this.avatar_url = avatar_url;
        this.name = name;
        this.company = company;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getEmail() {
        return email;
    }
}
