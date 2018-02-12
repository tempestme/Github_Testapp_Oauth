package com.example.pavel.githubtestapp.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("name")
    String username;

    public String getUsername() {
        return username;
    }
}
