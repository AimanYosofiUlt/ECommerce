package com.ultimate.ecommerce.ui.fragment.cart;

import com.ultimate.ecommerce.repository.server.response.update_cart.UpdateCartData;

public interface CartFragmentListener {
    void onOrderConfirmReq(UpdateCartData data);
}
