package com.ultimate.ecommerce.repository.local.tables.cart;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class ProductCart {
    @PrimaryKey
    Integer productId;
    String productTitle;
    String productImageUrl;
    String productPrice;
    String shortDescription;
    String discountPercentage;
    Integer productRate;
    Integer productQuantity;

    public ProductCart() {
    }

    @Ignore
    public ProductCart(Integer productId, String productTitle
            , String productImageUrl, String productPrice
            , String shortDescription, String discountPercentage
            , Integer productRate, Integer productQuantity) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.productImageUrl = productImageUrl;
        this.productPrice = productPrice;
        this.shortDescription = shortDescription;
        this.discountPercentage = discountPercentage;
        this.productRate = productRate;
        this.productQuantity = productQuantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setProductRate(Integer productRate) {
        this.productRate = productRate;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public Integer getProductRate() {
        return productRate;
    }

    public String getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(String discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }
}
