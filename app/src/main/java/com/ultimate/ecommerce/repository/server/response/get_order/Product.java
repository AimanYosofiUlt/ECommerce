package com.ultimate.ecommerce.repository.server.response.get_order;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("totalPrice")
    private double totalprice;
    @SerializedName("quantity")
    private double quantity;
    @SerializedName("image")
    private String image;
    @SerializedName("title")
    private String title;
    @SerializedName("type")
    private String type;
    @SerializedName("ID")
    private int id;

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
