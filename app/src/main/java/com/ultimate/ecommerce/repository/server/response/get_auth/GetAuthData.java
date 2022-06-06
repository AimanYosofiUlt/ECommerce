package com.ultimate.ecommerce.repository.server.response.get_auth;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAuthData {
    @SerializedName("fields")
    private List<Field> fields;
    @SerializedName("sms")
    private boolean sms;

    public GetAuthData() {
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public boolean getSms() {
        return sms;
    }

    public void setSms(boolean sms) {
        this.sms = sms;
    }
}
