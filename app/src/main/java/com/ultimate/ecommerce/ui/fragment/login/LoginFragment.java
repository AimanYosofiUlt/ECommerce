package com.ultimate.ecommerce.ui.fragment.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.FragmentLoginBinding;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.ui.base.BaseFragment;
import com.ultimate.ecommerce.utilities.LayoutUtil;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends BaseFragment<LoginFragmentViewModel> {


    FragmentLoginBinding bd;

    public LoginFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bd = FragmentLoginBinding.inflate(getLayoutInflater());
        return bd.getRoot();
    }


    @Override
    public void initEvent() {
        bd.loginBtn.btnBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userPhone = bd.phoneCCP.getFullNumberWithPlus();
                String userPassword = bd.passwordED.getText().toString();
                viewModel.login(userPhone, userPassword);
            }
        });

        bd.registerBtn.btnBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(requireParentFragment())
                        .navigate(R.id.actionLoginToRegister);
            }
        });

        bd.googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void initObservers() {
        viewModel.responseStateMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                if (responseState.isSuccessful()) {
                    NavHostFragment.findNavController(requireParentFragment()).popBackStack();
                    Toast.makeText(requireContext(), getText(R.string.login_success), Toast.LENGTH_SHORT).show();
                } else {
                    LayoutUtil.showMassageDialog(requireContext(), getString(R.string.login_filed), responseState.getMessage());
                }
            }
        });
    }

    @Override
    public void initLoading() {
        bd.title.titleTV.setText(getString(R.string.login_title));
        bd.loginBtn.btnTextTV.setText(getString(R.string.login));
        bd.loginBtn.btnTextTV.setText(getString(R.string.login));
        bd.registerBtn.btnTextTV.setText(getString(R.string.register_new));
        bd.phoneCCP.registerPhoneNumberTextView(bd.phoneED);
    }

    @Override
    public void initErrorObserver() {

    }
}


