package com.ultimate.ecommerce.repository.local.tables.page;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Page {
    public static final int CONTACT_US_ID = 0;
    public static final int ABOUT_US_ID = 1;
    public static final int HELP_US_ID = 2;
    @PrimaryKey
    Integer id;
    String jsonData;
    @ColumnInfo(defaultValue = "0")
    Integer version;

    public Page() {
    }

    @Ignore
    public Page(Integer id, String jsonData) {
        this.id = id;
        this.jsonData = jsonData;
    }

    public static int getContactUsId() {
        return CONTACT_US_ID;
    }

    public static int getAboutUsId() {
        return ABOUT_US_ID;
    }

    public static int getHelpUsId() {
        return HELP_US_ID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
