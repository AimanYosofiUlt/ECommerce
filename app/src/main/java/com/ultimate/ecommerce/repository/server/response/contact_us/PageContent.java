package com.ultimate.ecommerce.repository.server.response.contact_us;

import com.google.gson.annotations.SerializedName;

public class PageContent {
    @SerializedName("des")
    private String des;
    @SerializedName("title")
    private String title;
    @SerializedName("img")
    private String img;

    public PageContent() {
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
