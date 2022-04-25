package com.ultimate.ecommerce.ui.fragment.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.FragmentMainBinding;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.category.CategoryFragment;
import com.ultimate.ecommerce.ui.fragment.home.HomeFragment;
import com.ultimate.ecommerce.ui.fragment.main.views.mainviewpager.MainViewPagerAdapter;
import com.ultimate.ecommerce.ui.fragment.setting.SettingFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainFragment extends BaseFragment {
    FragmentMainBinding bd;
    MainViewPagerAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentMainBinding.inflate(getLayoutInflater());

        initBottomNavBar();

        return bd.getRoot();
    }

    private void initBottomNavBar() {
        float radius = getResources().getDimension(R.dimen.default_corner_radius);

        MaterialShapeDrawable bottomBarBackground = (MaterialShapeDrawable) bd.bottomAppBar.getBackground();
        bottomBarBackground.setShapeAppearanceModel(
                bottomBarBackground.getShapeAppearanceModel()
                        .toBuilder()
                        .setTopRightCorner(CornerFamily.ROUNDED,radius)
                        .setTopLeftCorner(CornerFamily.ROUNDED,radius)
                        .build());
    }

    @Override
    public void initObservers() {

    }

    @Override
    public void initLoading() {
        adapter = new MainViewPagerAdapter(requireParentFragment());
        adapter.addFragment(new CategoryFragment());
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new SettingFragment());
        bd.mainVP.setAdapter(adapter);
    }

    @Override
    public void initErrorObserver() {

    }

    @Override
    public void initEvent() {

    }
}