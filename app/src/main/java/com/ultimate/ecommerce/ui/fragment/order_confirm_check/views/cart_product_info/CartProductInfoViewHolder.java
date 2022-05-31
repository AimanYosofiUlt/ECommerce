package com.ultimate.ecommerce.ui.fragment.order_confirm_check.views.cart_product_info;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.databinding.ViewOrderProductBinding;
import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;

public class CartProductInfoViewHolder extends RecyclerView.ViewHolder {
    ProductCart data;
    CartProductInfoViewListener listener;
    ViewOrderProductBinding binding;


    public CartProductInfoViewHolder(@NonNull View itemView, CartProductInfoViewListener listener) {
        super(itemView);
        binding = ViewOrderProductBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    public void bind(ProductCart data) {
        this.data = data;
        binding.name.setText(data.getProductTitle());

        String quantity = "x" + data.getProductQuantity();
        binding.countTV.setText(quantity);

        String total = String.valueOf(data.getProductPrice());
        binding.totalPriceTV.setText(total);
    }

    private void initEvent() {

    }
}