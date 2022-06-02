package com.ultimate.ecommerce.repository.server.response.homepage.base;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseSection {
    @SerializedName("space")
    private int space;
    @SerializedName("title")
    private String title;
    @SerializedName("layout")
    private String layout;

    @SerializedName(value = "searchSliderOneImages", alternate = {"categoriesImages", "bannerOneImages","bannerTwoImages"
    ,"bannerThreeImages","bannerFourImages","products","mainSliderTwoImages","mainSliderThreeImages","mainSliderFourImages"})
    private List<Object> data;

    public void setData(List<Object> data) {
        this.data = data;
    }

    public List<Object> getData() {
        return data;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }
}
