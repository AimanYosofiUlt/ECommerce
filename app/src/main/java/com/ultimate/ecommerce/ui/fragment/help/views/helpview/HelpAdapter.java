package com.ultimate.ecommerce.ui.fragment.help.views.helpview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.repository.server.response.help.QuesData;

import java.util.ArrayList;
import java.util.List;

public class
HelpAdapter extends RecyclerView.Adapter<HelpViewHolder> {
    List<QuesData> list;
    HelpViewListener listener;

    public HelpAdapter(HelpViewListener listener) {
        list = new ArrayList<>();
        this.listener = listener;
    }

    public void setList(List<QuesData> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HelpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_help, parent, false);
        return new HelpViewHolder(inflate, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull HelpViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
