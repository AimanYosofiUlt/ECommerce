package com.ultimate.ecommerce.repository.server.response.homepage.base;

import com.google.gson.annotations.SerializedName;

public class Banner {

    @SerializedName("url")
    private String url;
    @SerializedName("imageTitle")
    private String imagetitle;
    @SerializedName("id")
    private int id;
    @SerializedName("type")
    private String type;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImagetitle() {
        return imagetitle;
    }

    public void setImagetitle(String imagetitle) {
        this.imagetitle = imagetitle;
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
