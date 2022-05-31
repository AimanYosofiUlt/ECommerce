package com.ultimate.ecommerce.repository.local.tables.cart;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class ProductCart {
    @PrimaryKey
    Integer productId;
    String productTitle;
    Integer productRate;
    String discountPercentage;
    String productPrice;
    @ColumnInfo(defaultValue = "0")
    Integer productQuantity;
    String shortDescription;


    public ProductCart() {
    }

    @Ignore
    public ProductCart(Integer productId, String productTitle, Integer productRate,
                       String discountPercentage, String productPrice, Integer productQuantity, String shortDescription) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.productRate = productRate;
        this.discountPercentage = discountPercentage;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.shortDescription = shortDescription;
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

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
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
}
