package com.ultimate.ecommerce.ui.fragment.login.views.auth_edittext;

import static com.ultimate.ecommerce.utilities.ValidateSt.EMAIL_EMPTY_FILED_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.EMPTY_FILED_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.NOT_EMAIL_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.NO_VALIDATION;
import static com.ultimate.ecommerce.utilities.ValidateSt.PASSWORD_EMPTY_FILED_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.PHONE_EMPTY_FILED_ERROR;
import static com.ultimate.ecommerce.utilities.ValidateSt.SMALL_PASSWORD_ERROR;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.rilixtech.widget.countrycodepicker.CountryCodePicker;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.repository.server.response.get_auth.Field;
import com.ultimate.ecommerce.utilities.AuthSt;
import com.ultimate.ecommerce.utilities.ValidateSt;

public class AuthEdittext {
    Field field;
    AuthEdittextListener listener;
    View view;
    EditText fieldEd;
    TextView optionalMsg;
    CountryCodePicker codePicker;

    public AuthEdittext(Context context, Field field, AuthEdittextListener listener) {
        this.field = field;
        this.listener = listener;
        init(context);
        initLoading();
        initEvent();
    }

    private void init(Context context) {
        int layoutId = getLayoutId(field.getName());
        view = LayoutInflater.from(context).inflate(layoutId, null, false);
        fieldEd = view.findViewById(R.id.fieldED);
        optionalMsg = view.findViewById(R.id.optionalMsg);
        initByType(context);
    }

    private void initByType(Context context) {
        if (field.getName().equals(AuthSt.PHONE)) {
            codePicker = view.findViewById(R.id.phoneCCP);
            codePicker.registerPhoneNumberTextView(fieldEd);
            return;
        }

        if (field.getName().equals(AuthSt.EMAIL)) {
            setStartDrawable(context, R.drawable.ic_email);
            return;
        }

        setStartDrawable(context, R.drawable.ic_username);
    }

    private void setStartDrawable(Context context, int ic_phone) {
        Drawable img = ContextCompat.getDrawable(context, ic_phone);
        Configuration configuration = context.getResources().getConfiguration();

        boolean isRtlDirection = configuration.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
        if (isRtlDirection)
            fieldEd.setCompoundDrawables(img, null, null, null);
        else
            fieldEd.setCompoundDrawables(null, null, img, null);
    }

    private void initLoading() {
        fieldEd.setHint(field.getPlaceholder());
        if (field.isRequired())
            optionalMsg.setVisibility(View.GONE);
        else
            optionalMsg.setVisibility(View.VISIBLE);
    }

    private int getLayoutId(String name) {
        if (name.equals(AuthSt.PHONE))
            return R.layout.tools_edittext_phone;

        if (name.equals(AuthSt.PASSWORD))
            return R.layout.tools_edittext_password;

        return R.layout.tools_edittext;
    }

    public View getView() {
        return view;
    }

    public Field getField() {
        return field;
    }

    private void initEvent() {

    }

    public String getText() {
        if (field.getName().equals(AuthSt.PHONE))
            return codePicker.getFullNumber();

        return fieldEd.getText().toString();
    }

    public String getValidation() {
        if (field.getName().equals(AuthSt.EMAIL))
            return getEmailValidation();

        if (field.getName().equals(AuthSt.PHONE))
            return getPhoneValidation();

        if (field.getName().equals(AuthSt.PASSWORD))
            return getPasswordValidation();

        boolean isEmpty = fieldEd.getText().toString().isEmpty() && field.isRequired();
        if (isEmpty)
            return EMPTY_FILED_ERROR;

        return NO_VALIDATION;
    }

    private String getPasswordValidation() {
        String password = fieldEd.getText().toString();
        boolean isEmptyPassword = password.trim().isEmpty() && field.isRequired();
        if (isEmptyPassword)
            return PASSWORD_EMPTY_FILED_ERROR;

        else if (password.trim().length() < 8)
            return SMALL_PASSWORD_ERROR;

        return ValidateSt.NO_VALIDATION;
    }

    private String getEmailValidation() {
        String emailPattern = "[a-zA-Z][a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String email = fieldEd.getText().toString();

        boolean isEmailEmpty = email.isEmpty() && field.isRequired();
        if (isEmailEmpty)
            return EMAIL_EMPTY_FILED_ERROR;

        boolean isNotEmail = !email.trim().matches(emailPattern);
        if (isNotEmail)
            return NOT_EMAIL_ERROR;

        return ValidateSt.NO_VALIDATION;
    }

    private String getPhoneValidation() {
        String phone = fieldEd.getText().toString();
        boolean isPhoneEmpty = phone.trim().isEmpty() && field.isRequired();
        if (isPhoneEmpty)
            return PHONE_EMPTY_FILED_ERROR;

        return ValidateSt.NO_VALIDATION;
    }

    public void setError(String message) {
        fieldEd.setError(message);
    }
}