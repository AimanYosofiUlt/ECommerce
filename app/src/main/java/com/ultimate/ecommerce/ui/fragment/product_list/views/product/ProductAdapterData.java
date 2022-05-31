package com.ultimate.ecommerce.ui.fragment.product_list.views.product;

import com.ultimate.ecommerce.repository.server.response.get_products.ProductData;

import java.io.Serializable;

public class ProductAdapterData implements Serializable {
    int cartQuantity;
    ProductData data;

    public ProductAdapterData(ProductData data, int cartQuantity) {
        this.data = data;
        this.cartQuantity = cartQuantity;
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
}
