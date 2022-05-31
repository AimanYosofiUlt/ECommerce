package com.ultimate.ecommerce.repository.server.request.create_order;

public class Args {
    private int productId;

    public Args(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
