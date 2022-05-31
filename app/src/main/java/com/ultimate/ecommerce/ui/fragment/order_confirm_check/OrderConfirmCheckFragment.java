package com.ultimate.ecommerce.ui.fragment.order_confirm_check;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.FragmentOrderConfirmCheckBinding;
import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;
import com.ultimate.ecommerce.repository.server.response.update_cart.UpdateCartData;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.order_confirm.OrderConfirmListener;
import com.ultimate.ecommerce.ui.fragment.order_confirm_check.views.cart_product_info.CartProductInfoAdapter;
import com.ultimate.ecommerce.ui.fragment.order_confirm_check.views.cart_product_info.CartProductInfoViewListener;

import java.util.List;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OrderConfirmCheckFragment extends BaseFragment<OrderConfirmCheckFragmentViewModel> {
    FragmentOrderConfirmCheckBinding binding;
    UpdateCartData data;
    OrderConfirmListener listener;
    CartProductInfoAdapter adapter;

    public OrderConfirmCheckFragment(UpdateCartData data, OrderConfirmListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderConfirmCheckBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }


    @Override
    public void initEvent() {
        binding.nextBtn.btnBody.setOnClickListener(v -> {
                listener.onNextReq();
        });
    }

    @Override
    public void initObservers() {
        viewModel.productCartLiveData.observe(getViewLifecycleOwner(), productCarts -> adapter.setList(productCarts));
    }

    @Override
    public void initLoading() {
        binding.nextBtn.btnTextTV.setText(getString(R.string.next));

        binding.vatTV.setText(String.valueOf(data.getVat()));
        binding.shipCostTV.setText(String.valueOf(data.getShipping()));
        binding.totalBeforeDiscountTV.setText(String.valueOf(data.getBeforeTotal()));
        binding.totalAfterDiscountTV.setText(String.valueOf(data.getTotal()));
        adapter = new CartProductInfoAdapter(new CartProductInfoViewListener() {
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.productsRV.setLayoutManager(layoutManager);
        binding.productsRV.setAdapter(adapter);
    }

    @Override
    public void initErrorObserver() {

    }
}


