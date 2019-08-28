package com.example.canadanews.service;

import com.example.canadanews.service.model.NewsList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("/s/2iodh4vg0eortkl/facts.json")
    Call<NewsList> getNews();
}

