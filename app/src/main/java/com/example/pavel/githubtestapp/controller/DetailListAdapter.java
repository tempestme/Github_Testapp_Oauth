package com.example.pavel.githubtestapp.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pavel.githubtestapp.R;

import java.util.ArrayList;

/**
 * Created by pavel on 17.02.18.
 */

public class DetailListAdapter extends RecyclerView.Adapter<DetailListAdapter.detailViewHolder> {
    ArrayList<String> shaList;
    Context context;
    public DetailListAdapter(Context context, ArrayList<String> shaList) {
        this.shaList = shaList;
        this.context = context;
    }

    @Override
    public detailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commit, parent, false);
        return new detailViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(detailViewHolder holder, int position) {
        holder.sha.setText(shaList.get(position));
    }

    @Override
    public int getItemCount() {
        return shaList.size();
    }

    public class detailViewHolder extends RecyclerView.ViewHolder{
        TextView sha;

        public detailViewHolder(View itemView) {
            super(itemView);
            sha = (TextView)itemView.findViewById(R.id.sha);

        }
    }
}
