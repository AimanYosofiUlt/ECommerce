package com.ultimate.ecommerce.ui.fragment.cart;
import android.app.Application;
import androidx.annotation.NonNull;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import javax.inject.Inject;

public class CartFragmentViewModel extends BaseViewModel {
    @Inject
    public CartFragmentViewModel(@NonNull Application application) {
        super(application);
    }
}