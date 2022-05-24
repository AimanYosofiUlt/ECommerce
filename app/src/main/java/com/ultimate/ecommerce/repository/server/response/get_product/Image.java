package com.ultimate.ecommerce.repository.server.response.get_product;

import com.google.gson.annotations.SerializedName;

public class Image {
    @SerializedName("imgUrl")
    private String imgUrl;
    @SerializedName("imgID")
    private int imgID;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }
}
