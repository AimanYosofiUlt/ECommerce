package com.ultimate.ecommerce.repository.server.response.shipping_methods;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShippingMethodsData {
    @SerializedName("shipping_tax")
    private int shipping_tax;
    @SerializedName("taxes")
    private List<String> taxes;
    @SerializedName("cost")
    private int cost;
    @SerializedName("title")
    private String title;
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private int id;

    public ShippingMethodsData() {
    }

    public int getShipping_tax() {
        return shipping_tax;
    }

    public void setShipping_tax(int shipping_tax) {
        this.shipping_tax = shipping_tax;
    }

    public List<String> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<String> taxes) {
        this.taxes = taxes;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
