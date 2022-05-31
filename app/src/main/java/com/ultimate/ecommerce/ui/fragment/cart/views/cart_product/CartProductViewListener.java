package com.ultimate.ecommerce.ui.fragment.cart.views.cart_product;

import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;

public interface CartProductViewListener {
    void onQuantityChange(ProductCart data, int qty);
    void onCancel(ProductCart data);
}