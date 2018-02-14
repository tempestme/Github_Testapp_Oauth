package com.example.pavel.githubtestapp.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.pavel.githubtestapp.R;
import com.example.pavel.githubtestapp.model.Api;

public class LoginActivity extends AppCompatActivity {

    private String clientId = "94b617a614abb82430c1";
    private String clientSecret ="19ca2935f4675e9c82163c455df1e35bbcb2f239";
    private String redirectUri = "tempestme://callback";
    private Uri uri;
    private static boolean key = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                "https://github.com/login/oauth/authorize" + "?client_id=" + clientId + "&scope=repo&redirect_uri=" + redirectUri));

        if(key==true){
            key = false;
            startActivity(intent);
        }



    }

    @Override
    protected void onResume() {
        super.onResume();

        uri = getIntent().getData();

        Api api = new Api(getApplicationContext(),uri);

        api.getInfo();




//        if (uri != null && uri.toString().startsWith(redirectUri)){
//            String code = uri.getQueryParameter("code");
//
//            Retrofit.Builder builder = new Retrofit.Builder()
//                    .baseUrl("https://github.com/")
//                    .addConverterFactory(GsonConverterFactory.create());
//            final Retrofit retrofit = builder.build();
//
//            GitHubClient client = retrofit.create(GitHubClient.class);
//
//            Call<AccessToken> accessTokenCall = client.getAccessToken(
//                    clientId, clientSecret, code
//            );
//
//            accessTokenCall.enqueue(new Callback<AccessToken>() {
//                @Override
//                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
//                    Toast.makeText(LoginActivity.this, "yey", Toast.LENGTH_SHORT).show();
//                    AccessToken token228 = response.body();
//                    /// /Intent intent = new Intent(getApplicationContext(),RepoActivity.class);
//
//                    Retrofit.Builder bldr = new Retrofit.Builder()
//                            .baseUrl(GitHubClient.BASE_URL)
//                            .addConverterFactory(GsonConverterFactory.create());
//                    Retrofit rtrft = bldr.build();
//
//
//                    GitHubClient clnt = rtrft.create(GitHubClient.class);
//
//                    final Map<String,String> map = new HashMap<>();
//
//                    map.put("access_token", token228.getAccessToken());
//
//                    final Call<ArrayList<Repository>> repos = clnt.getRepos(map);
//
//                    repos.enqueue(new Callback<ArrayList<Repository>>() {
//                        @Override
//                        public void onResponse(Call<ArrayList<Repository>> call, Response<ArrayList<Repository>> response) {
//                            ArrayList<Repository> repositories = response.body();
//
//                            for (Repository repo:repositories
//                                 ) {
//                                Log.e("repo",repo.getRepo());
//                            }
//
//                            Retrofit.Builder builder1 = new Retrofit.Builder()
//                                    .baseUrl(GitHubClient.BASE_URL)
//                                    .addConverterFactory(GsonConverterFactory.create());
//                            Retrofit retrofit1 = builder1.build();
//
//                            GitHubClient client1 = retrofit1.create(GitHubClient.class);
//                            Call<ArrayList<Commit>> commitCall = client1.getCommits("tempestme","android_dictophone", map);
//
//                            commitCall.enqueue(new Callback<ArrayList<Commit>>() {
//                                @Override
//                                public void onResponse(Call<ArrayList<Commit>> call, Response<ArrayList<Commit>> response) {
//
//                                }
//
//                                @Override
//                                public void onFailure(Call<ArrayList<Commit>> call, Throwable t) {
//
//                                }
//                            });
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<ArrayList<Repository>> call, Throwable t) {
//
//                        }
//                    });
//
//
//                }
//
//                @Override
//                public void onFailure(Call<AccessToken> call, Throwable t) {
//                    Toast.makeText(LoginActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }




    }
}
