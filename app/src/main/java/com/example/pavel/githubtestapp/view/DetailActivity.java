package com.example.pavel.githubtestapp.view;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.pavel.githubtestapp.R;
import com.example.pavel.githubtestapp.controller.DetailListAdapter;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    String name;
    String owner;
    String description;
    ArrayList<String> commits;
    TextView repoName;
    TextView repoOwner;
    TextView repoDescription;
    RecyclerView commitList;
    DetailListAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        name = getIntent().getExtras().get("repoName").toString();
        owner = getIntent().getExtras().get("owner").toString();
        description = getIntent().getExtras().get("description").toString();
        commits = (ArrayList<String>) getIntent().getExtras().get("commits");

        repoName = (TextView)findViewById(R.id.mainTv);
        repoOwner = (TextView)findViewById(R.id.secondTv);
        repoDescription = (TextView)findViewById(R.id.descriptionTv);
        commitList = (RecyclerView)findViewById(R.id.commitList);

        layoutManager = new LinearLayoutManager(this);
        commitList.setLayoutManager(layoutManager);
        adapter = new DetailListAdapter(getApplicationContext(), commits);
        commitList.setAdapter(adapter);

        repoName.setText(name);
        repoOwner.setText(owner);
        repoDescription.setText(description);



    }
}
