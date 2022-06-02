package com.ultimate.ecommerce.ui.fragment.home.views.banner_two;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.repository.server.response.homepage.base.Banner;

import java.util.ArrayList;
import java.util.List;

public class BannerTowAdapter extends RecyclerView.Adapter<BannerTowViewHolder> {
    List<Banner> list;
    BannerTowViewListener listener;

    public BannerTowAdapter(BannerTowViewListener listener) {
        list = new ArrayList<>();
        this.listener = listener;
    }

    public void setList(List<Banner> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BannerTowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_banner, parent, false);
        return new BannerTowViewHolder(inflate, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerTowViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}