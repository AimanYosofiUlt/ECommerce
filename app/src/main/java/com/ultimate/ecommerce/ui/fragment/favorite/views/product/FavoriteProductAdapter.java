package com.ultimate.ecommerce.ui.fragment.favorite.views.product;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.repository.local.tables.favorite.Favorite;

import java.util.ArrayList;
import java.util.List;

    public class FavoriteProductAdapter extends RecyclerView.Adapter<FavoriteProductViewHolder> {
    List<Favorite> list;
    FavoriteProductViewListener listener;

    public FavoriteProductAdapter(FavoriteProductViewListener listener) {
        list = new ArrayList<>();
        this.listener = listener;
    }

    public void setList(List<Favorite> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_product ,parent,false);
        return new FavoriteProductViewHolder(inflate, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteProductViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}