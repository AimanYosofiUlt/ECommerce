package com.ultimate.ecommerce.repository.server.response.contact_us;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContactUsData {
    @SerializedName("pageContent")
    private List<ContactUsContentData> pageContent;
    @SerializedName("pageDescription")
    private String pageDescription;
    @SerializedName("pageTitle")
    private String pageTitle;

    public ContactUsData() {
    }

    public List<ContactUsContentData> getPageContent() {
        return pageContent;
    }

    public void setPageContent(List<ContactUsContentData> pageContent) {
        this.pageContent = pageContent;
    }

    public String getPageDescription() {
        return pageDescription;
    }

    public void setPageDescription(String pageDescription) {
        this.pageDescription = pageDescription;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }
}
