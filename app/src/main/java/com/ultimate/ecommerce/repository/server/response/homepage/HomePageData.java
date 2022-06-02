package com.ultimate.ecommerce.repository.server.response.homepage;

import com.google.gson.annotations.SerializedName;
import com.ultimate.ecommerce.repository.server.response.homepage.base.BaseSection;

import java.util.List;

public class HomePageData {
    @SerializedName("pages")
    private int pages;
    @SerializedName("homeSections")
    private List<BaseSection> homeSections;

    public HomePageData() {
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<BaseSection> getHomeSections() {
        return homeSections;
    }

    public void setHomeSections(List<BaseSection> homeSections) {
        this.homeSections = homeSections;
    }
}
