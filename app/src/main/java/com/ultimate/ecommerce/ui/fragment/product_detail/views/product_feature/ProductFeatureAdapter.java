package com.ultimate.ecommerce.ui.fragment.product_detail.views.product_feature;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.repository.server.response.get_product.Tabs;

import java.util.ArrayList;
import java.util.List;

public class ProductFeatureAdapter extends RecyclerView.Adapter<ProductFeatureViewHolder> {
    List<Tabs> list;
    ProductFeatureViewListener listener;

    public ProductFeatureAdapter(ProductFeatureViewListener listener) {
        list = new ArrayList<>();
        this.listener = listener;
    }

    public void setList(List<Tabs> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductFeatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_product_feature, parent, false);
        return new ProductFeatureViewHolder(inflate, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductFeatureViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
