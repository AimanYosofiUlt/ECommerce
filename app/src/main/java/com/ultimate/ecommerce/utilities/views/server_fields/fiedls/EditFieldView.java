package com.ultimate.ecommerce.utilities.views.server_fields.fiedls;

import android.content.Context;
import android.view.LayoutInflater;

import com.ultimate.ecommerce.databinding.ViewEdittextBinding;
import com.ultimate.ecommerce.utilities.views.server_fields.base.BaseFieldView;
import com.ultimate.ecommerce.utilities.views.server_fields.base.FieldData;
import com.ultimate.ecommerce.utilities.views.server_fields.base.BaseValidate;

public class EditFieldView extends BaseFieldView {
    ViewEdittextBinding binding;

    public EditFieldView(Context context, FieldData data, BaseValidate validate) {
        super(data, validate);
        binding = ViewEdittextBinding.inflate(LayoutInflater.from(context));
        setViewContent(binding.getRoot());
    }
}
