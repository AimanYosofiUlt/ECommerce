package com.ultimate.ecommerce.ui.fragment.train;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.ultimate.ecommerce.app.DynamicTheme;
import com.ultimate.ecommerce.databinding.FragmentTrainBinding;
import com.ultimate.ecommerce.ui.base.BaseActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TrainActivity extends BaseActivity<TrainActivityViewModel> {
    FragmentTrainBinding bd;
    public String mainColor;
    public String secondColor;
    public String gradientStartColor;
    public String gradientEndColor;
    public String imageBackground;
    public String reviewColor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        bd = FragmentTrainBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initEvent() {
        bd.saveBtn.setOnClickListener(view -> {
            viewModel.saveConfigColor(mainColor, secondColor, gradientStartColor, gradientEndColor,imageBackground,reviewColor);
            DynamicTheme.mainColor = Color.parseColor(mainColor);
            DynamicTheme.secondColor = Color.parseColor(secondColor);
            DynamicTheme.gradientStartColor = Color.parseColor(gradientStartColor);
            DynamicTheme.gradientEndColor = Color.parseColor(gradientEndColor);
            DynamicTheme.imageBackground = Color.parseColor(imageBackground);
            DynamicTheme.reviewColor = Color.parseColor(reviewColor);
            recreate();
        });

        bd.button2.setOnClickListener(view -> new ColorDialog(TrainActivity.this, hexColor -> {
            mainColor = hexColor;
            bd.button4.setBackgroundColor(Color.parseColor(hexColor));
        }));
        bd.button12.setOnClickListener(view -> new ColorDialog(TrainActivity.this, hexColor -> {
            secondColor = hexColor;
            bd.button11.setBackgroundColor(Color.parseColor(hexColor));
        }));
        bd.button23.setOnClickListener(view -> new ColorDialog(TrainActivity.this, hexColor -> {
            gradientStartColor = hexColor;
            bd.button18.setBackgroundColor(Color.parseColor(hexColor));
        }));
        bd.button25.setOnClickListener(view -> new ColorDialog(TrainActivity.this, hexColor -> {
            gradientEndColor = hexColor;
            bd.button20.setBackgroundColor(Color.parseColor(hexColor));
        }));
        bd.button24.setOnClickListener(view -> new ColorDialog(TrainActivity.this, hexColor -> {
            imageBackground = hexColor;
            bd.button19.setBackgroundColor(Color.parseColor(hexColor));
        }));
        bd.button26.setOnClickListener(view -> new ColorDialog(TrainActivity.this, hexColor -> {
            reviewColor = hexColor;
            bd.button21.setBackgroundColor(Color.parseColor(hexColor));
        }));
    }

    private void showColorPicker(DialogInterface.OnClickListener onClickListener) {

    }

    @Override
    public void initViewModel() {

    }

    @Override
    public void initObservers() {
        viewModel.configurationLiveData.observe(this, configuration -> {
            bd.setConfig(configuration);
            mainColor = configuration.getMainColor();
            secondColor = configuration.getSecondColor();
            gradientStartColor = configuration.getGradientStartColor();
            gradientEndColor = configuration.getGradientEndColor();
            imageBackground = configuration.getImageBackground();
            reviewColor = configuration.getReviewColor();

            bd.button4.setBackgroundColor(Color.parseColor(mainColor));
            bd.button11.setBackgroundColor(Color.parseColor(secondColor));
            bd.button18.setBackgroundColor(Color.parseColor(gradientStartColor));
            bd.button20.setBackgroundColor(Color.parseColor(gradientEndColor));
            bd.button19.setBackgroundColor(Color.parseColor(imageBackground));
            bd.button21.setBackgroundColor(Color.parseColor(reviewColor));
        });
    }

    @Override
    public void initErrorObservers() {

    }

    @Override
    public void initLoading() {
        bd.LT.banner.gradiantView.isHasTopTriangle(true);
        bd.LT.banner2.gradiantView.isHasTopTriangle(true);
    }
}
