package com.ultimate.ecommerce.ui.fragment.product_list.views.product;

import com.ultimate.ecommerce.repository.server.response.get_products.ProductData;

import java.io.Serializable;

public class ProductAdapterData implements Serializable {
    ProductData data;

    public ProductAdapterData(ProductData data) {
        this.data = data;
    }

    public ProductData getData() {
        return data;
    }
}
