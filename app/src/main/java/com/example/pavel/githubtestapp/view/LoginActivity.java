package com.example.pavel.githubtestapp.view;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.example.pavel.githubtestapp.R;
import com.example.pavel.githubtestapp.controller.GitHubClient;
import com.example.pavel.githubtestapp.model.AccessToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private String clientId = "94b617a614abb82430c1";
    private String clientSecret ="19ca2935f4675e9c82163c455df1e35bbcb2f239";
    private String redirectUri = "tempestme://callback";
    private Uri uri;
    private static boolean key = true;
    ActionProcessButton button;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        button = (ActionProcessButton) findViewById(R.id.auth_button);


//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
//                "https://github.com/login/oauth/authorize" + "?client_id=" + clientId + "&scope=repo&redirect_uri=" + redirectUri));
//
//        if(key==true){
//        }



    }

    @Override
    protected void onResume() {
        super.onResume();


        uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(redirectUri)) {
            button.setOnClickListener(null);
            String code = uri.getQueryParameter("code");

            GitHubClient client_token_call;

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("https://github.com/")
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            client_token_call = retrofit.create(GitHubClient.class);

            Call<AccessToken> accessTokenCall = client_token_call.getAccessToken(clientId, clientSecret,code);

            accessTokenCall.enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                    AccessToken token = response.body();
                    Intent repoIntent = new Intent(getApplicationContext(),RepoActivity.class);
                    repoIntent.putExtra("token",token.getAccessToken());
                    setIntent(new Intent(getApplicationContext(),LoginActivity.class));
                    finish();
                    startActivity(repoIntent);
                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"check your connection", Toast.LENGTH_LONG).show();
                }
            });
        }else{
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button.setActivated(false);
                    key = false;
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(
                            "https://github.com/login/oauth/authorize" + "?client_id=" + clientId + "&scope=repo&redirect_uri=" + redirectUri)));

                }
            });
        }


//        Api api = new Api(getApplicationContext(),uri);
//        api.getInfo();





    }
}
