package com.ultimate.ecommerce.repository.server.response.filter_products;

import com.google.gson.annotations.SerializedName;
import com.ultimate.ecommerce.repository.server.response.get_products.ProductData;

import java.util.List;

public class FilterProductData {
    @SerializedName("products")
    private List<ProductData> products;

    public FilterProductData() {
    }

    public List<ProductData> getProducts() {
        return products;
    }

    public void setProducts(List<ProductData> products) {
        this.products = products;
    }
}
