package com.ultimate.ecommerce.ui.fragment.order_confirm_address.views.address;

import com.ultimate.ecommerce.repository.server.response.get_address_fields.GetAddressFieldData;

public class OrderAddress {
    boolean isSelected;
    GetAddressFieldData addressField;

    public OrderAddress(GetAddressFieldData addressField) {
        this.addressField = addressField;
        this.isSelected = false;
    }

    public GetAddressFieldData getAddressField() {
        return addressField;
    }
}
