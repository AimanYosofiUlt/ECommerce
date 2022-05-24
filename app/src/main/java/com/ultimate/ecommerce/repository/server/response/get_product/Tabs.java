package com.ultimate.ecommerce.repository.server.response.get_product;

import com.google.gson.annotations.SerializedName;

public class Tabs {
    @SerializedName("content")
    private String content;
    @SerializedName("title")
    private String title;
    @SerializedName("hidden")
    private boolean hidden;
    @SerializedName("name")
    private String name;

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

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
