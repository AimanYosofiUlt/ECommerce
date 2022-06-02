package com.ultimate.ecommerce.repository.local.user;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @NonNull
    @PrimaryKey
    String id = "0";
    String userName;
    String userPhone;
    String userEmail;
    String password;
    String tokenKey;
    boolean isSubscriber;

    public User() {

    }

    @Ignore
    public User(@NonNull String id, String userName, String userPhone, String userEmail,String password, String tokenKey, boolean isSubscriber) {
        this.id = id;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.password = password;
        this.tokenKey = tokenKey;
        this.isSubscriber = isSubscriber;
    }


    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public boolean isSubscriber() {
        return isSubscriber;
    }

    public void setSubscriber(boolean subscriber) {
        isSubscriber = subscriber;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
