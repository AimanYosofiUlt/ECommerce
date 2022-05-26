package com.ultimate.ecommerce.repository.local.tables.setting;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class AppSetting {
    @PrimaryKey(autoGenerate = true)
    Integer id;
    //todo change setting
    String tokenKey;

    public AppSetting() {
    }

    @Ignore
    public AppSetting(Integer id, String tokenKey) {
        this.id = id;
        this.tokenKey = tokenKey;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }
}
