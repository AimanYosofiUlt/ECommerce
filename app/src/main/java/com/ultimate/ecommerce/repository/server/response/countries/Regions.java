package com.ultimate.ecommerce.repository.server.response.countries;

import com.google.gson.annotations.SerializedName;

public class Regions {
    @SerializedName("depon_region")
    private Depon_region depon_region;
    @SerializedName("value")
    private String value;
    @SerializedName("placeholder")
    private String placeholder;

    public Depon_region getDepon_region() {
        return depon_region;
    }

    public void setDepon_region(Depon_region depon_region) {
        this.depon_region = depon_region;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }
}
