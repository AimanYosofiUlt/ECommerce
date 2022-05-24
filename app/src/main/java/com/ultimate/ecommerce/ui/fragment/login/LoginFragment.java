package com.ultimate.ecommerce.ui.fragment.login;

import static com.ultimate.ecommerce.utilities.ValidateSt.NO_INTERNET_CONNECTION;
import static com.ultimate.ecommerce.utilities.ValidateSt.PASSWORD_EMPTY_FILED_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.PHONE_EMPTY_FILED_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.SMALL_PASSWORD_ERROR;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;

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
        bd.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(requireParentFragment())
                        .popBackStack();
            }
        });


        bd.loginBtn.btnBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userPhone = bd.phoneCCP.getFullNumberWithPlus();
                String userPassword = bd.passwordED.getText().toString();
                showProgress(requireContext(), getString(R.string.login), getString(R.string.loading));
                viewModel.validateLoginEd(requireContext(), userPhone, userPassword);
            }
        });

        bd.registerBtn.btnBody.setOnClickListener(view -> NavHostFragment.findNavController(requireParentFragment())
                .navigate(R.id.actionLoginToRegister));

        bd.googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        bd.facebookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        bd.twitterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        bd.appleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        bd.registerBtn.btnBody.setOnClickListener(view -> NavHostFragment.findNavController(requireParentFragment())
                .navigate(
                        LoginFragmentDirections.actionLoginToRegister()
                ));

        bd.asVistorLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(requireParentFragment())
                        .popBackStack();
            }
        });

        bd.forgetPasswordLink.setOnClickListener(new View.OnClickListener() {
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
                hideProgress();
                if (responseState.isSuccessful()) {
                    NavHostFragment.findNavController(requireParentFragment()).popBackStack();
                    Toast.makeText(requireContext(), getText(R.string.login_success), Toast.LENGTH_SHORT).show();
                } else {
                    LayoutUtil.showMassageDialog(requireContext()
                            , getString(R.string.login_filed)
                            , responseState.getMessage());
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
        viewModel.validateResponseStateMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                hideProgress();
                handleValidateError(responseState.getMessage());
            }
        });
    }

    private void handleValidateError(String message) {
        switch (message) {
            case NO_INTERNET_CONNECTION:
                Toast.makeText(requireContext(), getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
                break;

            case PHONE_EMPTY_FILED_ERROR:
                bd.phoneED.setError(getString(R.string.empty_phone_error));
                break;

            case PASSWORD_EMPTY_FILED_ERROR:
                bd.passwordED.setError(getString(R.string.empty_password_error));
                break;

            case SMALL_PASSWORD_ERROR:
                bd.passwordED.setError(getString(R.string.small_password_error));
                break;

            default:
                Log.d("RegisterFragment", "HandleValidateError: You forget to handle this error :" + message);
        }
    }
}


