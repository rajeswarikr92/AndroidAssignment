package com.example.canadanews.service.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsList {
    @SerializedName("title")
    public String title;
    @SerializedName("rows")
    public ArrayList<NewsItem> newsItem;

    public NewsList(String title, ArrayList<NewsItem> newsItem) {
        this.title = title;
        this.newsItem= newsItem;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<NewsItem> getNewsItem(){
        return  newsItem;
    }

    public void setNewsItem(ArrayList<NewsItem> rnewsItem) {
        this.newsItem = newsItem;
    }

}
