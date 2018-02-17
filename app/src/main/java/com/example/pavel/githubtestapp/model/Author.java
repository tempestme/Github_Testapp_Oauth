package com.example.pavel.githubtestapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pavel on 17.02.18.
 */
public class Author {


    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("date")
    private String date;
    public String getName() {
            return name;
        }

    public void setName(String name) {
            this.name = name;
        }

    public String getEmail() {
            return email;
        }

    public void setEmail(String email) {
            this.email = email;
        }

    public String getDate() {
            return date;
        }

    public void setDate(String date) {
            this.date = date;
        }
}

