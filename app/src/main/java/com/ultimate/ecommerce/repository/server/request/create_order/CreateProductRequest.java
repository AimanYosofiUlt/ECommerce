package com.ultimate.ecommerce.repository.server.request.create_order;

import java.util.List;

public class CreateProductRequest {

    private OrderShippingMethod orderShippingMethod;
    private OrderVat orderVat;
    private String orderPaymentMethod;
    private List<OrderCouponItems> orderCouponItems;
    private OrderShippingAddress orderShippingAddress;
    private OrderBillingAddress orderBillingAddress;
    private String orderUserId;
    private List<OrderProducts> orderProducts;

    public OrderShippingMethod getOrderShippingMethod() {
        return orderShippingMethod;
    }

    public void setOrderShippingMethod(OrderShippingMethod orderShippingMethod) {
        this.orderShippingMethod = orderShippingMethod;
    }

    public OrderVat getOrderVat() {
        return orderVat;
    }

    public void setOrderVat(OrderVat orderVat) {
        this.orderVat = orderVat;
    }

    public String getOrderPaymentMethod() {
        return orderPaymentMethod;
    }

    public void setOrderPaymentMethod(String orderPaymentMethod) {
        this.orderPaymentMethod = orderPaymentMethod;
    }

    public List<OrderCouponItems> getOrderCouponItems() {
        return orderCouponItems;
    }

    public void setOrderCouponItems(List<OrderCouponItems> orderCouponItems) {
        this.orderCouponItems = orderCouponItems;
    }

    public OrderShippingAddress getOrderShippingAddress() {
        return orderShippingAddress;
    }

    public void setOrderShippingAddress(OrderShippingAddress orderShippingAddress) {
        this.orderShippingAddress = orderShippingAddress;
    }

    public OrderBillingAddress getOrderBillingAddress() {
        return orderBillingAddress;
    }

    public void setOrderBillingAddress(OrderBillingAddress orderBillingAddress) {
        this.orderBillingAddress = orderBillingAddress;
    }

    public String getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(String orderUserId) {
        this.orderUserId = orderUserId;
    }

    public List<OrderProducts> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProducts> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
