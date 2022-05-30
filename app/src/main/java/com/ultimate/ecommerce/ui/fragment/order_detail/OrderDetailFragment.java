package com.ultimate.ecommerce.ui.fragment.order_detail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.FragmentOrderDetailBinding;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.get_order.Billing;
import com.ultimate.ecommerce.repository.server.response.get_order.Cost;
import com.ultimate.ecommerce.repository.server.response.get_order.GetOrderData;
import com.ultimate.ecommerce.repository.server.response.get_order.Product;
import com.ultimate.ecommerce.repository.server.response.get_order.Shippingmethod;
import com.ultimate.ecommerce.repository.server.response.get_user_orders.Order;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.utilities.CustomDialogListener;
import com.ultimate.ecommerce.utilities.LayoutUtil;

import java.util.List;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OrderDetailFragment extends BaseFragment<OrderDetailFragmentViewModel> {
    FragmentOrderDetailBinding binding;
    GetOrderData data;
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
        binding.backBtn.setOnClickListener(view -> NavHostFragment.findNavController(requireParentFragment())
                .popBackStack());

        binding.rateBtn.btnBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (data == null)
                    return;

                NavHostFragment.findNavController(requireParentFragment())
                        .navigate(OrderDetailFragmentDirections.actionOrderDetailToRateOrder(data));
            }
        });

        binding.cancelBtn.btnBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutUtil.showMassageDialog(requireContext()
                        , getString(R.string.order_cancel_title)
                        , getString(R.string.order_cancel_title)
                        , new CustomDialogListener() {
                            @Override
                            public void onClick() {
                                showProgress(requireContext(), getString(R.string.order_cancel_title), getString(R.string.loading));
                                viewModel.validateRefundOrder(requireContext(), order);
                            }
                        });
            }
        });
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
        this.data = data;
        initProducts(data.getProducts());
        initCostPanel(data);
        initOrderState(data.getOrderstatus());
        initAddress(data.getBilling());
        initPaymentMethod(data.getPaymentMethod());
        initShippingMethod(data.getShippingMethod());
    }

    private void initShippingMethod(Shippingmethod shippingMethod) {
        // todo handel all shipping methodes type
    }

    private void initPaymentMethod(String paymentMethod) {
        // todo handel all payment methodes type
        binding.paymentMsg.setText(getString(R.string.payment_msg));
    }

    private void initAddress(Billing billing) {
        binding.addressTV.setText(billing.getAddress());
        binding.subAddressTV.setText(billing.getStreet());
    }

    private void initOrderState(String orderStatus) {
        switch (orderStatus) {
            case "pending":
                binding.cancelBtn.btnBody.setVisibility(View.VISIBLE);
                break;

            case "processing":
                break;

            case "on-hold":
                break;

            case "completed":
                binding.doneCard.cardBody.setVisibility(View.VISIBLE);
                binding.rateBtn.btnBody.setVisibility(View.VISIBLE);
                break;

            case "cancelled":
                break;

            case "refunded":
                break;

            default:
                Log.d("OrderDetailFragment", "initOrderState: 3874l: there is order state not handled (" + orderStatus + ")");
        }
    }

    private void initCostPanel(GetOrderData data) {
        Cost cost = data.getCost();
        String currency = " " + data.getCurrency();

        String vat = cost.getVat() + currency;
        String shipCost = data.getShippingMethod().getTotal() + currency;
        String discount = cost.getDiscountTotal() + currency;
        String totalBeforeDiscount = cost.getTotal() + currency;
        String totalAfterDiscount = cost.getTotalafterdiscount() + currency;

        binding.vatTV.setText(vat);
        binding.shipCostTV.setText(shipCost);
        binding.discountTV.setText(discount);
        binding.totalBeforeDiscountTV.setText(totalBeforeDiscount);
        binding.totalAfterDiscountTV.setText(totalAfterDiscount);
    }

    private void initProducts(List<Product> products) {
        setProductByIndex(0, binding.firstImage, products);
        setProductByIndex(1, binding.secoundImage, products);
        setProductByIndex(2, binding.thirdImage, products);
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
        binding.rateBtn.btnTextTV.setText(getString(R.string.rate_products));
        binding.costTitle.startTitle.setText(getString(R.string.cost_title));

        binding.doneCard.cardBody.setVisibility(View.GONE);
//        binding.rateBtn.btnBody.setVisibility(View.GONE);
        binding.cancelBtn.btnBody.setVisibility(View.GONE);

        viewModel.validateGetOrderDetail(requireContext(), order);
    }

    @Override
    public void initErrorObserver() {

    }
}


