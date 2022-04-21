package com.ultimate.ecommerce.repository.server.response.configuration;

import com.google.gson.annotations.SerializedName;
import com.ultimate.ecommerce.repository.server.response.base.ResponseObject;

public abstract class ConfigurationResponse extends ResponseObject {
    Data data;

    public ConfigurationResponse() {
    }

    public ConfigurationResponse(String status, String msg, Data data) {
        super(status, msg);
        this.data = data;
    }

    public static class Data {
        @SerializedName("colors")
        private Colors colors;
        @SerializedName("loginField")
        private String loginField;
        @SerializedName("countriesVersion")
        private int countriesVersion;
        @SerializedName("tokenKey")
        private String tokenKey;
        @SerializedName("defaultLanguage")
        private String defaultLanguage;

        public Colors getColors() {
            return colors;
        }

        public void setColors(Colors colors) {
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

        public static class Colors {
            @SerializedName("reviewColor")
            private String reviewColor;
            @SerializedName("imageBackground")
            private String imageBackground;
            @SerializedName("gradientEndColor")
            private String gradientEndColor;
            @SerializedName("gradientStartColor")
            private String gradientStartColor;
            @SerializedName("secondColor")
            private String secondColor;
            @SerializedName("mainColor")
            private String mainColor;

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
    }
}
