package com.ultimate.ecommerce.repository.server.response.get_products;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetProductsData {
    @SerializedName("pages")
    private int pages;
    @SerializedName("filters")
    private List<FiltersData> filters;
    @SerializedName("products")
    private List<ProductData> products;
    @SerializedName("subCategories")
    private List<SubCategoryData> subCategories;

    public GetProductsData() {
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<FiltersData> getFilters() {
        return filters;
    }

    public void setFilters(List<FiltersData> filters) {
        this.filters = filters;
    }

    public List<ProductData> getProducts() {
        return products;
    }

    public void setProducts(List<ProductData> products) {
        this.products = products;
    }

    public List<SubCategoryData> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategoryData> subCategories) {
        this.subCategories = subCategories;
    }
}
