package com.ultimate.ecommerce.ui.fragment.home.views.search_slider_one;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.ViewBanner2Binding;
import com.ultimate.ecommerce.repository.server.response.homepage.base.Banner;

public class SearchSliderOneViewHolder extends RecyclerView.ViewHolder {
    Banner data;
    SearchSliderOneViewListener listener;
    ViewBanner2Binding binding;


    public SearchSliderOneViewHolder(@NonNull View itemView, SearchSliderOneViewListener listener) {
        super(itemView);
        binding = ViewBanner2Binding.bind(itemView);
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