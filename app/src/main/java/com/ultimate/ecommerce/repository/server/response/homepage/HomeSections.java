package com.ultimate.ecommerce.repository.server.response.homepage;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeSections {
    // be Sure About the names
    @SerializedName(value = "bannerTwoImages", alternate = {"bannerThreeImages","bannerFourImages"})
    private List<HomePageBanner> bannerTwoImages;
    @SerializedName("space")
    private int space;
    @SerializedName("title")
    private String title;
    @SerializedName("layout")
    private String layout;

    public HomeSections() {
    }

    public List<HomePageBanner> getBanner() {
        return bannerTwoImages;
    }

    public void setBanner(List<HomePageBanner> bannerTwoImages) {
        this.bannerTwoImages = bannerTwoImages;
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
