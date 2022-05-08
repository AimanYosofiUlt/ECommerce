package com.ultimate.ecommerce.ui.fragment.product_list.views.product;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.repository.server.response.get_products.ProductData;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    List<ProductData> list;
    ProductViewListener listener;

    public ProductAdapter(ProductViewListener listener) {
        list = new ArrayList<>();
        this.listener = listener;
    }

    public void setList(List<ProductData> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_product, parent, false);
        return new ProductViewHolder(inflate, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}