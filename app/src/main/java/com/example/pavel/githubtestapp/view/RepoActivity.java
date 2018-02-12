package com.example.pavel.githubtestapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pavel.githubtestapp.R;

public class RepoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);

        Toast.makeText(getApplicationContext(),"pidor suka",Toast.LENGTH_SHORT).show();

    }
}
