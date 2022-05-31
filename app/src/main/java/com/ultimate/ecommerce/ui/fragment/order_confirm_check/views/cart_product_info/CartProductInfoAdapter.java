package com.ultimate.ecommerce.ui.fragment.order_confirm_check.views.cart_product_info;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;

import java.util.ArrayList;
import java.util.List;

public class CartProductInfoAdapter extends RecyclerView.Adapter<CartProductInfoViewHolder> {
    List<ProductCart> list;
    CartProductInfoViewListener listener;

    public CartProductInfoAdapter(CartProductInfoViewListener listener) {
        list = new ArrayList<>();
        this.listener = listener;
    }

    public void setList(List<ProductCart> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartProductInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_order_product, parent, false);
        return new CartProductInfoViewHolder(inflate, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CartProductInfoViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}