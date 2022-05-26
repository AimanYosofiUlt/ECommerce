package com.ultimate.ecommerce.ui.fragment.review_list.views.rate_star;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.ToolsRetingStarsBinding;

public class RateStars {
    ToolsRetingStarsBinding binding;
    RateStarsListener listener;
    int rate = 0;

    public int getRate() {
        return rate;
    }

    public RateStars(ToolsRetingStarsBinding binding, RateStarsListener listener) {
        this.binding = binding;
        this.listener = listener;
        initLoading();
        initEvent();
    }

    private void initLoading() {

    }

    private void initEvent() {
        binding.one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRate(1);
            }
        });

        binding.tow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRate(2);
            }
        });

        binding.three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRate(3);
            }
        });

        binding.four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRate(4);
            }
        });

        binding.five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRate(5);
            }
        });
    }

    private void setRate(int rate) {
        Drawable fill = ContextCompat.getDrawable(binding.getRoot().getContext(), R.drawable.ic_rateing_star);
        Drawable border = ContextCompat.getDrawable(binding.getRoot().getContext(), R.drawable.ic_star_outline);

        if (rate >= 1) {
            binding.one.setImageDrawable(fill);
        } else {
            binding.one.setImageDrawable(border);
        }

        if (rate >= 2) {
            binding.tow.setImageDrawable(fill);
        } else {
            binding.tow.setImageDrawable(border);
        }

        if (rate >= 3) {
            binding.three.setImageDrawable(fill);
        } else {
            binding.three.setImageDrawable(border);
        }

        if (rate >= 4) {
            binding.four.setImageDrawable(fill);
        } else {
            binding.four.setImageDrawable(border);
        }

        if (rate >= 5) {
            binding.five.setImageDrawable(fill);
        } else {
            binding.five.setImageDrawable(border);
        }
        listener.onRateChange(rate);
    }
}
