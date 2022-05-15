package com.ultimate.ecommerce.repository.server.request.update_cart;

import com.google.gson.annotations.SerializedName;

public class UpdateCartProductRequest {
    @SerializedName("qty")
    private int quantity;
    @SerializedName("price")
    private int price;
    @SerializedName("id")
    private int id;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
