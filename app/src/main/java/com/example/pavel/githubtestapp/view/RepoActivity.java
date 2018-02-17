package com.example.pavel.githubtestapp.view;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pavel.githubtestapp.R;
import com.example.pavel.githubtestapp.controller.GitHubClient;
import com.example.pavel.githubtestapp.controller.RepoListAdapter;
import com.example.pavel.githubtestapp.model.Commit;
import com.example.pavel.githubtestapp.model.Repository;
import com.example.pavel.githubtestapp.model.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepoActivity extends AppCompatActivity {

    final Map<String,String> map = new HashMap<>();
    private ArrayList<Repository> repos;
    private User user;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView avatarIv;
    private TextView login;
    private TextView name;
    private TextView email;
    GitHubClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        avatarIv = (ImageView)findViewById(R.id.htab_header);
        login = (TextView) findViewById(R.id.loginTv);
        name = (TextView)findViewById(R.id.nameTv);
        email = (TextView)findViewById(R.id.emailTv);

        repos = new ArrayList<Repository>();
        //repos.add(new Repository("dummy", new Repository.Owner("login","id","avatar_url"),"dummy","dummy","dummy","dummy","dummy"));
        //user = new User("tempestme",null,"pavel","zavr","paulsecondtwin@gmail.com");


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());
        final Retrofit retrofit = builder.build();
        client = retrofit.create(GitHubClient.class);

        Call<ArrayList<Repository>> repoCall = client.getRepos(map);
        Call<User> userCall = client.getUser(map);


        recyclerView = (RecyclerView)findViewById(R.id.repoList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final RepoListAdapter adapter = new RepoListAdapter(repos, getApplicationContext());
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        String token = intent.getExtras().get("token").toString();
        map.put("access_token", token);

        repoCall.enqueue(new Callback<ArrayList<Repository>>() {
            @Override
            public void onResponse(Call<ArrayList<Repository>> call, Response<ArrayList<Repository>> response) {
                repos.addAll(response.body());
                adapter.notifyDataSetChanged();

                for(int i=0; i<response.body().size();i++){
                    Call<ArrayList<Commit>> commitCall = client.getCommits(
                            repos.get(i).getOwnerName(),
                            repos.get(i).getRepo(),
                            map
                    );

                    final int finalI = i;
                    commitCall.enqueue(new Callback<ArrayList<Commit>>() {
                        @Override
                        public void onResponse(Call<ArrayList<Commit>> call, Response<ArrayList<Commit>> response) {
                            repos.get(finalI).commits = response.body();
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<ArrayList<Commit>> call, Throwable t) {
                            Toast.makeText(getBaseContext(),"check interner connection",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Repository>> call, Throwable t) {
                Toast.makeText(getBaseContext(),"check interner connection",Toast.LENGTH_SHORT).show();
            }
        });
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
                Picasso.with(getApplicationContext()).load(user.getAvatar_url()).into(avatarIv);
                name.setText(user.getName());
                login.setText(user.getLogin());
                email.setText(user.getEmail());


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });




    }
}
