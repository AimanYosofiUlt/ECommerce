package com.ultimate.ecommerce.repository.server.response.about_us;

import com.google.gson.annotations.SerializedName;

public class SocialMedia {
    @SerializedName("whatsapp")
    private String whatsapp;
    @SerializedName("snapchat")
    private String snapchat;
    @SerializedName("instagram")
    private String instagram;
    @SerializedName("twitter")
    private String twitter;
    @SerializedName("facebook")
    private String facebook;

    public SocialMedia() {
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getSnapchat() {
        return snapchat;
    }

    public void setSnapchat(String snapchat) {
        this.snapchat = snapchat;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }
}
