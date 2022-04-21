package com.ultimate.ecommerce.ui.fragment.train;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.jaredrummler.android.colorpicker.ColorPickerView;

public class ColorDialog implements DialogInterface.OnClickListener {
    ColorDialog(Context context, ColorDialogListener listener) {
        ColorPickerView pickerView = new ColorPickerView(context);
        pickerView.getColor();
        new AlertDialog.Builder(context)
                .setView(pickerView)
                .setNegativeButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String hexColor = String.format("#%06X", (0xFFFFFF & pickerView.getColor()));
                        listener.onGetColor(hexColor);
                        dialogInterface.dismiss();
                    }
                })
                .create()
                .show();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }

    interface ColorDialogListener {
      public   void onGetColor(String hexColor);
    }
}
