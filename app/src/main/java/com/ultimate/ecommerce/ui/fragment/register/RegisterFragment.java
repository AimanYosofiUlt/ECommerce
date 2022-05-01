package com.ultimate.ecommerce.ui.fragment.register;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.FragmentRegisterBinding;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.ui.base.BaseFragment;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RegisterFragment extends BaseFragment<RegisterFragmentViewModel> {
    FragmentRegisterBinding bd;
    private static final String TAG = "RegisterFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bd = FragmentRegisterBinding.inflate(getLayoutInflater());
        return bd.getRoot();
    }


    @Override
    public void initEvent() {
        bd.registerBtn.borderdBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = bd.userNameED.getText().toString();
                String userPhone = bd.phoneCCP.getFullNumberWithPlus();
                String userEmail = bd.emailED.getText().toString();
                String userPassword = bd.passwordED.getText().toString();
                viewModel.registerUser(userName, userPhone, userEmail, userPassword);
            }
        });

        bd.googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.registerGoogleUser();
            }
        });
    }

    @Override
    public void initObservers() {
        viewModel.responseMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                Log.d("RegisterFragment", "onChanged: 21312w: " + responseState.getMessage());
            }
        });
    }

    @Override
    public void initLoading() {
        bd.registerBtn.btnTextTV.setText(getString(R.string.register));
        bd.title.titleTV.setText(getString(R.string.register_with_us));
        bd.phoneCCP.registerPhoneNumberTextView(bd.phoneED);
    }

    @Override
    public void initErrorObserver() {

    }
}


