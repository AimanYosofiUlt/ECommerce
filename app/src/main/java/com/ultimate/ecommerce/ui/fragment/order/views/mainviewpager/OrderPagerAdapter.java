package com.ultimate.ecommerce.ui.fragment.order.views.mainviewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ultimate.ecommerce.ui.fragment.order_inner.OrderInnerFragment;

import java.util.ArrayList;
import java.util.List;

public class OrderPagerAdapter extends FragmentStateAdapter {
    List<OrderInnerFragment> fragments = new ArrayList<>();

    public OrderPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public OrderPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public OrderPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public void addFragment(OrderInnerFragment fragment) {
        fragments.add(fragment);
        notifyItemInserted(fragments.size() - 1);
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