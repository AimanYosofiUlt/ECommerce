package com.ultimate.ecommerce.ui.fragment.order_confirm_address;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.FragmentOrderConfirmAddressBinding;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.get_address_fields.GetAddressFieldData;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.order_confirm.OrderConfirmListener;
import com.ultimate.ecommerce.ui.fragment.order_confirm_address.views.address.OrderAddress;
import com.ultimate.ecommerce.ui.fragment.order_confirm_address.views.address.OrderAddressAdapter;
import com.ultimate.ecommerce.ui.fragment.order_confirm_address.views.address.OrderAddressViewListener;

import java.util.ArrayList;
import java.util.List;

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

        viewModel.addressFieldsMDL.observe(getViewLifecycleOwner(), data -> {
            List<GetAddressFieldData> list = new ArrayList<>();
            for (GetAddressFieldData fieldData : data) {
                if (!fieldData.isHidden())
                    list.add(fieldData);
            }
            adapter.setList(list);
        });
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


