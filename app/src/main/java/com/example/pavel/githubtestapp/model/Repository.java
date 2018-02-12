package com.example.pavel.githubtestapp.model;

import com.google.gson.annotations.SerializedName;


public class Repository {
    @SerializedName("name")
    private String repo;
    private String full_name;
    private String description;
    @SerializedName("private")
    private String isprivate;
    private String fork;


    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }
}
