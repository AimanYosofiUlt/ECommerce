package com.ultimate.ecommerce.ui.fragment.order_confirm_check;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;
import com.ultimate.ecommerce.repository.repos.cart.CartRepo;
import com.ultimate.ecommerce.ui.base.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

public class OrderConfirmCheckFragmentViewModel extends BaseViewModel {
    LiveData<List<ProductCart>> productCartLiveData;

    @Inject
    public OrderConfirmCheckFragmentViewModel(@NonNull Application application, CartRepo cartRepo) {
        super(application);
        productCartLiveData = cartRepo.getCartProducts();
    }
}