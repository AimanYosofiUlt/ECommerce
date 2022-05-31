package com.ultimate.ecommerce.ui.fragment.order_confirm_shipment_method.views.shipment_method;

import com.ultimate.ecommerce.repository.server.response.shipping_methods.ShippingMethodsData;

public interface ShippingMethodViewListener {
    void onSelect(ShippingMethodsData data);
}