package com.ultimate.ecommerce.repository.server.response.add_user;

import com.google.gson.annotations.SerializedName;

public class UserData {
    @SerializedName("display_name")
    private String displayName;
    @SerializedName("user_status")
    private String userStatus;
    @SerializedName("user_activation_key")
    private String userActivationKey;
    @SerializedName("user_registered")
    private String userRegistered;
    @SerializedName("user_url")
    private String userUrl;
    @SerializedName("user_email")
    private String userEmail;
    @SerializedName("user_nicename")
    private String userNicename;
    @SerializedName("user_pass")
    private String userPass;
    @SerializedName("user_login")
    private String userLogin;
    @SerializedName("ID")
    private String id;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserActivationKey() {
        return userActivationKey;
    }

    public void setUserActivationKey(String userActivationKey) {
        this.userActivationKey = userActivationKey;
    }

    public String getUserRegistered() {
        return userRegistered;
    }

    public void setUserRegistered(String userRegistered) {
        this.userRegistered = userRegistered;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserNicename() {
        return userNicename;
    }

    public void setUserNicename(String userNicename) {
        this.userNicename = userNicename;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
