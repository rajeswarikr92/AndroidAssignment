package com.example.canadanews.service.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsList {
    @SerializedName("title")
    public String title;
    @SerializedName("rows")
    public ArrayList<NewsItem> newsItem;
}
