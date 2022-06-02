package com.ultimate.ecommerce.ui.fragment.category.views;

import android.graphics.Color;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.app.DynamicTheme;
import com.ultimate.ecommerce.databinding.ViewCategoryBinding;
import com.ultimate.ecommerce.repository.local.tables.category.Category;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "CategoryViewHolder";
    ViewCategoryBinding bd;

    Category category;
    CategoryViewListener listener;

    public CategoryViewHolder(@NonNull View itemView, CategoryViewListener listener) {
        super(itemView);
        bd = ViewCategoryBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    private void initEvent() {
        bd.logoRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onOpenReq(category);
            }
        });
    }

    public void bind(Category category) {
        this.category = category;
        bd.title.setText(category.getTitle());

        Glide.with(itemView)
                .load(category.getImage())
                .error(R.drawable.ic_baseline_error_24)
                .into(bd.categoryImg);

        int gradientStartColor = DynamicTheme.gradientStartColor;
        int gradientEndColor = DynamicTheme.gradientEndColor;
        try {
            gradientStartColor = Color.parseColor(category.getGradientStartColor());
            gradientEndColor = Color.parseColor(category.getGradientEndColor());
        } catch (Exception exception) {
            Log.d(TAG, "bind: "+exception.getMessage());
        }

        bd.back.setGradient(gradientStartColor, gradientEndColor);
    }
}
