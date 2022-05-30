package com.ultimate.ecommerce.ui.fragment.rate_order.views.mainviewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ultimate.ecommerce.ui.fragment.rate_order_inner.RateOrder;
import com.ultimate.ecommerce.ui.fragment.rate_order_inner.RateOrderInnerFragment;

import java.util.ArrayList;
import java.util.List;

public class RateOrderPagerAdapter extends FragmentStateAdapter {
    List<RateOrderInnerFragment> fragments = new ArrayList<>();

    public RateOrderPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public RateOrderPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public RateOrderPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public void addFragment(RateOrderInnerFragment fragment) {
        fragments.add(fragment);
        notifyItemInserted(fragments.size() - 1);
    }

   public List<RateOrder> getRateOrders() {
        List<RateOrder> rateOrders = new ArrayList<>();
        for (RateOrderInnerFragment fragment : fragments) {
            rateOrders.add(fragment.getData());
        }
        return rateOrders;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}