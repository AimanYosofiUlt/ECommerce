package com.ultimate.ecommerce.ui.fragment.order_confirm_shipment_method.views.shipment_method;

import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.databinding.ViewShipoingMethodBinding;
import com.ultimate.ecommerce.repository.server.response.shipping_methods.ShippingMethodsData;

public class ShippingMethodViewHolder extends RecyclerView.ViewHolder {
    ShippingMethodsData data;
    ShippingMethodViewListener listener;
    ViewShipoingMethodBinding binding;

    public ShippingMethodViewHolder(@NonNull View itemView, ShippingMethodViewListener listener) {
        super(itemView);
        binding = ViewShipoingMethodBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    public void bind(ShippingMethodsData data) {
        this.data = data;
        binding.nameTV.setText(data.getName());
        String cost = String.valueOf(data.getCost());
        binding.costTV.setText(cost);

        if (data.getCost() == 0)
            binding.costTV.setVisibility(View.GONE);
        else
            binding.costTV.setVisibility(View.VISIBLE);
    }

    private void initEvent() {
        binding.radioButton.setOnCheckedChangeListener((buttonView, isChecked) -> listener.onSelect(data));
    }
}