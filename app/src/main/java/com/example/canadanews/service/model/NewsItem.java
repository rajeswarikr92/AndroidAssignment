package com.example.canadanews.service.model;

import com.google.gson.annotations.SerializedName;

public class NewsItem {
    @SerializedName("title")
    public String title;
    @SerializedName("description")
    public String description;
    @SerializedName("imageHref")
    public String imageHref;

    public String getTitle() {
        return title;
    }

    public void setTitle(){
        this.title = title ;
    }
    public String getdescription() {
        return description;
    }

    public void setDescription(){
        this.description = description ;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(){
        this.imageHref = imageHref ;
    }

}
