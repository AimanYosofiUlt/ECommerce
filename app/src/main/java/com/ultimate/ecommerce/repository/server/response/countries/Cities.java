package com.ultimate.ecommerce.repository.server.response.countries;

import com.google.gson.annotations.SerializedName;

public class Cities {
    @SerializedName("depon_city")
    private Depon_city depon_city;
    @SerializedName("value")
    private String value;
    @SerializedName("placeholder")
    private String placeholder;

    public Depon_city getDepon_city() {
        return depon_city;
    }

    public void setDepon_city(Depon_city depon_city) {
        this.depon_city = depon_city;
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
