package com.example.tourguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.tourguide.API.NewsAPI;
import com.example.tourguide.Adapter.Adapter_news;
import com.example.tourguide.model.Response.JSONResponse;
import com.example.tourguide.model.sources;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsActivity extends AppCompatActivity {
    private RecyclerView newsrecyclerView;
    private ArrayList<sources> data;
    private Adapter_news adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        newsrecyclerView = findViewById(R.id.newrecycleview);
        initnews();
    }


    private void initnews() {
        newsrecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        newsrecyclerView.setLayoutManager(layoutManager);
        newsJSON();
    }

    private void newsJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://saurav.tech/NewsAPI/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsAPI request = retrofit.create(NewsAPI.class);
        Call<JSONResponse> call = request.getNews();

        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                if (!response.isSuccessful()) {
                    final ProgressDialog progressDialog = new ProgressDialog(NewsActivity.this);
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                    progressDialog.dismiss();
                    Toast.makeText(NewsActivity.this, "Error: API is not responding... " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                final ProgressDialog progressDialog = new ProgressDialog(NewsActivity.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();

                JSONResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getSources()));
                adapter = new Adapter_news(data);
                newsrecyclerView.setAdapter(adapter);

                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                final ProgressDialog progressDialog = new ProgressDialog(NewsActivity.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();

                Log.d("Error",t.getMessage());

            }
        });
    }

}