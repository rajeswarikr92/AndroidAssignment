package com.example.canadanews.models;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsListItemModel implements Parcelable {
    private String newsTitle;
    private String title;
    private String description;
    private String imageUrl;

    public NewsListItemModel(String newsTitle, String title, String description, String imageUrl) {
        this.newsTitle = newsTitle;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;

    }

    protected NewsListItemModel(Parcel in) {
        newsTitle = in.readString();
        title = in.readString();
        description = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<NewsListItemModel> CREATOR = new Creator<NewsListItemModel>() {
        @Override
        public NewsListItemModel createFromParcel(Parcel in) {
            return new NewsListItemModel(in);
        }

        @Override
        public NewsListItemModel[] newArray(int size) {
            return new NewsListItemModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
