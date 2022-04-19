package com.ultimate.ecommerce.ui.fragment.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.FragmentHomeBinding;
import com.ultimate.ecommerce.databinding.FragmentMainBinding;
import com.ultimate.ecommerce.ui.base.BaseFragment;

public class MainFragment extends BaseFragment {
    FragmentMainBinding bd;

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

    }

    @Override
    public void initErrorObserver() {

    }

    @Override
    public void initEvent() {

    }
}