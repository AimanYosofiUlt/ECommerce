package com.ultimate.ecommerce.ui.fragment.order_detail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.FragmentOrderDetailBinding;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.get_order.Cost;
import com.ultimate.ecommerce.repository.server.response.get_order.GetOrderData;
import com.ultimate.ecommerce.repository.server.response.get_order.Product;
import com.ultimate.ecommerce.repository.server.response.get_user_orders.Order;
import com.ultimate.ecommerce.ui.base.BaseFragment;

import java.util.List;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OrderDetailFragment extends BaseFragment<OrderDetailFragmentViewModel> {
    FragmentOrderDetailBinding binding;
    Order order;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderDetailBinding.inflate(getLayoutInflater());
        order = OrderDetailFragmentArgs.fromBundle(getArguments()).getOrder();
        return binding.getRoot();
    }


    @Override
    public void initEvent() {

    }

    @Override
    public void initObservers() {
        viewModel.getDetailResponseMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                //todo handel the server erros
                Log.d("OrderDetailFragment", "onChanged: 923879i:" + responseState.getMessage());
            }
        });

        viewModel.detailMDL.observe(getViewLifecycleOwner(), this::initLayout);
    }

    private void initLayout(GetOrderData data) {
        initProducts(data.getProducts());

        initCostPanel(data);
    }

    private void initCostPanel(GetOrderData data) {
        Cost cost = data.getCost();
        String currency = " " + data.getCurrency();

        String vat = cost.getVat() + currency;
        String shipCost = data.getShippingmethod().getTotal() + currency;
        String discount = cost.getDiscounttotal() + currency;
        String totalBeforeDiscount = cost.getTotal() + currency;
        String totalAfterDiscount = cost.getTotalafterdiscount() + currency;

        binding.vatTV.setText(vat);
        binding.shipCostTV.setText(shipCost);
        binding.discountTV.setText(discount);
        binding.totalBeforeDiscountTV.setText(totalBeforeDiscount);
        binding.totalAfterDiscountTV.setText(totalAfterDiscount);
    }

    private void initProducts(List<Product> products) {
        setProductByIndex(0, binding.firstImage.image, products);
        setProductByIndex(1, binding.secoundImage.image, products);
        setProductByIndex(2, binding.thirdImage.image, products);
        if (products.size() > 3) {
            binding.productsCountGroup.setVisibility(View.VISIBLE);
            String anotherCount = String.valueOf(products.size() - 3);
            String moreMessage = "+" + anotherCount + binding.getRoot().getContext().getString(R.string.other_product);
            binding.moreImageTV.setText(moreMessage);
        } else {
            binding.productsCountGroup.setVisibility(View.GONE);
        }
    }

    private void setProductByIndex(int index, ImageView image, List<Product> products) {
        if (products.size() > index) {
            image.setVisibility(View.VISIBLE);
            Glide.with(binding.getRoot().getContext())
                    .load(products.get(0).getImage())
                    .into(image);
        } else {
            image.setVisibility(View.GONE);
        }
    }

    @Override
    public void initLoading() {
        binding.productsTitle.startTitle.setText(getString(R.string.products_title));
        binding.addressTitle.startTitle.setText(getString(R.string.address_title));
        binding.shipTitle.startTitle.setText(getString(R.string.ship_title));
        binding.deliverTitle.startTitle.setText(getString(R.string.deliver_title));
        binding.costTitle.startTitle.setText(getString(R.string.cost_title));

        viewModel.validateGetOrderDetail(requireContext(), order);
    }

    @Override
    public void initErrorObserver() {

    }
}


