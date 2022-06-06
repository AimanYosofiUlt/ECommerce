package com.ultimate.ecommerce.ui.fragment.register;

import static com.ultimate.ecommerce.utilities.ValidateSt.EMAIL_EMPTY_FILED_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.NAME_EMPTY_FILED_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.NOT_EMAIL_ERROR;
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
        bd.backBtn.setOnClickListener(view ->
                NavHostFragment.findNavController(requireParentFragment())
                        .popBackStack());

        bd.registerBtn.btnBody.setOnClickListener(view -> {
            String userName = bd.userNameED.getText().toString();
            String userPhone = bd.phoneCCP.getFullNumber();
            String userEmail = bd.emailED.getText().toString();
            String userPassword = bd.passwordED.getText().toString();
            showProgress(requireContext(),getString(R.string.register),getString(R.string.loading));
            viewModel.validateRegisterUser(requireContext(), userName, userPhone, userEmail, userPassword);
        });

        bd.googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.registerGoogleUser();
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
    }

    @Override
    public void initObservers() {
        viewModel.registerResponseMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
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
        viewModel.validateResponseStateMDL.observe(getViewLifecycleOwner(), responseState -> {
            if (!responseState.isSuccessful()) {
                handleValidateError(responseState.getMessage());
            }
        });
    }

    private void handleValidateError(String message) {
        switch (message) {
            case NO_INTERNET_CONNECTION:
                Toast.makeText(requireContext(), getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
                break;

            case NAME_EMPTY_FILED_ERROR:
                bd.userNameED.setError(getString(R.string.empty_name_error));
                break;

            case EMAIL_EMPTY_FILED_ERROR:
                bd.emailED.setError(getString(com.ultimate.ecommerce.R.string.empty_email_error));
                break;

            case NOT_EMAIL_ERROR:
                bd.emailED.setError(getString(com.ultimate.ecommerce.R.string.not_email_error));
                break;

            case PASSWORD_EMPTY_FILED_ERROR:
                bd.passwordED.setError(getString(com.ultimate.ecommerce.R.string.empty_password_error));
                break;

            case SMALL_PASSWORD_ERROR:
                bd.passwordED.setError(getString(com.ultimate.ecommerce.R.string.small_password_error));
                break;

            case PHONE_EMPTY_FILED_ERROR:
                bd.phoneED.setError(getString(R.string.empty_phone_error));
                break;

            default:
                Log.d("RegisterFragment", "HandleValidateError: You forget to handle this error :" + message);
        }
    }
}


