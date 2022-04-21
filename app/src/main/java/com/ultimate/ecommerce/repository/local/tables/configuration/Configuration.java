package com.ultimate.ecommerce.repository.local.tables.configuration;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Configuration")
public class Configuration {
    @PrimaryKey(autoGenerate = true)
    Integer id;
    @ColumnInfo(defaultValue = "en")
    String defaultLanguage;
    @ColumnInfo(defaultValue = "123456")
    String tokenKey;
    @ColumnInfo(defaultValue = "1")
    Integer countriesVersion;
    @ColumnInfo(defaultValue = "phone")
    String loginField;
    @ColumnInfo(defaultValue = "#37123C")
    String mainColor;
    @ColumnInfo(defaultValue = "#AFA8B1")
    String secondColor;
    @ColumnInfo(defaultValue = "#FE938C")
    String gradientStartColor;
    @ColumnInfo(defaultValue = "#FFF87C")
    String gradientEndColor;
    @ColumnInfo(defaultValue = "#FFEDEC")
    String imageBackground;
    @ColumnInfo(defaultValue = "#E7E3E7")
    String reviewColor;

    public Configuration() {
    }

    @Ignore
    public Configuration(Integer id, String defaultLanguage, String tokenKey, Integer countriesVersion, String loginField, String mainColor, String secondColor, String gradientStartColor, String gradientEndColor, String imageBackground, String reviewColor) {
        this.id = id;
        this.defaultLanguage = defaultLanguage;
        this.tokenKey = tokenKey;
        this.countriesVersion = countriesVersion;
        this.loginField = loginField;
        this.mainColor = mainColor;
        this.secondColor = secondColor;
        this.gradientStartColor = gradientStartColor;
        this.gradientEndColor = gradientEndColor;
        this.imageBackground = imageBackground;
        this.reviewColor = reviewColor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public Integer getCountriesVersion() {
        return countriesVersion;
    }

    public void setCountriesVersion(Integer countriesVersion) {
        this.countriesVersion = countriesVersion;
    }

    public String getLoginField() {
        return loginField;
    }

    public void setLoginField(String loginField) {
        this.loginField = loginField;
    }

    public String getMainColor() {
        return mainColor;
    }

    public void setMainColor(String mainColor) {
        this.mainColor = mainColor;
    }

    public String getSecondColor() {
        return secondColor;
    }

    public void setSecondColor(String secondColor) {
        this.secondColor = secondColor;
    }

    public String getGradientStartColor() {
        return gradientStartColor;
    }

    public void setGradientStartColor(String gradientStartColor) {
        this.gradientStartColor = gradientStartColor;
    }

    public String getGradientEndColor() {
        return gradientEndColor;
    }

    public void setGradientEndColor(String gradientEndColor) {
        this.gradientEndColor = gradientEndColor;
    }

    public String getImageBackground() {
        return imageBackground;
    }

    public void setImageBackground(String imageBackground) {
        this.imageBackground = imageBackground;
    }

    public String getReviewColor() {
        return reviewColor;
    }

    public void setReviewColor(String reviewColor) {
        this.reviewColor = reviewColor;
    }
}
