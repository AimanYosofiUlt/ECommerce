package com.ultimate.ecommerce.ui.fragment.home.views.banner_four;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.ViewBannerBinding;
import com.ultimate.ecommerce.repository.server.response.homepage.base.Banner;

public class BannerFourViewHolder extends RecyclerView.ViewHolder {
    Banner data;
    BannerFourViewListener listener;
    ViewBannerBinding binding;


    public BannerFourViewHolder(@NonNull View itemView, BannerFourViewListener listener) {
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