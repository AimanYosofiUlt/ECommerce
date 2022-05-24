package com.ultimate.ecommerce.repository.server.response.search_product;

import com.google.gson.annotations.SerializedName;
import com.ultimate.ecommerce.repository.server.response.get_products.Categories;

import java.util.List;

public class Products {
    @SerializedName("stockQuantity")
    private int stockQuantity;
    @SerializedName("stockStatus")
    private String stockStatus;
    @SerializedName("manageStock")
    private boolean manageStock;
    @SerializedName("categories")
    private List<Categories> categories;
    @SerializedName("ratingCount")
    private int ratingCount;
    @SerializedName("averageRating")
    private String averageRating;
    @SerializedName("discountPercentage")
    private String discountPercentage;
    @SerializedName("salePrice")
    private String salePrice;
    @SerializedName("regularPrice")
    private String regularPrice;
    @SerializedName("rangePrice")
    private String rangePrice;
    @SerializedName("price")
    private String price;
    @SerializedName("onSale")
    private boolean onSale;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("shortDescription")
    private String shortDescription;
    @SerializedName("description")
    private String description;
    @SerializedName("title")
    private String title;
    @SerializedName("type")
    private String type;
    @SerializedName("id")
    private int id;

    public Products() {
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public boolean getManageStock() {
        return manageStock;
    }

    public void setManageStock(boolean manageStock) {
        this.manageStock = manageStock;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public String getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(String discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getRangePrice() {
        return rangePrice;
    }

    public void setRangePrice(String rangePrice) {
        this.rangePrice = rangePrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean getOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
