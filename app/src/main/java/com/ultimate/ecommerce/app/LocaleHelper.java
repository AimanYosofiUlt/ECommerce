package com.ultimate.ecommerce.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.ultimate.ecommerce.utilities.SharedPreferenceHelper;

import java.util.Locale;

/**
 * Created by Youssif Hamdy on 6/25/2019.
 */
public class LocaleHelper {
    public static Context setLocale(Context context) {
        String Language = SharedPreferenceHelper.getSharedPreferenceString(context, SharedPreferenceHelper.Keys.LANGUAGE_KEY.getValue(), SharedPreferenceHelper.Keys.EN.getValue());
        return updateResources(context, Language);
    }

    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.setLocale(locale);
        context = context.createConfigurationContext(config);
        return context;
    }
}
