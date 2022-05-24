package com.ultimate.ecommerce.repository.server.response.get_product;

import com.google.gson.annotations.SerializedName;
import com.ultimate.ecommerce.repository.server.response.get_products.Categories;

import java.util.List;

public class GetProductData {
    @SerializedName("tabs")
    private List<Tabs> tabs;
    @SerializedName("reviewsTab")
    private ReviewsTab reviewstab;
    @SerializedName("crossSell")
    private Crosssell crosssell;
    @SerializedName("upsell")
    private Upsell upsell;
    @SerializedName("relatedProducts")
    private RelatedProducts relatedProducts;
    @SerializedName("types")
    private List<String> types;
    @SerializedName("categories")
    private List<Categories> categories;
    @SerializedName("images")
    private List<Image> images;
    @SerializedName("dimensions")
    private Dimensions dimensions;
    @SerializedName("stockQuantity")
    private int stockQuantity;
    @SerializedName("stockStatus")
    private String stockStatus;
    @SerializedName("manageStock")
    private boolean manageStock;
    @SerializedName("dateOnSaleTo")
    private String dateOnSaleTo;
    @SerializedName("dateOnSaleFrom")
    private String dateOnSaleFrom;
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
    @SerializedName("sku")
    private String sku;
    @SerializedName("shortDescription")
    private ShortDescription shortDescription;
    @SerializedName("title")
    private String title;
    @SerializedName("slug")
    private String slug;
    @SerializedName("type")
    private String type;
    @SerializedName("variation_id")
    private int variationId;
    @SerializedName("id")
    private int id;

    public List<Tabs> getTabs() {
        return tabs;
    }

    public void setTabs(List<Tabs> tabs) {
        this.tabs = tabs;
    }

    public ReviewsTab getReviewstab() {
        return reviewstab;
    }

    public void setReviewstab(ReviewsTab reviewstab) {
        this.reviewstab = reviewstab;
    }

    public Crosssell getCrosssell() {
        return crosssell;
    }

    public void setCrosssell(Crosssell crosssell) {
        this.crosssell = crosssell;
    }

    public Upsell getUpsell() {
        return upsell;
    }

    public void setUpsell(Upsell upsell) {
        this.upsell = upsell;
    }

    public RelatedProducts getRelatedProducts() {
        return relatedProducts;
    }

    public void setRelatedProducts(RelatedProducts relatedProducts) {
        this.relatedProducts = relatedProducts;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
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

    public boolean isManageStock() {
        return manageStock;
    }

    public void setManageStock(boolean manageStock) {
        this.manageStock = manageStock;
    }

    public String getDateOnSaleTo() {
        return dateOnSaleTo;
    }

    public void setDateOnSaleTo(String dateOnSaleTo) {
        this.dateOnSaleTo = dateOnSaleTo;
    }

    public String getDateOnSaleFrom() {
        return dateOnSaleFrom;
    }

    public void setDateOnSaleFrom(String dateOnSaleFrom) {
        this.dateOnSaleFrom = dateOnSaleFrom;
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

    public boolean isOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public ShortDescription getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(ShortDescription shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getVariationId() {
        return variationId;
    }

    public void setVariationId(int variationId) {
        this.variationId = variationId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
