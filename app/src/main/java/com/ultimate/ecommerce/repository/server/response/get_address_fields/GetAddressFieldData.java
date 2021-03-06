package com.ultimate.ecommerce.repository.server.response.get_address_fields;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAddressFieldData {
    @SerializedName(value = "billing_address",alternate = {"shipping_address"})
    private List<Address> addresses;
    @SerializedName("fields_count")
    private int fields_count;
    @SerializedName("title")
    private String title;
    @SerializedName("name")
    private String name;
    @SerializedName("hidden")
    private boolean hidden;

    public List<Address> getAddress() {
        return addresses;
    }

    public void setAddress(List<Address> address) {
        this.addresses = address;
    }

    public int getFields_count() {
        return fields_count;
    }

    public void setFields_count(int fields_count) {
        this.fields_count = fields_count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
