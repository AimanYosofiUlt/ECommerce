package com.ultimate.ecommerce.ui.fragment.order_confirm_shipment_method;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.FragmentOrderConfirmShipmentMethodBinding;
import com.ultimate.ecommerce.repository.server.response.shipping_methods.ShippingMethodsData;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.order_confirm.OrderConfirmListener;
import com.ultimate.ecommerce.ui.fragment.order_confirm_shipment_method.views.shipment_method.ShippingMethodAdapter;
import com.ultimate.ecommerce.ui.fragment.order_confirm_shipment_method.views.shipment_method.ShippingMethodViewListener;

import java.util.List;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OrderConfirmShipmentMethodFragment extends BaseFragment<OrderConfirmShipmentMethodFragmentViewModel> {
    FragmentOrderConfirmShipmentMethodBinding binding;
    ShippingMethodAdapter adapter;
    ShippingMethodsData shippingMethods;
    boolean isNextAble = false;
    OrderConfirmListener listener;

    public ShippingMethodsData getShippingMethods() {
        return shippingMethods;
    }

    public OrderConfirmShipmentMethodFragment(OrderConfirmListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderConfirmShipmentMethodBinding.inflate(getLayoutInflater());
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
        viewModel.shipmentMethodsMDL.observe(getViewLifecycleOwner(), data -> adapter.setList(data));
    }

    @Override
    public void initLoading() {
        binding.nextBtn.btnBody.setGradient(Color.LTGRAY, Color.LTGRAY);
        binding.nextBtn.btnTextTV.setTextColor(Color.GRAY);
        binding.nextBtn.btnTextTV.setText(getString(R.string.next));

        adapter = new ShippingMethodAdapter(data -> {
            shippingMethods = data;
            isNextAble = true;
            binding.nextBtn.btnBody.setGradientDef();
            binding.nextBtn.btnTextTV.setTextColor(Color.BLACK);
        });

        LinearLayoutManager layout = new LinearLayoutManager(requireContext());
        binding.shipmentRV.setLayoutManager(layout);
        binding.shipmentRV.setAdapter(adapter);
        viewModel.validateShipmentMethods(requireContext());
    }

    @Override
    public void initErrorObserver() {

    }
}


