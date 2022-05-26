package com.ultimate.ecommerce.ui.fragment.order_inner.views.order;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.databinding.ViewOrderBinding;
import com.ultimate.ecommerce.repository.server.response.get_user_orders.Order;

public class OrderViewHolder extends RecyclerView.ViewHolder {
    Order data;
    OrderViewListener listener;
    ViewOrderBinding bd;


    public OrderViewHolder(@NonNull View itemView, OrderViewListener listener) {
        super(itemView);
        bd = ViewOrderBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    public void bind(Order data) {
        this.data = data;

    }

    private void initEvent() {

    }
}