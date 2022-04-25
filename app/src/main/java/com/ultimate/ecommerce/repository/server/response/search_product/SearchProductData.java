package com.ultimate.ecommerce.repository.server.response.search_product;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchProductData {
    @SerializedName("filters")
    private List<Filters> filters;
    @SerializedName("products")
    private List<Products> products;

    public SearchProductData() {
    }

    public List<Filters> getFilters() {
        return filters;
    }

    public void setFilters(List<Filters> filters) {
        this.filters = filters;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }
}
