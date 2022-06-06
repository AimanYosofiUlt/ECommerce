package com.ultimate.ecommerce.repository.server.response.get_auth;

import com.google.gson.annotations.SerializedName;

public class Field {
    @SerializedName("required")
    private boolean required;
    @SerializedName("keyboard_type")
    private String keyboard_type;
    @SerializedName("type")
    private String type;
    @SerializedName("placeholder")
    private String placeholder;
    @SerializedName("name")
    private String name;
    @SerializedName("hidden")
    private boolean hidden;

    public Field() {
    }

    public boolean isRequired() {
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

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
