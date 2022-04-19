package com.ultimate.ecommerce.utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Abubaker on 04/10/2016.
 */

public class SharedPreferenceHelper {
    public static void setSharedPreferenceString(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(Keys.PREF_FILE.getValue(), 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }


    public static void setSharedPreferenceInt(Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(Keys.PREF_FILE.getValue(), 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
    }


    public static void setSharedPreferenceBoolean(Context context, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(Keys.PREF_FILE.getValue(), 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }


    public static String getSharedPreferenceString(Context context, String key, String defValue) {
        SharedPreferences settings = context.getSharedPreferences(Keys.PREF_FILE.getValue(), 0);
        return settings.getString(key, defValue);
    }


    public static int getSharedPreferenceInt(Context context, String key, int defValue) {
        SharedPreferences settings = context.getSharedPreferences(Keys.PREF_FILE.getValue(), 0);
        return settings.getInt(key, defValue);
    }


    public static boolean getSharedPreferenceBoolean(Context context, String key, boolean defValue) {
        SharedPreferences settings = context.getSharedPreferences(Keys.PREF_FILE.getValue(), 0);
        return settings.getBoolean(key, defValue);
    }


    public enum Keys {

        USER_ID("User_ID"),
        FINANCIAL_UNIT("FinancialUnit"),
        BRANCH_POSITION("BranchPosition"),
        LOGIN_DATE("LoginDate"),
        LANGUAGE_KEY("LANGUAGE_KEY"),
        PRINTER_ADDRESS_KEY("PRinTER_address_KEY"),
        SEARCH_GRID_SPANCOUNT("GridSpanCount"),
        AR("ar"),
        EN("en"),
        PREF_FILE("PREF_FILE");

        String value;

        Keys(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }



    }


}


