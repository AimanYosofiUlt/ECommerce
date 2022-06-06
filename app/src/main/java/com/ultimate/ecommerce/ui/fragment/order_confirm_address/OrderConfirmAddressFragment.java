package com.ultimate.ecommerce.ui.fragment.order_confirm_address;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.FragmentOrderConfirmAddressBinding;
import com.ultimate.ecommerce.repository.server.response.get_address_fields.GetAddressFieldData;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.order_confirm.OrderConfirmListener;
import com.ultimate.ecommerce.ui.fragment.order_confirm_address.views.address.OrderAddress;
import com.ultimate.ecommerce.ui.fragment.order_confirm_address.views.address.OrderAddressAdapter;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OrderConfirmAddressFragment extends BaseFragment<OrderConfirmAddressFragmentViewModel> {
    private static final String TAG = "OrderConfirmAddressFrag";
    FragmentOrderConfirmAddressBinding binding;
    OrderConfirmListener listener;
    OrderAddressAdapter adapter;
    OrderAddress address;
    boolean isNextAble = false;

    public OrderAddress getAddress() {
        return address;
    }

    public OrderConfirmAddressFragment(OrderConfirmListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderConfirmAddressBinding.inflate(getLayoutInflater());
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
        viewModel.getAddressFieldsResponseMDL.observe(getViewLifecycleOwner(), responseState -> {
            hideProgress();
            //handel server errors
            Log.d(TAG, "onChanged: 2388l:" + responseState.getMessage());
        });

        viewModel.addressFieldsMDL.observe(getViewLifecycleOwner(), addressFieldData -> {
            for (GetAddressFieldData address : addressFieldData) {
                if (address.getName().equals("shipping")) {
                    initShipAddress(address);
                }
            }
        });
    }

    private void initShipAddress(GetAddressFieldData data) {

    }

    @Override
    public void initLoading() {
        binding.nextBtn.btnBody.setGradient(Color.LTGRAY, Color.LTGRAY);
        binding.nextBtn.btnTextTV.setTextColor(Color.GRAY);
        binding.nextBtn.btnTextTV.setText(getString(R.string.next));

        adapter = new OrderAddressAdapter(data -> {
            address = data;
            binding.nextBtn.btnBody.setGradientDef();
            binding.nextBtn.btnTextTV.setTextColor(Color.BLACK);
            isNextAble = true;
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.addressRV.setLayoutManager(layoutManager);
        binding.addressRV.setAdapter(adapter);

        viewModel.validateGetAddressFields(requireContext());
    }

    @Override
    public void initErrorObserver() {

    }
}


