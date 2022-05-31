package com.ultimate.ecommerce.ui.fragment.cart.views.cart_product;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;

import java.util.ArrayList;
import java.util.List;

public class CartProductAdapter extends RecyclerView.Adapter<CartProductViewHolder> {
    List<ProductCart> list;
    CartProductViewListener listener;

    public CartProductAdapter(CartProductViewListener listener) {
        list = new ArrayList<>();
        this.listener = listener;
    }

    public void setList(List<ProductCart> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<ProductCart> getList() {
        return list;
    }

    @NonNull
    @Override
    public CartProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_product_cart, parent, false);
        return new CartProductViewHolder(inflate, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CartProductViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}