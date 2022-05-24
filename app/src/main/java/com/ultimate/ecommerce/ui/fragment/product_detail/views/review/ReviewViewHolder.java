package com.ultimate.ecommerce.ui.fragment.product_detail.views.review;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.databinding.ViewReviewBinding;

public class ReviewViewHolder extends RecyclerView.ViewHolder {
    String data;
    ReviewViewListener listener;
    ViewReviewBinding bd;


    public ReviewViewHolder(@NonNull View itemView, ReviewViewListener listener) {
        super(itemView);
        bd = ViewReviewBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    public void bind(String data) {
        this.data = data;

    }

    private void initEvent() {

    }
}