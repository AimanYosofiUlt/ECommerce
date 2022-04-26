package com.ultimate.ecommerce.repository.server.response.homepage;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomePageData {
    @SerializedName("pages")
    private int pages;
    @SerializedName("homeSections")
    private List<HomeSections> homeSections;

    public HomePageData() {
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<HomeSections> getHomeSections() {
        return homeSections;
    }

    public void setHomeSections(List<HomeSections> homeSections) {
        this.homeSections = homeSections;
    }
}
