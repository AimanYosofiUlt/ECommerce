package com.ultimate.ecommerce.ui.fragment.product_detail.views.product_feature;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.databinding.ViewProductFeatureBinding;
import com.ultimate.ecommerce.repository.server.response.get_product.Tabs;

public class ProductFeatureViewHolder extends RecyclerView.ViewHolder {
    Tabs data;
    ProductFeatureViewListener listener;
    ViewProductFeatureBinding bd;


    public ProductFeatureViewHolder(@NonNull View itemView, ProductFeatureViewListener listener) {
        super(itemView);
        bd = ViewProductFeatureBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    public void bind(Tabs data) {
        this.data = data;
        bd.titleTV.setText(data.getTitle());
        bd.contentTV.setText(data.getContent());
    }

    private void initEvent() {

    }
}