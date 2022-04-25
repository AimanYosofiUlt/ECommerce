package com.ultimate.ecommerce.repository.server.response.update_cart;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpdateCartData {
    @SerializedName("total")
    private int total;
    @SerializedName("beforeTotal")
    private int beforeTotal;
    @SerializedName("discount")
    private int discount;
    @SerializedName("vat")
    private int vat;
    @SerializedName("shipping")
    private int shipping;
    @SerializedName("subTotal")
    private int subTotal;
    @SerializedName("products")
    private List<Products> products;
    @SerializedName("mustLogin")
    private boolean mustLogin;

    public UpdateCartData() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getBeforeTotal() {
        return beforeTotal;
    }

    public void setBeforeTotal(int beforeTotal) {
        this.beforeTotal = beforeTotal;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }

    public int getShipping() {
        return shipping;
    }

    public void setShipping(int shipping) {
        this.shipping = shipping;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
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
