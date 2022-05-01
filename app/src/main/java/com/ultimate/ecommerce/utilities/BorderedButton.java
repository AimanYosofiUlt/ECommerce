package com.ultimate.ecommerce.utilities;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.app.DynamicTheme;

public class BorderedButton extends View {
    int mainColor = DynamicTheme.gradientStartColor;
    float radius = 100f;
    float strockWidth = 8f;

    public BorderedButton(Context context) {
        super(context);
        init(null);
    }

    public BorderedButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public BorderedButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public BorderedButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs == null)
            return;

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.BorderedButton);
        radius = typedArray.getDimension(R.styleable.BorderedButton_radius, 100f);
        strockWidth = typedArray.getDimension(R.styleable.BorderedButton_strokeWidth, 8f);
        typedArray.recycle();

        setClickable(true);
        setFocusable(true);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawRoundBack(canvas);
    }

    private void drawRoundBack(Canvas canvas) {
        Paint paint = new Paint();
        Path bottomPath = getRoundRectPathBottom();
        paint.setColor(mainColor);
        paint.setAntiAlias(true);
        canvas.drawPath(bottomPath, paint);

        Path topPath = getRoundRectPathTop();
        paint.setColor(Color.WHITE);
        canvas.drawPath(topPath, paint);
    }

    private Path getRoundRectPathBottom() {
        Path path = new Path();
        float[] corners = getTheCorners();
        path.addRoundRect(new RectF(getLeft(), getTop(), getRight(), getBottom()), corners, Path.Direction.CW);
        return path;
    }

    private Path getRoundRectPathTop() {
        Path path = new Path();
        float[] corners = getTheCorners();
        path.addRoundRect(new RectF(getLeft() + strockWidth, getTop() + strockWidth, getRight() - strockWidth, getBottom() - strockWidth), corners, Path.Direction.CW);
        return path;
    }


    private float[] getTheCorners() {
        return new float[]{
                radius, radius,   // Top left radius in px
                radius, radius,   // Top right radius in px
                radius, radius,     // Bottom right radius in px
                radius, radius      // Bottom left radius in px
        };
    }


    @Override
    public boolean performClick() {
        if (isClickable())
            startAnime();
        return super.performClick();
    }

    void startAnime() {
        Animation animation = AnimationUtils.loadAnimation(this.getContext(), R.anim.bounce);
        this.startAnimation(animation);
    }

    public void setGradientDef() {
        setGradient(DynamicTheme.mainColor);
    }

    private void setGradient(int mainColor) {
        this.mainColor = mainColor;
        invalidate();
    }

}
