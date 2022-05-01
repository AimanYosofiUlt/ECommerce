package com.ultimate.ecommerce.utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.ultimate.ecommerce.R;

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
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .create();
        dialog.show();

        ((TextView) view.findViewById(R.id.title).findViewById(R.id.titleTV)).setText(title);
        ((TextView) view.findViewById(R.id.messageTV)).setText(title);

        view.findViewById(R.id.doneBtn).findViewById(R.id.btnBody)
                .setOnClickListener(view1 -> dialog.dismiss());
    }
}
