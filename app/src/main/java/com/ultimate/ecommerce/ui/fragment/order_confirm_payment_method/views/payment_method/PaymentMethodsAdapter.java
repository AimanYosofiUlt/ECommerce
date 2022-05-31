package com.ultimate.ecommerce.ui.fragment.order_confirm_payment_method.views.payment_method;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.repository.server.response.payment_methods.PaymentMethodsData;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethodsAdapter extends RecyclerView.Adapter<PaymentMethodsViewHolder> {
    List<PaymentMethodsData> list;
    PaymentMethodsViewListener listener;

    public PaymentMethodsAdapter(PaymentMethodsViewListener listener) {
        list = new ArrayList<>();
        this.listener = listener;
    }

    public void setList(List<PaymentMethodsData> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PaymentMethodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_payment_method, parent, false);
        return new PaymentMethodsViewHolder(inflate, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentMethodsViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}