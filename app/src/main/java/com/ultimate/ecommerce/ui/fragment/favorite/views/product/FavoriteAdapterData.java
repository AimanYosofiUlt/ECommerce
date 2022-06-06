package com.ultimate.ecommerce.ui.fragment.favorite.views.product;

import androidx.room.Embedded;
import androidx.room.Ignore;

import com.ultimate.ecommerce.repository.local.tables.favorite.Favorite;

public class FavoriteAdapterData {
    int cartQuantity = 0;
    @Embedded
    Favorite favorite;

    public FavoriteAdapterData() {
    }

    @Ignore
    public FavoriteAdapterData(Favorite favorite) {
        this.favorite = favorite;
    }

    @Ignore
    public FavoriteAdapterData(int cartQuantity, Favorite data) {
        this.cartQuantity = cartQuantity;
        this.favorite = data;
    }

    public int getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public Favorite getFavorite() {
        return favorite;
    }

    public void setFavorite(Favorite favorite) {
        this.favorite = favorite;
    }

    public int increaseQuantity() {
        return ++cartQuantity;
    }
}
