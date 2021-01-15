package com.example.tourguide.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tourguide.R;
import com.example.tourguide.model.sources;

import java.util.ArrayList;
import java.util.List;

public class Adapter_news extends RecyclerView.Adapter<Adapter_news.ViewHolder> {

    private ArrayList<sources> sourcesArrayList;
    public Adapter_news(ArrayList<sources> sources){this.sourcesArrayList = sources; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.newstitle.setText(sourcesArrayList.get(position).getName());
        holder.newssource.setText(sourcesArrayList.get(position).getDescription());
        holder.newscate.setText(sourcesArrayList.get(position).getCategory());
        holder.newsUrl.setText(sourcesArrayList.get(position).getUrl());

    }

    @Override
    public int getItemCount() {
        return sourcesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView newstitle, newssource, newscate,newsUrl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newstitle = itemView.findViewById(R.id.etnewstitle);
            newssource = itemView.findViewById(R.id.etnewssource);
            newscate = itemView.findViewById(R.id.etnewssummary);
            newsUrl = itemView.findViewById(R.id.etnewsurl);
        }
    }

}

