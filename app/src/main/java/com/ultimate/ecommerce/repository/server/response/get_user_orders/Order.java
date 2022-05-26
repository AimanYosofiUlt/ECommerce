package com.ultimate.ecommerce.repository.server.response.get_user_orders;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Order {
    @SerializedName("products")
    private List<Products> products;
    @SerializedName("productsNo")
    private int productsno;
    @SerializedName("order_total")
    private String orderTotal;
    @SerializedName("order_status")
    private String orderStatus;
    @SerializedName("order_date")
    private OrderDate orderDate;
    @SerializedName("order_id")
    private int orderId;

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public int getProductsno() {
        return productsno;
    }

    public void setProductsno(int productsno) {
        this.productsno = productsno;
    }

    public String getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(OrderDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
