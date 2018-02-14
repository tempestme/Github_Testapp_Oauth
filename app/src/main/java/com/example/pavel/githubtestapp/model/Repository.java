package com.example.pavel.githubtestapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.regex.Pattern;


public class Repository {
    @SerializedName("name")
    private String repo;
    private Owner owner;
    private String full_name;
    private String description;
    @SerializedName("private")
    private String isprivate;
    private String fork;
    private String commits_url;

    public Repository(String repo, Owner owner, String full_name, String description, String isprivate, String fork, String commits_url) {
        this.repo = repo;
        this.owner = owner;
        this.full_name = full_name;
        this.description = description;
        this.isprivate = isprivate;
        this.fork = fork;

        String clearUrl = commits_url.replaceAll(Pattern.quote("{/sha}"),"");
        clearUrl = clearUrl.replaceAll(Pattern.quote("https://api.github.com/"),"");
        this.commits_url = clearUrl;
    }

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getDescription() {
        return description;
    }

    public String getIsprivate() {
        return isprivate;
    }

    public String getFork() {
        return fork;
    }

    public String getCommits_url() {
        String clearUrl = commits_url;
        clearUrl = clearUrl.replaceAll(Pattern.quote("{/sha}"),"");
        clearUrl = clearUrl.replaceAll(Pattern.quote("https://api.github.com/"),"");
        clearUrl = clearUrl.trim();
        return clearUrl;
    }
    public void setCommits_url(String commits_url) {
    }

    private class Owner{
        private String login;
        private String id;
        private String avatar_url;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }
    }
}
