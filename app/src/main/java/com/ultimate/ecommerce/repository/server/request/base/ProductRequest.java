package com.ultimate.ecommerce.repository.server.request.base;

import com.google.gson.annotations.SerializedName;

public class ProductRequest {

    @SerializedName("id")
    private int id;
    @SerializedName("price")
    private String price;
    @SerializedName("qty")
    private int qty;

    public ProductRequest(int id, String price, int qty) {
        this.id = id;
        this.price = price;
        this.qty = qty;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

