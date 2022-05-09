package com.ultimate.ecommerce.repository.local.tables.favorite;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Favorite {
    @PrimaryKey
    Integer productId;
}
