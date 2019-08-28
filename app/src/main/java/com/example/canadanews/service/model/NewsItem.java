package com.example.canadanews.service.model;

import com.google.gson.annotations.SerializedName;

public class NewsItem {
    @SerializedName("title")
    public String title;
    @SerializedName("description")
    public String description;
    @SerializedName("imageHref")
    public String imageHref;

}
