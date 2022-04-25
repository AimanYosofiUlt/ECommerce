package com.ultimate.ecommerce.repository.server.response.countries;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Depon_country {
    @SerializedName("regions")
    private List<Regions> regions;
    @SerializedName("type")
    private String type;
    @SerializedName("placeholder")
    private String placeholder;
    @SerializedName("name")
    private String name;

    public List<Regions> getRegions() {
        return regions;
    }

    public void setRegions(List<Regions> regions) {
        this.regions = regions;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
