package com.ultimate.ecommerce.ui.fragment.home.views.mainslider_two;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.repository.server.response.homepage.base.Banner;

import java.util.ArrayList;
import java.util.List;

public class MinSliderTowAdapter extends RecyclerView.Adapter<MinSliderTowViewHolder> {
    List<Banner> list;
    MinSliderTowViewListener listener;

    public MinSliderTowAdapter(MinSliderTowViewListener listener) {
        list = new ArrayList<>();
        this.listener = listener;
    }

    public void setList(List<Banner> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MinSliderTowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_banner, parent, false);
        return new MinSliderTowViewHolder(inflate, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MinSliderTowViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}