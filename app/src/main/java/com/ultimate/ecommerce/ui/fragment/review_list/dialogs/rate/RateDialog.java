package com.ultimate.ecommerce.ui.fragment.review_list.dialogs.rate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.DialogReteBinding;
import com.ultimate.ecommerce.ui.base.BaseDialog;
import com.ultimate.ecommerce.ui.fragment.review_list.views.rate_star.RateStars;

public class RateDialog extends BaseDialog {
    DialogReteBinding binding;
    RateDialogListener listener;
    int rate = 0;

    public RateDialog(Context context, RateDialogListener listener) {
        super(context);
        this.listener  = listener;
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_rete, null, false);
        binding = DialogReteBinding.bind(inflate);
        setView(binding.getRoot());
    }
    @Override
    protected void initLoading() {
        binding.title.titleTV.setText(getContext().getString(R.string.rate_product));
        new RateStars(binding.rateStars, rate -> RateDialog.this.rate = rate);
    }

    @Override
    protected void initEvent() {
        binding.addBtn.btnBody.setOnClickListener(view -> {
            String review = binding.reviewED.getText().toString();
            listener.onAddReviewReq(review, rate);
        });

        binding.removeBtn.setOnClickListener(view -> dialog.cancel());
    }
}
