package com.ultimate.ecommerce.ui.fragment.product_list.views.filter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.databinding.ViewFilterBinding;

public class FilterViewHolder extends RecyclerView.ViewHolder {
    String data;
    FilterViewListener listener;
    ViewFilterBinding bd;

    public FilterViewHolder(@NonNull View itemView, FilterViewListener listener) {
        super(itemView);
        bd = ViewFilterBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    public void bind(String data) {
        this.data = data;
        bd.title.setText(data);
    }

    private void initEvent() {
        bd.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemCheck(data);
            }
        });
    }
}