package com.ultimate.ecommerce.ui.fragment.order_confirm_payment_method.views.payment_method;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.ecommerce.databinding.ViewPaymentMethodBinding;
import com.ultimate.ecommerce.repository.server.response.payment_methods.PaymentMethodsData;

public class PaymentMethodsViewHolder extends RecyclerView.ViewHolder {
    PaymentMethodsData data;
    PaymentMethodsViewListener listener;
    ViewPaymentMethodBinding binding;


    public PaymentMethodsViewHolder(@NonNull View itemView, PaymentMethodsViewListener listener) {
        super(itemView);
        binding = ViewPaymentMethodBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    public void bind(PaymentMethodsData data) {
        this.data = data;
        binding.nameTV.setText(data.getName());
        binding.descTV.setText(data.getDescription());
    }

    private void initEvent() {
        binding.radioButton.setOnCheckedChangeListener((buttonView, isChecked) -> listener.onSelect(data));
    }
}