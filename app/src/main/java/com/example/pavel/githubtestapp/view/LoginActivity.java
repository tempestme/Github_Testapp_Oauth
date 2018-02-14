package com.example.pavel.githubtestapp.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dd.processbutton.iml.ActionProcessButton;
import com.example.pavel.githubtestapp.R;
import com.example.pavel.githubtestapp.model.Api;

public class LoginActivity extends AppCompatActivity {

    private String clientId = "94b617a614abb82430c1";
    private String clientSecret ="19ca2935f4675e9c82163c455df1e35bbcb2f239";
    private String redirectUri = "tempestme://callback";
    private Uri uri;
    private static boolean key = true;
    ActionProcessButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (ActionProcessButton) findViewById(R.id.auth_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                key = false;
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "https://github.com/login/oauth/authorize" + "?client_id=" + clientId + "&scope=repo&redirect_uri=" + redirectUri)));
            }
        });
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

        Api api = new Api(getApplicationContext(),uri);
        api.getInfo();




    }
}
