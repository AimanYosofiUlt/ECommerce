package com.ultimate.ecommerce.repository.server.response.get_product;

import com.google.gson.annotations.SerializedName;

public class ShortDescription {
    @SerializedName("content")
    private String content;
    @SerializedName("hidden")
    private boolean hidden;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
