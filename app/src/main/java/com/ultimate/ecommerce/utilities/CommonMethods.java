package com.ultimate.ecommerce.utilities;

import android.content.Context;

import com.ultimate.ecommerce.R;

import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Created by Youssif Hamdy on 1/26/2021.
 */
public class CommonMethods {
    public static String DecodePass(String Pass) {
        int Cur_Chr;
        StringBuilder p_pass = new StringBuilder();
        for (int i = 0; i < Pass.length(); i++) {
            Cur_Chr = String.valueOf(Pass.charAt(i)).codePointAt(0);
            Cur_Chr = Cur_Chr - Pass.length();
            p_pass.append((char) Cur_Chr);
        }
        return p_pass.toString();
    }


    public static String round(Double value, String noOfDecimalAr) {
        int places;
        if (noOfDecimalAr != null) {
            if (noOfDecimalAr.isEmpty() || noOfDecimalAr.equalsIgnoreCase("0")) {
                return String.valueOf(value);
            }
            places = Integer.parseInt(noOfDecimalAr);
        } else {
            places = 3;
        }
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return formatNumber(places, (double) tmp / factor);

    }


    public static String round(String mValue, String noOfDecimalAr) {

        if (mValue == null || mValue.equals(""))
            mValue = "0";

        double value = Double.parseDouble(mValue);
        int places;


        if (noOfDecimalAr != null) {
            if (noOfDecimalAr.isEmpty() || noOfDecimalAr.equalsIgnoreCase("0")) {
                return String.valueOf(value);
            }
            places = Integer.parseInt(noOfDecimalAr);
        } else {
            places = 3;
        }
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);

        value = value * factor;
        long tmp = Math.round(value);
        return formatNumber(places, (double) tmp / factor);

    }

    public static String formatNumber(int decimals, double number) {
        StringBuilder sb = new StringBuilder(decimals + 2);
        if (number == 0 || number < 1) {
            sb.append("#0.");
        } else {
            sb.append("#.");
        }
        for (int i = 0; i < decimals; i++) {
            sb.append("0");
        }
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.ENGLISH);
        return new DecimalFormat(sb.toString(), symbols).format(number);
    }


    public static String getLanguageId(Context context) {
        String Language = SharedPreferenceHelper.getSharedPreferenceString(context, SharedPreferenceHelper.Keys.LANGUAGE_KEY.getValue(), Locale.getDefault().getLanguage());
        if (Language.equalsIgnoreCase("ar")) {
            return "1";
        }
        if (Language.equalsIgnoreCase("en")) {
            return "2";
        }

        return "1";
    }

    public static String replaceArabicNumbers(String original) {
        return original
                .replace("٠", "0")
                .replace("١", "1")
                .replace("٢", "2")
                .replace("٣", "3")
                .replace("٤", "4")
                .replace("٥", "5")
                .replace("٦", "6")
                .replace("٧", "7")
                .replace("٨", "8")
                .replace("٩", "9");

    }

    public static String getAppPath(Context context) {

        File dir = new File(context.getExternalFilesDir(null)
                + File.separator
                + context.getResources().getString(com.ultimate.ecommerce.R.string.app_name)
                + File.separator
        );

        if (!dir.exists()) {
            dir.mkdir();
        }

        return dir.getPath() + File.separator;

    }

    public static String getErrorMessage(Context context, String errorMsg, int errorNo) {
        switch (errorNo) {

            case 1: // no data
                return context.getString(R.string.no_data_found);

            case 10: // database error / code error
                return context.getString(R.string.database_error);

            case 802: // P_TRMNL is required.
                return context.getString(R.string.p_trmnl_required);

            case 803: // this machine is not verified.
                return context.getString(R.string.machine_not_verified);

            case 804: // there's no users.
                return context.getString(R.string.no_users_found);

            case 805: // there's no branches for the users.
                return context.getString(R.string.no_branches_for_the_users);

            case 806: // P_MACHINE_NO is required.
                return context.getString(R.string.machine_no_required);

            case 807: // pos is not exist
                return context.getString(R.string.pos_does_not_exist);

            case -120: // pos is not exist
                return context.getString(R.string.couldnt_reach_server);

            default:
                return errorMsg;
        }


    }

}
