package com.example.pavel.githubtestapp.model;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.example.pavel.githubtestapp.controller.GitHubClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pavel on 14.02.18.
 */


public class Api {
    GitHubClient client_token_call;
    GitHubClient client;
    private final String clientId = "94b617a614abb82430c1";
    private final String clientSecret ="19ca2935f4675e9c82163c455df1e35bbcb2f239";
    private final String redirectUri = "tempestme://callback";
    final Map<String,String> map = new HashMap<>();
    private Context mainContext;
    private Uri uri;
    ArrayList<Repository> repositories;
    AccessToken token;


    public Api(Context context, Uri uri) {
        this.uri = uri;
        this.mainContext = context;
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());
        final Retrofit retrofit = builder.build();
        client = retrofit.create(GitHubClient.class);

        Retrofit.Builder builder1 = new Retrofit.Builder()
                .baseUrl("https://github.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit1 = builder1.build();
        client_token_call = retrofit1.create(GitHubClient.class);



    }

    public void getInfo(){
        /**
         * initializing callback hell
         */

        if (uri != null && uri.toString().startsWith(redirectUri)) {
            String code = uri.getQueryParameter("code");

            Call<AccessToken> accessTokenCall = client_token_call.getAccessToken(
                    clientId, clientSecret, code
            );

            /**
             * get request to request access token aka authorization
             */
            accessTokenCall.enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                    token = response.body();
                    map.put("access_token", token.getAccessToken());

                    getUser();
                    getRepos();

                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {
                    Toast.makeText(mainContext, "check your internet connection", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }
    public void getUser(){
        Call<User> user = client.getUser(map);

        user.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void getCommits(){
        Call<ArrayList<Commit>> commitCall = client.getCommits(
                repositories.get(4).getFull_name().split("/")[0],
                repositories.get(4).getFull_name().split("/")[1],
                map);

        commitCall.enqueue(new Callback<ArrayList<Commit>>() {
            @Override
            public void onResponse(Call<ArrayList<Commit>> call, Response<ArrayList<Commit>> response) {

            }

            @Override
            public void onFailure(Call<ArrayList<Commit>> call, Throwable t) {

            }
        });
    }

    public void getRepos(){
        Call<ArrayList<Repository>> repos = client.getRepos(map);

        repos.enqueue(new Callback<ArrayList<Repository>>() {
            @Override
            public void onResponse(Call<ArrayList<Repository>> call, Response<ArrayList<Repository>> response) {
                repositories = response.body();

                getCommits();

            }

            @Override
            public void onFailure(Call<ArrayList<Repository>> call, Throwable t) {

            }
        });
    } //getCommits inside.


}
