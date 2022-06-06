package com.ultimate.ecommerce.repository.local.tables.auth;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Auth {
    public static final String LOGIN = "login";
    public static final String REGISTER = "register";

    @PrimaryKey(autoGenerate = true)
    Integer id;
    String screenName;
    String jsonData;

    public Auth() {
    }

    @Ignore
    public Auth(String screenName, String jsonData) {
        this.screenName = screenName;
        this.jsonData = jsonData;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }
}
