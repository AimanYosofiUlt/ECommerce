package com.ultimate.ecommerce.ui.fragment.home.views.search_slider_one;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.repository.server.response.homepage.base.Banner;

import java.util.ArrayList;
import java.util.List;

public class SearchSliderOneAdapter extends RecyclerView.Adapter<SearchSliderOneViewHolder> {
    List<Banner> list;
    SearchSliderOneViewListener listener;

    public SearchSliderOneAdapter(SearchSliderOneViewListener listener) {
        list = new ArrayList<>();
        this.listener = listener;
    }

    public void setList(List<Banner> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchSliderOneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_image_slider, parent, false);
        return new SearchSliderOneViewHolder(inflate, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchSliderOneViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}