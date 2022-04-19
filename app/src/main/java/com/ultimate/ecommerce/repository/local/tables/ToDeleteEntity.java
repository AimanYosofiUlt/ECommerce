package com.ultimate.ecommerce.repository.local.tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ToDeleteEntity {
    @PrimaryKey(autoGenerate = true)
    Integer id;

    public ToDeleteEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
