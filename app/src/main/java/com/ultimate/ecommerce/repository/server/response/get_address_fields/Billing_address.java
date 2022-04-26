package com.ultimate.ecommerce.repository.server.response.get_address_fields;

import com.google.gson.annotations.SerializedName;

public class Billing_address {
    @SerializedName("required")
    private boolean required;
    @SerializedName("keyboard_type")
    private String keyboard_type;
    @SerializedName("type")
    private String type;
    @SerializedName("value")
    private String value;
    @SerializedName("default_option")
    private String default_option;
    @SerializedName("placeholder")
    private String placeholder;
    @SerializedName("name")
    private String name;
    @SerializedName("hidden")
    private boolean hidden;

    public boolean getRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getKeyboard_type() {
        return keyboard_type;
    }

    public void setKeyboard_type(String keyboard_type) {
        this.keyboard_type = keyboard_type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDefault_option() {
        return default_option;
    }

    public void setDefault_option(String default_option) {
        this.default_option = default_option;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
