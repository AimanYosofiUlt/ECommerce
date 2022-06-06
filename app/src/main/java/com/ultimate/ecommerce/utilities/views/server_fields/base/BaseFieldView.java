package com.ultimate.ecommerce.utilities.views.server_fields.base;

import android.view.View;

public abstract class BaseFieldView {
    private FieldData data;
    private BaseValidate validate;
    private View itemView;

    public BaseFieldView(FieldData data, BaseValidate validate) {
        this.data = data;
        this.validate = validate;
        validate.setRequired(data.isRequired());
    }

    public void setViewContent(View itemView) {
        this.itemView = itemView;
    }

    public View getRootView() {
        return itemView;
    }
}
