package com.ultimate.ecommerce.repository.server.response.create_order;

import com.google.gson.annotations.SerializedName;

public class CreateOrderData {
    @SerializedName("order_id")
    private int order_id;

    public CreateOrderData() {
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
}
