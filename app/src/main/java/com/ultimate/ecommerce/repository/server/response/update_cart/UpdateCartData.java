package com.ultimate.ecommerce.repository.server.response.update_cart;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class UpdateCartData implements Serializable {
    @SerializedName("total")
    private double total;
    @SerializedName("beforeTotal")
    private double beforeTotal;
    @SerializedName("discount")
    private double discount;
    @SerializedName("vat")
    private double vat;
    @SerializedName("shipping")
    private double shipping;
    @SerializedName("subTotal")
    private double subTotal;
    @SerializedName("products")
    private List<Products> products;
    @SerializedName("mustLogin")
    private boolean mustLogin;

    public UpdateCartData() {
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getBeforeTotal() {
        return beforeTotal;
    }

    public void setBeforeTotal(double beforeTotal) {
        this.beforeTotal = beforeTotal;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public double getShipping() {
        return shipping;
    }

    public void setShipping(double shipping) {
        this.shipping = shipping;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public boolean getMustLogin() {
        return mustLogin;
    }

    public void setMustLogin(boolean mustLogin) {
        this.mustLogin = mustLogin;
    }
}
