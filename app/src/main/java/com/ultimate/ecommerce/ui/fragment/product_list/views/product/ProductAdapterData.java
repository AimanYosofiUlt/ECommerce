package com.ultimate.ecommerce.ui.fragment.product_list.views.product;

import com.ultimate.ecommerce.repository.server.response.get_products.ProductData;

public class ProductAdapterData {
    ProductData data;

    public ProductAdapterData(ProductData data) {
        this.data = data;
    }

    public ProductData getData() {
        return data;
    }
}
