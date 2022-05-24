package com.ultimate.ecommerce.repository.server.response.get_product;

import com.google.gson.annotations.SerializedName;
import com.ultimate.ecommerce.repository.server.response.get_products.ProductData;

import java.util.List;

public class Upsell {
    @SerializedName("products")
    private List<ProductData> products;
    @SerializedName("hidden")
    private boolean hidden;

    public List<ProductData> getProducts() {
        return products;
    }

    public void setProducts(List<ProductData> products) {
        this.products = products;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
