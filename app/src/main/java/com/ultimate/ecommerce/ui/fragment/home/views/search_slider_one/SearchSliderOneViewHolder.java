package com.ultimate.ecommerce.ui.fragment.home.views.search_slider_one;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.ViewImageBinding;
import com.ultimate.ecommerce.databinding.ViewImageSliderBinding;
import com.ultimate.ecommerce.databinding.ViewSliderBinding;
import com.ultimate.ecommerce.repository.server.response.homepage.base.Banner;

public class SearchSliderOneViewHolder extends RecyclerView.ViewHolder {
    Banner data;
    SearchSliderOneViewListener listener;
    ViewImageSliderBinding binding;


    public SearchSliderOneViewHolder(@NonNull View itemView, SearchSliderOneViewListener listener) {
        super(itemView);
        binding = ViewImageSliderBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    public void bind(Banner data) {
        this.data = data;
        Glide.with(binding.getRoot())
                .load(data.getUrl())
                .error(R.drawable.ic_baseline_error_24)
                .fitCenter()
                .into(binding.image );
    }

    private void initEvent() {

    }
}