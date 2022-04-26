package com.ultimate.ecommerce.ui.fragment.subSections;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ultimate.ecommerce.databinding.FragmentSubsectionsBinding;
import com.ultimate.ecommerce.ui.base.BaseFragment;


public class SubSectionsFragment extends BaseFragment<SubsectionsViewModel> {

    private FragmentSubsectionsBinding bd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        bd = FragmentSubsectionsBinding.inflate(getLayoutInflater());
        return bd.getRoot();
    }

    @Override
    public void initEvent() {

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
}