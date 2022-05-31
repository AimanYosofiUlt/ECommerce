package com.ultimate.ecommerce.ui.fragment.cart.views.cart_product;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.databinding.ViewProductCartBinding;
import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;

public class CartProductViewHolder extends RecyclerView.ViewHolder {
    ProductCart data;
    CartProductViewListener listener;
    ViewProductCartBinding binding;

    public CartProductViewHolder(@NonNull View itemView, CartProductViewListener listener) {
        super(itemView);
        binding = ViewProductCartBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    public void bind(ProductCart data) {
        this.data = data;
        binding.name.setText(data.getProductTitle());
        // todo make sure you has the correct desc and note to show here
        // todo handle when shorDescription is hidden
        binding.desc.setText(data.getShortDescription());
        String quantity = String.valueOf(data.getProductQuantity());
        binding.quantityTV.setText(quantity);
        binding.priceTV.setText(data.getProductPrice());

    }

    private void initEvent() {
        binding.plusBtn.setOnClickListener(v -> {
            Integer productQuantity = data.getProductQuantity();
            listener.onQuantityChange(data, productQuantity + 1);
        });

        binding.minusBtn.setOnClickListener(v -> {
            Integer productQuantity = data.getProductQuantity();
            if (productQuantity == 0)
                listener.onCancel(data);
            else
                listener.onQuantityChange(data, productQuantity - 1);
        });

        binding.cancelBtn.setOnClickListener(v -> listener.onCancel(data));
    }
}