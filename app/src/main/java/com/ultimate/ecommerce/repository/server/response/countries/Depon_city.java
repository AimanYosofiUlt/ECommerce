package com.ultimate.ecommerce.repository.server.response.countries;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Depon_city {
    @SerializedName("districts")
    private List<String> districts;
    @SerializedName("type")
    private String type;
    @SerializedName("placeholder")
    private String placeholder;
    @SerializedName("name")
    private String name;

    public List<String> getDistricts() {
        return districts;
    }

    public void setDistricts(List<String> districts) {
        this.districts = districts;
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
