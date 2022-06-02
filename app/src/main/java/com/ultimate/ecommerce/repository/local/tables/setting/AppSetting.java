package com.ultimate.ecommerce.repository.local.tables.setting;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AppSetting {
    @PrimaryKey(autoGenerate = true)
    Integer id;

    @ColumnInfo(defaultValue = "en")
    String language;

    public AppSetting() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
