package com.ultimate.ecommerce.repository.server.request.update_cart;

import com.google.gson.annotations.SerializedName;

public class UpdateCartProductRequest {
    @SerializedName("id")
    private int id;
    @SerializedName("price")
    private String price;
    @SerializedName("qty")
    private int quantity;

    public UpdateCartProductRequest(int id, String price, int quantity) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
