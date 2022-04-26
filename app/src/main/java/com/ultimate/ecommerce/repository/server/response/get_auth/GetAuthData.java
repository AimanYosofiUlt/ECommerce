package com.ultimate.ecommerce.repository.server.response.get_auth;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAuthData {
    @SerializedName("fields")
    private List<Fields> fields;
    @SerializedName("sms")
    private boolean sms;

    public GetAuthData() {
    }

    public List<Fields> getFields() {
        return fields;
    }

    public void setFields(List<Fields> fields) {
        this.fields = fields;
    }

    public boolean getSms() {
        return sms;
    }

    public void setSms(boolean sms) {
        this.sms = sms;
    }
}
