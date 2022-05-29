package com.ultimate.ecommerce.ui.fragment.order_detail.views.order_info;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.repository.server.response.get_order.Product;
import com.ultimate.ecommerce.R;

import java.util.ArrayList;
import java.util.List;

public class OrderInfoAdapter extends RecyclerView.Adapter<OrderProductViewHolder> {
    List<Product> list;
    OrderInfoViewListener listener;

    public OrderInfoAdapter(OrderInfoViewListener listener) {
        list = new ArrayList<>();
        this.listener = listener;
    }

    public void setList(List<Product> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_order_product, parent, false);
        return new OrderProductViewHolder(inflate, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderProductViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
