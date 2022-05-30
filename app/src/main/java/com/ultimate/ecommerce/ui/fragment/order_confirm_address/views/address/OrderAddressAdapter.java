package com.ultimate.ecommerce.ui.fragment.order_confirm_address.views.address;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.repository.server.response.get_address_fields.GetAddressFieldData;

import java.util.ArrayList;
import java.util.List;

public class OrderAddressAdapter extends RecyclerView.Adapter<OrderAddressViewHolder> {
    List<OrderAddress> list;
    OrderAddressViewListener listener;

    public OrderAddressAdapter(OrderAddressViewListener listener) {
        list = new ArrayList<>();
        this.listener = listener;
    }

    public void setList(List<GetAddressFieldData> list) {
        for (GetAddressFieldData data : list) {
            if (!data.isHidden())
                this.list.add(new OrderAddress(data));
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderAddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_address, parent, false);
        return new OrderAddressViewHolder(inflate, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAddressViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}