package com.ultimate.ecommerce.ui.fragment.order_detail.views.order_info;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.databinding.ViewOrderProductBinding;
import com.ultimate.ecommerce.repository.server.response.get_order.Product;

public class OrderProductViewHolder extends RecyclerView.ViewHolder {
    Product data;
    OrderInfoViewListener listener;
    ViewOrderProductBinding binding;


    public OrderProductViewHolder(@NonNull View itemView, OrderInfoViewListener listener) {
        super(itemView);
        binding = ViewOrderProductBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    public void bind(Product data) {
        this.data = data;
        binding.name.setText(data.getTitle());

        String quantity = "x" + data.getQuantity();
        binding.countTV.setText(quantity);

        String total = String.valueOf(data.getTotalprice());
        binding.totalPriceTV.setText(total);

    }

    private void initEvent() {

    }
}