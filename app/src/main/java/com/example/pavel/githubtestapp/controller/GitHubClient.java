package com.example.pavel.githubtestapp.controller;

import com.example.pavel.githubtestapp.model.AccessToken;
import com.example.pavel.githubtestapp.model.Commit;
import com.example.pavel.githubtestapp.model.Repository;
import com.example.pavel.githubtestapp.model.User;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;


public interface GitHubClient {
    public String BASE_URL = "http://api.github.com/";

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    Call<AccessToken> getAccessToken(
        @Field("client_id")String clientId,
        @Field("client_secret")String clientSecret,
        @Field("code")String code
    );

    @GET("/user/repos")
    Call<ArrayList<Repository>> getRepos(@QueryMap Map<String, String> map);

    @GET("/user")
    Call<User> getUser(@QueryMap Map<String, String> map);

    @GET("repos/{name}/{repo}/commits")
    Call<ArrayList<Commit>> getCommits(
            @Path("name")String name,
            @Path("repo")String repo,
            @QueryMap Map<String, String> map
    );

}
