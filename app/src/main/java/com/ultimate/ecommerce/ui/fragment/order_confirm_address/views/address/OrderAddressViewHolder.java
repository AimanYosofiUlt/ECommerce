package com.ultimate.ecommerce.ui.fragment.order_confirm_address.views.address;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.app.DynamicTheme;
import com.ultimate.ecommerce.databinding.ViewAddressBinding;
import com.ultimate.ecommerce.repository.server.response.get_address_fields.GetAddressFieldData;

public class OrderAddressViewHolder extends RecyclerView.ViewHolder {
    OrderAddress data;
    OrderAddressViewListener listener;
    ViewAddressBinding binding;


    public OrderAddressViewHolder(@NonNull View itemView, OrderAddressViewListener listener) {
        super(itemView);
        binding = ViewAddressBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    public void bind(OrderAddress data) {
        this.data = data;

        GetAddressFieldData addressField = data.getAddressField();
        binding.addressTV.setText(addressField.getTitle());
        binding.addressComplateTV.setText(addressField.getName());
    }

    private void initEvent() {
        binding.cardBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.cardBody.setStrokeColor(DynamicTheme.gradientStartColor);
                listener.onSelect(data);
            }
        });
    }
}