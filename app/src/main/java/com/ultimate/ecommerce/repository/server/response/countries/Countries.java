package com.ultimate.ecommerce.repository.server.response.countries;

import com.google.gson.annotations.SerializedName;

public class Countries {
    @SerializedName("depon_country")
    private Depon_country depon_country;
    @SerializedName("value")
    private String value;
    @SerializedName("placeholder")
    private String placeholder;

    public Countries() {
    }

    public Depon_country getDepon_country() {
        return depon_country;
    }

    public void setDepon_country(Depon_country depon_country) {
        this.depon_country = depon_country;
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
