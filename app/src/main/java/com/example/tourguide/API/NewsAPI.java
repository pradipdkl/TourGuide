package com.example.tourguide.API;

import com.example.tourguide.model.Response.JSONResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsAPI {
    @GET("sources.json")
    Call<JSONResponse> getNews();
}
