package com.ultimate.ecommerce.ui.fragment.product_list.views.sub_category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.repository.server.response.get_products.SubCategoryData;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryViewHolder> {
    List<SubCategoryData> list;
    SubCategoryViewListener listener;

    public SubCategoryAdapter(SubCategoryViewListener listener) {
        list = new ArrayList<>();
        this.listener = listener;
    }

    public void setList(List<SubCategoryData> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_subcategory, parent, false);
        return new SubCategoryViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
