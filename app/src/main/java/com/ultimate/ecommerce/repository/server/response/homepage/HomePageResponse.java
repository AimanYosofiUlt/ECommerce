package com.ultimate.ecommerce.repository.server.response.homepage;

import com.google.gson.annotations.SerializedName;
import com.ultimate.ecommerce.repository.server.response.base.ResponseObject;

public abstract class HomePageResponse extends ResponseObject {
    @SerializedName("data")
    private HomePageData data;

    public HomePageResponse() {
    }

    public HomePageResponse(String status, String msg, HomePageData data) {
        super(status, msg);
        this.data = data;
    }

    public HomePageData getData() {
        return data;
    }

    public void setData(HomePageData data) {
        this.data = data;
    }
}
