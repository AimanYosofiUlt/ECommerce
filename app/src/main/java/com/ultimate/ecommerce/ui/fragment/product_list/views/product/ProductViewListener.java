package com.ultimate.ecommerce.ui.fragment.product_list.views.product;

import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;

public interface ProductViewListener {
    void onAddToCart(ProductCart productCart);
    void onClick(ProductAdapterData data);
    void addToFavorite(ProductAdapterData data);
    void removeFromFavorite(ProductAdapterData data);
}