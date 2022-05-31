package com.ultimate.ecommerce.repository.server.request.create_order;

public class OrderProducts {
    private Args args;
    private int quantity;

    public OrderProducts(Args args, int quantity) {
        this.args = args;
        this.quantity = quantity;
    }

    public Args getArgs() {
        return args;
    }

    public void setArgs(Args args) {
        this.args = args;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
