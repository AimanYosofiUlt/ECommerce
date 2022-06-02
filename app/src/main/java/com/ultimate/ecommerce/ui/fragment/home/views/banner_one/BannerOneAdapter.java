package com.ultimate.ecommerce.ui.fragment.home.views.banner_one;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.repository.server.response.homepage.base.Banner;

import java.util.ArrayList;
import java.util.List;

public class BannerOneAdapter extends RecyclerView.Adapter<BannerOneViewHolder> {
    List<Banner> list;
    BannerOneViewListener listener;

    public BannerOneAdapter(BannerOneViewListener listener) {
        list = new ArrayList<>();
        this.listener = listener;
    }

    public void setList(List<Banner> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BannerOneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_banner, parent, false);
        return new BannerOneViewHolder(inflate, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerOneViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}