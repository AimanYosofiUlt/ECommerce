package com.ultimate.ecommerce.repository.server.response.search_product;

import com.google.gson.annotations.SerializedName;
import com.ultimate.ecommerce.repository.server.response.get_products.ProductData;

import java.util.List;

public class SearchProductData {
    @SerializedName("filters")
    private List<Filters> filters;
    @SerializedName("products")
    private List<ProductData> products;

    public SearchProductData() {
    }

    public List<Filters> getFilters() {
        return filters;
    }

    public void setFilters(List<Filters> filters) {
        this.filters = filters;
    }

    public List<ProductData> getProducts() {
        return products;
    }

    public void setProducts(List<ProductData> products) {
        this.products = products;
    }
}
