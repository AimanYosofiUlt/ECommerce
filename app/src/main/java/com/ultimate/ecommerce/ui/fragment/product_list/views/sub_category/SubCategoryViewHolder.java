package com.ultimate.ecommerce.ui.fragment.product_list.views.sub_category;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.databinding.ViewSubcategoryBinding;
import com.ultimate.ecommerce.repository.server.response.get_products.SubCategoryData;

public class SubCategoryViewHolder extends RecyclerView.ViewHolder {
    SubCategoryData data;
    SubCategoryViewListener listener;
    ViewSubcategoryBinding bd;


    public SubCategoryViewHolder(@NonNull View itemView, SubCategoryViewListener listener) {
        super(itemView);
        bd = ViewSubcategoryBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    public void bind(SubCategoryData data) {
        this.data = data;
        bd.subCategoryTV.setText(data.getTitle());
    }

    private void initEvent() {

    }
}