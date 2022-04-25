package com.ultimate.ecommerce.repository.server.response.homepage;

import com.google.gson.annotations.SerializedName;

public class HomePageBanner {
    @SerializedName("url")
    private String url;
    @SerializedName("imageTitle")
    private String imageTitle;
    @SerializedName("id")
    private int id;
    @SerializedName("type")
    private String type;

    public HomePageBanner() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
