package com.ultimate.ecommerce.utilities.state;

public interface OnValidateListener {
    default boolean onValidate() {
        return true;
    }
}