package com.ultimate.ecommerce.ui.fragment.login;

import android.widget.EditText;

public class ValidateMessage {
   private final EditText editText;
   private final String Message;

    public ValidateMessage(EditText editText, String message) {
        this.editText = editText;
        Message = message;
    }

    public EditText getView() {
        return editText;
    }

    public String getMessage() {
        return Message;
    }
}
