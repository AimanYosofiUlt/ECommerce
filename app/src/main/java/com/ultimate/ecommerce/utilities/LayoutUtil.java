package com.ultimate.ecommerce.utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.DialogMessageBinding;

public class LayoutUtil {
    public static void showShimmer(ConstraintLayout constraintLayout, ShimmerFrameLayout shimmerFrameLayout) {
        constraintLayout.setVisibility(View.INVISIBLE);
        constraintLayout.setAlpha(0);
        shimmerFrameLayout.startShimmer();
    }

    public static void hideShimmer(ConstraintLayout constraintLayout, ShimmerFrameLayout shimmerFrameLayout) {
        shimmerFrameLayout.animate().setDuration(200).alpha(0).withEndAction(() -> {
            shimmerFrameLayout.stopShimmer();
            shimmerFrameLayout.setVisibility(View.GONE);
            constraintLayout.setVisibility(View.VISIBLE);
            constraintLayout.animate().setDuration(1000).alpha(1);
        });
    }


    private static void showMassageDialogImpl(Context context, String title, String message, boolean isCancelAble, CustomDialogListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_message, null, false);
        DialogMessageBinding bd = DialogMessageBinding.bind(view);
        bd.title.titleTV.setText(title);
        bd.messageTV.setText(message);
        bd.cancelBtn.btnTextTV.setText(context.getString(R.string.cancel));


        if (isCancelAble) {
            bd.cancelBtn.CL.setVisibility(View.VISIBLE);
        } else {
            bd.cancelBtn.CL.setVisibility(View.GONE);
        }
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .create();

        dialog.getWindow()
                .setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();


        if (listener == null) {
            bd.doneBtn.btnBody.setOnClickListener(view1 -> dialog.dismiss());
            bd.cancelBtn.btnBody.setOnClickListener(view1 -> dialog.dismiss());
        } else {
            bd.doneBtn.btnBody.setOnClickListener(view1 -> {
                dialog.dismiss();
                listener.onClick();
            });
            bd.cancelBtn.btnBody.setOnClickListener(view12 -> {
                dialog.dismiss();
                listener.onCancel();
            });
        }
    }

    public static void showMassageDialog(Context context, String title, String message) {
        showMassageDialogImpl(context, title, message, false, null);
    }

    public static void showOptionDialog(Context context, String title, String message, CustomDialogListener listener) {
        showMassageDialogImpl(context, title, message, true, listener);
    }

}
