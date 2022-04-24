package com.ultimate.ecommerce.repository.server.response.payment_methods;

import com.google.gson.annotations.SerializedName;

public class PaymentMethodsData {
    @SerializedName("publishable_key")
    private String publishable_key;
    @SerializedName("secret_key")
    private String secret_key;
    @SerializedName("description")
    private String description;
    @SerializedName("title")
    private String title;
    @SerializedName("icon")
    private String icon;
    @SerializedName("type")
    private String type;
    @SerializedName("payment_request")
    private boolean payment_request;
    @SerializedName("test_mode")
    private boolean test_mode;
    @SerializedName("name")
    private String name;

    public String getPublishable_key() {
        return publishable_key;
    }

    public void setPublishable_key(String publishable_key) {
        this.publishable_key = publishable_key;
    }

    public String getSecret_key() {
        return secret_key;
    }

    public void setSecret_key(String secret_key) {
        this.secret_key = secret_key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getPayment_request() {
        return payment_request;
    }

    public void setPayment_request(boolean payment_request) {
        this.payment_request = payment_request;
    }

    public boolean getTest_mode() {
        return test_mode;
    }

    public void setTest_mode(boolean test_mode) {
        this.test_mode = test_mode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
