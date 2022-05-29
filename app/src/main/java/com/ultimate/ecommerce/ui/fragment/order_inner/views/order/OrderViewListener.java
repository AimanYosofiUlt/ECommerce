package com.ultimate.ecommerce.ui.fragment.order_inner.views.order;

import com.ultimate.ecommerce.repository.server.response.get_user_orders.Order;

public interface OrderViewListener {
    void onClick(Order data);
}