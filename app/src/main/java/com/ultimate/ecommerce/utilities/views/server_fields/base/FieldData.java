package com.ultimate.ecommerce.utilities.views.server_fields.base;

public class FieldData {
    private   String value = "";
    private   String hint;
    private boolean isRequired;

    public FieldData(String value, String hint, boolean isRequired) {
        this.value = value;
        this.hint = hint;
        this.isRequired = isRequired;
    }

    public FieldData(String hint, boolean isRequired) {
        this.hint = hint;
        this.isRequired = isRequired;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean isRequired) {
        isRequired = isRequired;
    }
}
