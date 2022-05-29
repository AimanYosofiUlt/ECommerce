package com.ultimate.ecommerce.repository.server.response.get_order;

import com.google.gson.annotations.SerializedName;

public class Shippingmethod {
    @SerializedName("total")
    private double total;
    @SerializedName("details")
    private String details;
    @SerializedName("name")
    private String name;

    public double getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
