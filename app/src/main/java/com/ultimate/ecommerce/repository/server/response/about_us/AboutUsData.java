package com.ultimate.ecommerce.repository.server.response.about_us;

import com.google.gson.annotations.SerializedName;

public class AboutUsData {
    @SerializedName("socialMedia")
    private SocialMedia socialMedia;
    @SerializedName("content")
    private String content;
    @SerializedName("title")
    private String title;
    @SerializedName("logo")
    private String logo;

    public AboutUsData() {
    }

    public SocialMedia getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(SocialMedia socialMedia) {
        this.socialMedia = socialMedia;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
