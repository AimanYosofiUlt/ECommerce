package com.ultimate.ecommerce.repository.server.response.get_order;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetOrderData implements Serializable {
    @SerializedName("cost")
    private Cost cost;
    @SerializedName("shippingMethod")
    private Shippingmethod shippingmethod;
    @SerializedName("paymentMethod")
    private String paymentmethod;
    @SerializedName("billing")
    private Billing billing;
    @SerializedName("products")
    private List<Product> products;
    @SerializedName("productsNo")
    private double productsno;
    @SerializedName("currency")
    private String currency;
    @SerializedName("orderStatus")
    private String orderstatus;
    @SerializedName("orderID")
    private int orderid;

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public Shippingmethod getShippingMethod() {
        return shippingmethod;
    }

    public void setShippingmethod(Shippingmethod shippingmethod) {
        this.shippingmethod = shippingmethod;
    }

    public String getPaymentMethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getProductsno() {
        return productsno;
    }

    public void setProductsno(double productsno) {
        this.productsno = productsno;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }
}
