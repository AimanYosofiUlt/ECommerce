package com.ultimate.ecommerce.repository.server.response.filter_products;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilterProductData {
    @SerializedName("products")
    private List<Products> products;

    public FilterProductData() {
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }
}
