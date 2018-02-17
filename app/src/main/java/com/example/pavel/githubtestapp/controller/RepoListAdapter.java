package com.example.pavel.githubtestapp.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pavel.githubtestapp.R;
import com.example.pavel.githubtestapp.model.Repository;
import com.example.pavel.githubtestapp.view.DetailActivity;

import java.util.ArrayList;

/**
 * Created by pavel on 16.02.18.
 */

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.customViewHolder> {
    ArrayList<Repository> repositories;
    Context context;

    public RepoListAdapter(ArrayList<Repository> repositories, Context context) {
        this.repositories = repositories;
        this.context = context;
    }

    @Override
    public RepoListAdapter.customViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repo, parent, false);

        return new customViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RepoListAdapter.customViewHolder holder, final int position) {
        //holder.PostText.setText(dataController.returnClear(posts.get(position).getElementPureHtml()));
        holder.repoName.setText(repositories.get(position).getRepo());
        holder.owner.setText("owner: "+repositories.get(position).getOwnerName());
        holder.description.setText(repositories.get(position).getDescription());
        if(repositories.get(position).commits!=null){
            holder.commits.setText(repositories.get(position).commits.size()+" commits");
        }
        else{
            holder.commits.setText("0 commits");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"item" + Integer.toString(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("repoName", repositories.get(position).getRepo());
                intent.putExtra("owner", repositories.get(position).getOwnerName());
                if(repositories.get(position).getDescription()!=null){
                    intent.putExtra("description", repositories.get(position).getDescription());
                }
                else{
                    intent.putExtra("description", "none");
                }
                ArrayList<String> commits = new ArrayList<String>();

                for(int i=0; i<repositories.get(position).commits.size();i++){
                    commits.add(repositories.get(position).commits.get(i).sha);
                }
                intent.putExtra("commits",commits);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }
    public class customViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView repoName;
        TextView owner;
        TextView description;
        TextView commits;
        public customViewHolder(View itemView) {
            super(itemView);
            repoName = (TextView)itemView.findViewById(R.id.repoTv);
            owner = (TextView)itemView.findViewById(R.id.ownerTV);
            description = (TextView)itemView.findViewById(R.id.descriptionTv);
            commits = (TextView)itemView.findViewById(R.id.commitsTv);

        }

        @Override
        public void onClick(View view) {

        }
    }
}
