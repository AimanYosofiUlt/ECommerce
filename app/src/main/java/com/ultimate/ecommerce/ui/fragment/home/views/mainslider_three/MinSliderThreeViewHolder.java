package com.ultimate.ecommerce.ui.fragment.home.views.mainslider_three;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.ViewImageBinding;
import com.ultimate.ecommerce.repository.server.response.homepage.base.Banner;

public class MinSliderThreeViewHolder extends RecyclerView.ViewHolder {
    Banner data;
//    MinSliderThreeViewListener listener;
//    ViewBannerBinding binding;
    ViewImageBinding binding;

    public MinSliderThreeViewHolder(@NonNull View itemView, MinSliderThreeViewListener listener) {
        super(itemView);
        binding = ViewImageBinding.bind(itemView);
//        this.listener = listener;
        initEvent();
    }

    public void bind(Banner data) {
//        this.data = data;
////        binding.bannerTitle.setText(data.getImagetitle());
//        Glide.with(binding.getRoot().getContext())
//                .load(data.getUrl())
//                .error(com.ultimate.ecommerce.R.drawable.ic_baseline_error_24)
//                .into(binding.imageView);
    }

    private void initEvent() {

    }
}