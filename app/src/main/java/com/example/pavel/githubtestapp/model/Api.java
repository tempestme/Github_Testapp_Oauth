package com.example.pavel.githubtestapp.model;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.pavel.githubtestapp.controller.GitHubClient;

import java.util.ArrayList;
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
    private final String redirectUri = "tempestme://callback";
    private Context mainContext;
    private Uri uri;
    private ArrayList<Repository> repositories;
    private User user;
    private AccessToken token;


    public Api(Context context, User user, ArrayList<Repository> repositories) {
        this.mainContext = context;
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());
        final Retrofit retrofit = builder.build();
        client = retrofit.create(GitHubClient.class);
        this.repositories = repositories;
        this.user = user;

    }

    public void getUser(Map map){
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

    public void getCommits(Map map){
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

    public void getRepos(final Map map, final RecyclerView.Adapter adapter){
        Call<ArrayList<Repository>> repos = client.getRepos(map);

        repos.enqueue(new Callback<ArrayList<Repository>>() {
            @Override
            public void onResponse(Call<ArrayList<Repository>> call, Response<ArrayList<Repository>> response) {
                repositories = response.body();
                adapter.notifyDataSetChanged();



                for(int i = 0;i<repositories.size();i++){
                    Call<ArrayList<Commit>> commitCall = client.getCommits(
                            repositories.get(i).getFull_name().split("/")[0],
                            repositories.get(i).getFull_name().split("/")[1],
                            map);
                    Log.e("response","got repos from"+repositories.get(i).getFull_name().toString());

                    final int finalI = i;
                    commitCall.enqueue(new Callback<ArrayList<Commit>>() {
                        @Override
                        public void onResponse(Call<ArrayList<Commit>> call, Response<ArrayList<Commit>> response) {

                        }

                        @Override
                        public void onFailure(Call<ArrayList<Commit>> call, Throwable t) {

                        }
                    });
                }


            }

            @Override
            public void onFailure(Call<ArrayList<Repository>> call, Throwable t) {
                Toast.makeText(mainContext,"failed to connect",Toast.LENGTH_SHORT).show();
            }
        });
    } //getCommits inside.


}
