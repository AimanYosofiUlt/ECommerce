package com.ultimate.ecommerce.ui.fragment.favorite.views.product;

import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;
import com.ultimate.ecommerce.repository.local.tables.favorite.Favorite;
import com.ultimate.ecommerce.ui.fragment.product_list.views.product.ProductAdapterData;

public interface FavoriteProductViewListener {
    void onAddToCart(ProductCart productCart);
    void onClick(Favorite data);
    void removeFromFavorite(Favorite data);
}