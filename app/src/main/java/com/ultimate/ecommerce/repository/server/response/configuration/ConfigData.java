package com.ultimate.ecommerce.repository.server.response.configuration;

import com.google.gson.annotations.SerializedName;

public class ConfigData {
    @SerializedName("defaultLanguage")
    private String defaultLanguage;
    @SerializedName("tokenKey")
    private String tokenKey;
    @SerializedName("countriesVersion")
    private int countriesVersion;@SerializedName("loginField")
    private String loginField;
    @SerializedName("colors")
    private ConfigColorData colors;

    public ConfigData() {
    }

    public ConfigData(String defaultLanguage, String tokenKey, int countriesVersion, String loginField, ConfigColorData colors) {
        this.defaultLanguage = defaultLanguage;
        this.tokenKey = tokenKey;
        this.countriesVersion = countriesVersion;
        this.loginField = loginField;
        this.colors = colors;
    }

    public ConfigColorData getColors() {
        return colors;
    }

    public void setColors(ConfigColorData colors) {
        this.colors = colors;
    }

    public String getLoginField() {
        return loginField;
    }

    public void setLoginField(String loginField) {
        this.loginField = loginField;
    }

    public int getCountriesVersion() {
        return countriesVersion;
    }

    public void setCountriesVersion(int countriesVersion) {
        this.countriesVersion = countriesVersion;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

}