package com.ultimate.ecommerce.utilities.views.server_fields.base;

public class BaseValidate {
    private  boolean isRequired;

    void setRequired(boolean isRequired){
        this.isRequired = isRequired;
    }
    public boolean isRequired() {
        return isRequired;
    }
}
