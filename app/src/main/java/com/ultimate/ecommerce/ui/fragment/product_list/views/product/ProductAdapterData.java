package com.ultimate.ecommerce.ui.fragment.product_list.views.product;

import com.ultimate.ecommerce.repository.server.response.get_products.ProductData;

import java.io.Serializable;

public class ProductAdapterData implements Serializable {
    private ProductData data;
    private int cartQuantity = 0;
    private boolean isInFavorite = false;

    public ProductAdapterData(ProductData data, int cartQuantity, boolean isInFavorite) {
        this.data = data;
        this.cartQuantity = cartQuantity;
        this.isInFavorite = isInFavorite;
    }

    public ProductAdapterData(ProductData data) {
        this.data = data;
    }

    public ProductData getData() {
        return data;
    }

    public int increaseQuantity() {
        return ++cartQuantity;
    }

    public int getCartQuantity() {
        return cartQuantity;
    }

    public boolean isInFavorite() {
        return isInFavorite;
    }

    public void setInFavorite(boolean inFavorite) {
        isInFavorite = inFavorite;
    }
}
