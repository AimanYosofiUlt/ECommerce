package com.ultimate.ecommerce.ui.fragment.contact_us;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.ultimate.ecommerce.databinding.FragmentConnectUsBinding;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.contact_us.ContactUsData;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.ui.fragment.contact_us.views.contact_view.ContactViewAdapter;
import com.ultimate.ecommerce.utilities.LayoutUtil;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ContactUsFragment extends BaseFragment<ContactUsFragmentViewModel> {
    FragmentConnectUsBinding bd;

    private static final String TAG = "ContactUsFragment";

    ContactViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bd = FragmentConnectUsBinding.inflate(getLayoutInflater());
        return bd.getRoot();
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initObservers() {
        viewModel.responseMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                Log.d("ContactUsFragment", "onChanged: 232432: " + responseState.getMessage());
            }
        });
        viewModel.contactUsDataMDL.observe(getViewLifecycleOwner(), new Observer<ContactUsData>() {
            @Override
            public void onChanged(ContactUsData contactUsData) {
                bd.placeholder.pageTitleTV.setText(contactUsData.getPageTitle());
                bd.placeholder.pageDiscriptionTV.setText(contactUsData.getPageDescription());
                adapter.setList(contactUsData.getPageContent());

                LayoutUtil.hideShimmer(bd.placeholder.placeholderCL, bd.shimmer.shimmerL);
            }
        });
    }

    @Override
    public void initLoading() {
        adapter = new ContactViewAdapter();
        bd.placeholder.contentRV.setAdapter(adapter);
        LayoutUtil.showShimmer(bd.placeholder.placeholderCL, bd.shimmer.shimmerL);

        viewModel.getContactUs();
    }

    @Override
    public void initErrorObserver() {

    }
}
