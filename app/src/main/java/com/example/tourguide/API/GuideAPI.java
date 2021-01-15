package com.example.tourguide.API;

import com.example.tourguide.model.guideModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GuideAPI {
    @GET("guide")
    Call<List<guideModel>> getguide();
}
