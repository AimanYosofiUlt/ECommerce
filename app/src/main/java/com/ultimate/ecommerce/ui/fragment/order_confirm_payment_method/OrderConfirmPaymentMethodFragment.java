package com.ultimate.ecommerce.ui.fragment.order_confirm_payment_method;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.FragmentOrderConfirmPaymentMethodBinding;
import com.ultimate.ecommerce.repository.server.response.payment_methods.PaymentMethodsData;
import com.ultimate.ecommerce.repository.server.response.shipping_methods.ShippingMethodsData;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.order_confirm.OrderConfirmListener;
import com.ultimate.ecommerce.ui.fragment.order_confirm_address.views.address.OrderAddress;
import com.ultimate.ecommerce.ui.fragment.order_confirm_payment_method.views.payment_method.PaymentMethodsAdapter;
import com.ultimate.ecommerce.ui.fragment.order_confirm_payment_method.views.payment_method.PaymentMethodsViewListener;
import com.ultimate.ecommerce.ui.fragment.order_confirm_shipment_method.views.shipment_method.ShippingMethodAdapter;
import com.ultimate.ecommerce.ui.fragment.order_confirm_shipment_method.views.shipment_method.ShippingMethodViewListener;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OrderConfirmPaymentMethodFragment extends BaseFragment<OrderConfirmPaymentMethodFragmentViewModel> {
    FragmentOrderConfirmPaymentMethodBinding binding;
    PaymentMethodsAdapter adapter;
    PaymentMethodsData paymentMethod;
    OrderConfirmListener listener;
    boolean isNextAble = false;

    public PaymentMethodsData getPaymentMethod() {
        return paymentMethod;
    }

    public OrderConfirmPaymentMethodFragment(OrderConfirmListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderConfirmPaymentMethodBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }


    @Override
    public void initEvent() {
        binding.nextBtn.btnBody.setOnClickListener(v -> {
            if (isNextAble) {
                listener.onNextReq();
            }
        });
    }

    @Override
    public void initObservers() {
        viewModel.paymentMethodsMDL.observe(getViewLifecycleOwner(), data -> adapter.setList(data));
    }

    @Override
    public void initLoading() {
        binding.nextBtn.btnBody.setGradient(Color.LTGRAY, Color.LTGRAY);
        binding.nextBtn.btnTextTV.setTextColor(Color.GRAY);
        binding.nextBtn.btnTextTV.setText(getString(R.string.pay));

        adapter = new PaymentMethodsAdapter(data -> {
            paymentMethod = data;
            isNextAble = true;
            binding.nextBtn.btnBody.setGradientDef();
            binding.nextBtn.btnTextTV.setTextColor(Color.BLACK);
        });

        LinearLayoutManager layout = new LinearLayoutManager(requireContext());
        binding.paymentRV.setLayoutManager(layout);
        binding.paymentRV.setAdapter(adapter);
        viewModel.validatePaymentMethods(requireContext());
    }

    @Override
    public void initErrorObserver() {

    }
}


