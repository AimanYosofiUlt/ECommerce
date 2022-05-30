package com.ultimate.ecommerce.utilities;

public interface CustomDialogListener {
    void onClick();
    default void onCancel() {

    }
}
