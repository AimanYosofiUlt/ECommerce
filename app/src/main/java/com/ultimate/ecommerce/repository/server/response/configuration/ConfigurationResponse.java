package com.ultimate.ecommerce.repository.server.response.configuration;

import com.google.gson.annotations.SerializedName;
import com.ultimate.ecommerce.repository.server.response.base.ResponseObject;

public class ConfigurationResponse extends ResponseObject {
    @SerializedName("data")
    ConfigData data;

    public ConfigurationResponse() {
        super();
    }

    public ConfigurationResponse(String status, String msg, ConfigData data) {
        super(status, msg);
        this.data = data;
    }

    public ConfigData getData() {
        return data;
    }

    public void setData(ConfigData data) {
        this.data = data;
    }
}
