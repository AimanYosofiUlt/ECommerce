package com.ultimate.ecommerce.utilities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.app.DynamicTheme;

@SuppressLint("AppCompatCustomView")
public class ColoredImage extends ImageView {
    String colorName = "gs";
    int color = DynamicTheme.gradientStartColor;

    public ColoredImage(Context context) {
        super(context);
        init(null);
    }

    public ColoredImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ColoredImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public ColoredImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs == null)
            return;

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ColoredImage);
        colorName = typedArray.getString(R.styleable.ColoredImage_colorName);
        if (colorName != null)
            changeImageColor();

        setColorFilter(color);
        typedArray.recycle();
    }

    private void changeImageColor() {
        switch (colorName) {
            case "main":
                color = DynamicTheme.mainColor;
                break;

            case "second":
                color = DynamicTheme.secondColor;
                break;

            case "gs":
                color = DynamicTheme.gradientStartColor;
                break;

            case "ge":
                color = DynamicTheme.gradientEndColor;
                break;

            case "img":
                color = DynamicTheme.imageBackground;
                break;

            case "review":
                color = DynamicTheme.reviewColor;
                break;

            default:
                color = DynamicTheme.gradientStartColor;
        }

    }
}
