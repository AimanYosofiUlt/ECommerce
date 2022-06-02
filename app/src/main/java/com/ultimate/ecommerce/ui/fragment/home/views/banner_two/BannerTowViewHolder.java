package com.ultimate.ecommerce.ui.fragment.home.views.banner_two;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.ViewBannerBinding;
import com.ultimate.ecommerce.repository.server.response.homepage.base.Banner;

public class BannerTowViewHolder extends RecyclerView.ViewHolder {
    Banner data;
    BannerTowViewListener listener;
    ViewBannerBinding binding;


    public BannerTowViewHolder(@NonNull View itemView, BannerTowViewListener listener) {
        super(itemView);
        binding = ViewBannerBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    public void bind(Banner data) {
        this.data = data;
        binding.bannerTitle.setText(data.getImagetitle());
        Glide.with(binding.getRoot())
                .load(data.getUrl())
                .error(R.drawable.ic_baseline_error_24)
                .into(binding.bannerImg);
    }

    private void initEvent() {

    }
}