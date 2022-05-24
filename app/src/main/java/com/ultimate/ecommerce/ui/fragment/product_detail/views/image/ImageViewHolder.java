package com.ultimate.ecommerce.ui.fragment.product_detail.views.image;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ultimate.ecommerce.databinding.ToolsViewpagerImageBinding;
import com.ultimate.ecommerce.repository.server.response.get_product.Image;

public class ImageViewHolder extends RecyclerView.ViewHolder {
    Image data;
    ImageViewListener listener;
    ToolsViewpagerImageBinding bd;


    public ImageViewHolder(@NonNull View itemView, ImageViewListener listener) {
        super(itemView);
        bd = ToolsViewpagerImageBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    public void bind(Image data) {
        this.data = data;
        Glide.with(itemView.getContext())
                .load(data.getImgUrl())
                .into(bd.image);
    }

    private void initEvent() {

    }
}