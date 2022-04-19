package com.ultimate.ecommerce.ui.fragment.sections;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.FragmentHomeBinding;
import com.ultimate.ecommerce.databinding.FragmentSectionsBinding;
import com.ultimate.ecommerce.ui.base.BaseFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SectionsFragment extends BaseFragment {
    FragmentSectionsBinding bd;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentSectionsBinding.inflate(getLayoutInflater());
        return bd.getRoot();
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