package com.ultimate.ecommerce.ui.fragment.cart;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.FragmentCartBinding;
import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.update_cart.Products;
import com.ultimate.ecommerce.repository.server.response.update_cart.UpdateCartData;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.cart.views.cart_product.CartProductAdapter;
import com.ultimate.ecommerce.ui.fragment.cart.views.cart_product.CartProductViewListener;
import com.ultimate.ecommerce.utilities.LayoutUtil;

import java.util.List;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CartFragment extends BaseFragment<CartFragmentViewModel> {
    private static final String TAG = "CartFragment";
    FragmentCartBinding binding;
    CartProductAdapter adapter;
    CartFragmentListener listener;
    UpdateCartData cartData;

    public CartFragment(CartFragmentListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void initEvent() {
        binding.payBtn.btnBody.setOnClickListener(v -> listener.onOrderConfirmReq(cartData));

        binding.updateBtn.btnBody.setOnClickListener(v -> {
            showProgress(requireContext(), getString(R.string.update_cart), getString(R.string.loading));
            String coupon = binding.coupon.couponED.getText().toString();
            viewModel.validateUpdateCart(requireContext(), coupon, adapter.getList());
        });

        binding.clearBtn.setOnClickListener(v -> LayoutUtil.showOptionDialog(requireContext()
                , getString(R.string.clear_cart)
                , getString(R.string.clear_cart_msg)
                , () -> viewModel.clearCart()));

        binding.coupon.couponED.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                showUpdateOpt();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void initObservers() {
        viewModel.productCartLiveData.observe(getViewLifecycleOwner(),
                data -> {
                    boolean thereIsNoItem = data.size() == 0;
                    setNoItemStateVisibility(thereIsNoItem);
                    adapter.setList(data);
                });

        viewModel.cartTotalLiveData.observe(getViewLifecycleOwner(),
                total -> binding.coupon.totalAfterDiscountTV.setText(String.valueOf(total)));

        viewModel.updateCartResponseMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                hideProgress();
                //todo handel server errors and validation error
                Log.d(TAG, "onChanged: 9234" + responseState.getMessage());
            }
        });

        viewModel.cartDataMDL.observe(getViewLifecycleOwner(), new Observer<UpdateCartData>() {
            @Override
            public void onChanged(UpdateCartData data) {

                cartData = data;
                boolean isPayAvailable = checkProductAvailability(data.getProducts());
//                if (isPayAvailable) {
                hideUpdateOpt(data.getDiscount());
                calculateCouponTotalPrice(data);
//                }
                //todo handel when the product unaviable
            }

            private void calculateCouponTotalPrice(UpdateCartData data) {
                //todo here you should handel other paramter
                String discount = String.valueOf(data.getDiscount());
                String beforeTotal = String.valueOf(data.getBeforeTotal());
                String subTotal = String.valueOf(data.getSubTotal());
                String total = String.valueOf(data.getTotal());
                binding.coupon.discountTV.setText(discount);
                binding.coupon.totalBeforeDiscountTV.setText(beforeTotal);
                binding.coupon.totalAfterDiscountTV.setText(total);

            }

            private boolean checkProductAvailability(List<Products> products) {
                for (Products product : products) {
                    if (!product.isAvailability()) {
                        return false;
                    }
                }
                return true;
            }
        });
    }


    @Override
    public void initLoading() {
        binding.title.titleTV.setText(getString(R.string.cart));
        binding.payBtn.btnTextTV.setText(getString(R.string.pay));
        binding.updateBtn.btnTextTV.setText(getString(R.string.update));
        binding.noItemStateMessage.messageTV.setText(getString(R.string.empty_cart));
        setNoItemStateVisibility(true);


        adapter = new CartProductAdapter(new CartProductViewListener() {
            @Override
            public void onQuantityChange(ProductCart data, int qty) {
                showUpdateOpt();
                viewModel.updateProductQty(data.getProductId(), qty);
            }

            @Override
            public void onCancel(ProductCart data) {
                viewModel.removeCartProduct(data.getProductId());
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.cartProductRV.setLayoutManager(layoutManager);
        binding.cartProductRV.setAdapter(adapter);
    }

    private void showUpdateOpt() {
        binding.payBtn.CL.setVisibility(View.INVISIBLE);
        binding.updateBtn.CL.setVisibility(View.VISIBLE);
        binding.clearBtn.setVisibility(View.VISIBLE);
        binding.coupon.discountTV.setVisibility(View.GONE);
        binding.coupon.discountTitle.setVisibility(View.GONE);
        binding.coupon.totalBeforeDiscountTV.setVisibility(View.GONE);
    }

    private void hideUpdateOpt(double discount) {
        binding.payBtn.CL.setVisibility(View.VISIBLE);
        binding.updateBtn.CL.setVisibility(View.GONE);
        binding.clearBtn.setVisibility(View.GONE);
        binding.coupon.discountTV.setVisibility(View.VISIBLE);
        binding.coupon.discountTitle.setVisibility(View.VISIBLE);
        if (discount != 0) {
            binding.coupon.totalBeforeDiscountTV.setVisibility(View.VISIBLE);
        }
    }

    void setNoItemStateVisibility(boolean isVisible) {
        if (isVisible) {
            binding.mainCL.setVisibility(View.GONE);
            binding.noItemStateMessage.CL.setVisibility(View.VISIBLE);
        } else {
            binding.mainCL.setVisibility(View.VISIBLE);
            binding.noItemStateMessage.CL.setVisibility(View.GONE);
        }
    }

    @Override
    public void initErrorObserver() {

    }
}


