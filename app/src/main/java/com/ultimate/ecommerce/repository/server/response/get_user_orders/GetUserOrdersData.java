package com.ultimate.ecommerce.repository.server.response.get_user_orders;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetUserOrdersData {
    @SerializedName("orders_completed")
    private List<Order> ordersCompleted;
    @SerializedName("orders_charged")
    private List<Order> ordersCharged;
    @SerializedName("orders_approved")
    private List<Order> ordersApproved;
    @SerializedName("orders_new")
    private List<Order> ordersNew;

    public List<Order> getOrdersCompleted() {
        return ordersCompleted;
    }

    public void setOrdersCompleted(List<Order> ordersCompleted) {
        this.ordersCompleted = ordersCompleted;
    }

    public List<Order> getOrdersCharged() {
        return ordersCharged;
    }

    public void setOrdersCharged(List<Order> ordersCharged) {
        this.ordersCharged = ordersCharged;
    }

    public List<Order> getOrdersApproved() {
        return ordersApproved;
    }

    public void setOrdersApproved(List<Order> ordersApproved) {
        this.ordersApproved = ordersApproved;
    }

    public List<Order> getOrdersNew() {
        return ordersNew;
    }

    public void setOrdersNew(List<Order> ordersNew) {
        this.ordersNew = ordersNew;
    }
}
