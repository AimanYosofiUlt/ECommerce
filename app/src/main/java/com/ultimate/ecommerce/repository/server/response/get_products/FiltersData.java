package com.ultimate.ecommerce.repository.server.response.get_products;

import com.google.gson.annotations.SerializedName;

public class FiltersData {
    @SerializedName("maxPrice")
    private int maxPrice;
    @SerializedName("minPrice")
    private int minPrice;
    @SerializedName("filterTitle")
    private String filterTitle;
    @SerializedName("filterHidden")
    private boolean filterHidden;
    @SerializedName("filterType")
    private String filterType;

    public FiltersData() {
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public String getFilterTitle() {
        return filterTitle;
    }

    public void setFilterTitle(String filterTitle) {
        this.filterTitle = filterTitle;
    }

    public boolean getFilterHidden() {
        return filterHidden;
    }

    public void setFilterHidden(boolean filterHidden) {
        this.filterHidden = filterHidden;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }
}
