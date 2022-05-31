package com.ultimate.ecommerce.ui.fragment.order_confirm_payment_method.views.payment_method;

import com.ultimate.ecommerce.repository.server.response.payment_methods.PaymentMethodsData;
import com.ultimate.ecommerce.ui.fragment.order_confirm_address.views.address.OrderAddress;

public interface PaymentMethodsViewListener {
    void onSelect(PaymentMethodsData data);
}