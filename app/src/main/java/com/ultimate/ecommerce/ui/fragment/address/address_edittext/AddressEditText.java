package com.ultimate.ecommerce.ui.fragment.address.address_edittext;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ultimate.ecommerce.repository.server.response.get_address_fields.Address;
import com.ultimate.ecommerce.repository.server.response.get_address_fields.GetAddressFieldData;

import java.util.List;

public class AddressEditText {
    View view;
    EditText fieldEd;
    TextView optionalMsg;
    AddressEditTextListener listener;
    GetAddressFieldData data;
    public AddressEditText(Context context, AddressEditTextListener listener) {
        this.listener = listener;
        List<Address> address = data.getAddress();
        init(context);
    }

    private void init(Context context) {
//        int layoutId = getLayoutId(field.getName());
//        view = LayoutInflater.from(context).inflate(layoutId, null, false);
//        fieldEd = view.findViewById(R.id.fieldED);
//        optionalMsg = view.findViewById(R.id.optionalMsg);
//        initByType(context);
    }
}
