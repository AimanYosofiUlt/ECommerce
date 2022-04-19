package com.ultimate.ecommerce.ui.base;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.os.Build.VERSION.SDK_INT;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.app.LocaleHelper;
import com.ultimate.ecommerce.utilities.CommonMethods;
import com.ultimate.ecommerce.utilities.SharedPreferenceHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public abstract class BaseActivity extends AppCompatActivity {
    public static int langFlag = 1;
    public static final int PERMISSION_REQUEST_CODE = 1005;
    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        initActivityResultLauncher();
        checkAppPermission();
    }


    private void initActivityResultLauncher() {
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    proceed();
                } else {
                    Toast.makeText(this, this.getString(com.ultimate.ecommerce.R.string.allow_permission_for_storage_access), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void proceed() {
        initViewModel();
        initObservers();
        initErrorObservers();
        activityReadyToUse();
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    public void checkAppPermission() {


        if (ContextCompat.checkSelfPermission(this, READ_PHONE_STATE) != PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(this,
                    new String[]{READ_PHONE_STATE, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_CODE);


        } else if (SDK_INT >= Build.VERSION_CODES.R) {
            checkAndroid11permission();
        } else {
            proceed();
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.R)
    private void checkAndroid11permission() {

        if (!Environment.isExternalStorageManager()) {
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s", getApplicationContext().getPackageName())));
                launcher.launch(intent);
            } catch (Exception e) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                launcher.launch(intent);
            }
        } else {
            proceed();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {


            if (hasAllPermissionsGranted(grantResults)) {
                // all permissions granted

                if (SDK_INT >= Build.VERSION_CODES.R) {
                    checkAndroid11permission();
                } else {
                    proceed();
                }

            } else {
                // some permission are denied.
                finish();
            }

        }
    }

    public boolean hasAllPermissionsGranted(@NonNull int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    public abstract void initViewModel();

    public abstract void initObservers();

    public abstract void initErrorObservers();

    public abstract void activityReadyToUse();


    public void changeLanguage() {
        CharSequence[] LanguagesList = new CharSequence[1];

        switch (CommonMethods.getLanguageId(this)) {
            case "1": // ar
                LanguagesList[0] = this.getString(R.string.english);
                break;
            case "2": // en
                LanguagesList[0] = this.getString(R.string.arabic);

        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(getString(R.string.tv_change_lang_header));

        builder.setItems(LanguagesList, (dialog, item) -> {
            CharSequence ChoosenLanguage = LanguagesList[item];
            if (ChoosenLanguage != null) {
                String arabic = this.getString(R.string.arabic);
                String english = this.getString(R.string.english);

                if (ChoosenLanguage.toString().equalsIgnoreCase(arabic)) {
                    SharedPreferenceHelper.setSharedPreferenceString(this, SharedPreferenceHelper.Keys.LANGUAGE_KEY.getValue(), SharedPreferenceHelper.Keys.AR.getValue());

                }
                if (ChoosenLanguage.toString().equalsIgnoreCase(english)) {
                    SharedPreferenceHelper.setSharedPreferenceString(this, SharedPreferenceHelper.Keys.LANGUAGE_KEY.getValue(), SharedPreferenceHelper.Keys.EN.getValue());

                }

                finish();
                this.startActivity(getIntent());
                overridePendingTransition(R.anim.rotate_in, R.anim.rotate_out);

            }

        });
        builder.show();
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.setLocale(newBase));
    }

    @Override
    public void applyOverrideConfiguration(Configuration overrideConfiguration) {
        if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT <= 25) {
            //  getResources().getConfiguration().setTo(overrideConfiguration);
            createConfigurationContext(overrideConfiguration);
        }
        super.applyOverrideConfiguration(getResources().getConfiguration());
    }


    @SuppressLint("SimpleDateFormat")
    public String reFormatLoginDate(String date) {
     /*   DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzzz yyyy");
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            return dateTime.format(formatter2);
        } else {
          */
        try {
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat spf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy", Locale.ENGLISH);
            Date newDate = spf.parse(date);
            spf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
            date = spf.format(newDate);
            return date;

        } catch (Exception e) {

        }

        /*   }*/
        return Calendar.getInstance().getTime().toString();
    }
}
