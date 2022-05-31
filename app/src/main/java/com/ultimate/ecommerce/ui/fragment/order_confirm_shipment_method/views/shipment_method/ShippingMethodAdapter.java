package com.ultimate.ecommerce.ui.fragment.order_confirm_shipment_method.views.shipment_method;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.repository.server.response.shipping_methods.ShippingMethodsData;

import java.util.ArrayList;
import java.util.List;

public class ShippingMethodAdapter extends RecyclerView.Adapter<ShippingMethodViewHolder> {
    List<ShippingMethodsData> list;
    ShippingMethodViewListener listener;

    public ShippingMethodAdapter(ShippingMethodViewListener listener) {
        list = new ArrayList<>();
        this.listener = listener;
    }

    public void setList(List<ShippingMethodsData> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShippingMethodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_shipoing_method, parent, false);
        return new ShippingMethodViewHolder(inflate, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ShippingMethodViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}