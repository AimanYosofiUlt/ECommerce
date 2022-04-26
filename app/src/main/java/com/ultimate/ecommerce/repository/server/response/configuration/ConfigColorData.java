package com.ultimate.ecommerce.repository.server.response.configuration;


import com.google.gson.annotations.SerializedName;

public class ConfigColorData {
    @SerializedName("mainColor")
    private String mainColor;
    @SerializedName("secondColor")
    private String secondColor;

    @SerializedName("gradientStartColor")
    private String gradientStartColor;
    @SerializedName("gradientEndColor")
    private String gradientEndColor;

    @SerializedName("imageBackground")
    private String imageBackground;
    @SerializedName("reviewColor")
    private String reviewColor;

    public ConfigColorData() {
    }

    public ConfigColorData(String mainColor, String secondColor, String gradientStartColor, String gradientEndColor, String imageBackground, String reviewColor) {
        this.mainColor = mainColor;
        this.secondColor = secondColor;
        this.gradientStartColor = gradientStartColor;
        this.gradientEndColor = gradientEndColor;
        this.imageBackground = imageBackground;
        this.reviewColor = reviewColor;
    }

    public String getReviewColor() {
        return reviewColor;
    }

    public void setReviewColor(String reviewColor) {
        this.reviewColor = reviewColor;
    }

    public String getImageBackground() {
        return imageBackground;
    }

    public void setImageBackground(String imageBackground) {
        this.imageBackground = imageBackground;
    }

    public String getGradientEndColor() {
        return gradientEndColor;
    }

    public void setGradientEndColor(String gradientEndColor) {
        this.gradientEndColor = gradientEndColor;
    }

    public String getGradientStartColor() {
        return gradientStartColor;
    }

    public void setGradientStartColor(String gradientStartColor) {
        this.gradientStartColor = gradientStartColor;
    }

    public String getSecondColor() {
        return secondColor;
    }

    public void setSecondColor(String secondColor) {
        this.secondColor = secondColor;
    }

    public String getMainColor() {
        return mainColor;
    }

    public void setMainColor(String mainColor) {
        this.mainColor = mainColor;
    }
}
