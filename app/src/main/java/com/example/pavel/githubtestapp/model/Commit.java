package com.example.pavel.githubtestapp.model;

import com.google.gson.annotations.SerializedName;

public class Commit {
    @SerializedName("sha")
    public String sha;
    @SerializedName("author")
    public Author author;
    @SerializedName("committer")
    public Committer committer;
    @SerializedName("message")
    public String message;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Committer getCommitter() {
        return committer;
    }

    public void setCommitter(Committer committer) {
        this.committer = committer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}