package com.ultimate.ecommerce.repository.server.response.coupon;

import com.google.gson.annotations.SerializedName;

public class CouponData {
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
}
