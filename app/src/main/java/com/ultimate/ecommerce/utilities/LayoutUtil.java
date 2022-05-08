package com.ultimate.ecommerce.utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

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
        shimmerFrameLayout.animate().setDuration(200).alpha(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                constraintLayout.setVisibility(View.VISIBLE);
                constraintLayout.animate().setDuration(1000).alpha(1);
            }
        });
    }

    public static void showMassageDialog(Context context, String title, String message) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_message, null, false);
        DialogMessageBinding bd  = DialogMessageBinding.bind(view);
        bd.title.titleTV.setText(title);
        bd.messageTV.setText(message);

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .create();
        dialog.show();

        bd.doneBtn.btnBody.setOnClickListener(view1 -> dialog.dismiss());
    }
}
