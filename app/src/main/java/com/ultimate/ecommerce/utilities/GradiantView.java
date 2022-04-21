package com.ultimate.ecommerce.utilities;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.app.DynamicTheme;

public class GradiantView extends View {
    boolean hasTopTriangle = false;
    boolean hasThreeColor = false;
    int gradientStart = Color.BLACK;
    int gradientEnd = Color.LTGRAY;
    int imageBackground = Color.WHITE;

    float topLeft = 0f;
    float topRight = 0f;
    float bottomLeft = 0f;
    float bottomRight = 0f;

    public GradiantView(Context context) {
        super(context);
        init(null);
    }

    public GradiantView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public GradiantView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public GradiantView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs == null)
            return;

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.GradiantView);
        topLeft = typedArray.getDimension(R.styleable.GradiantView_topLeft, 0f);
        topRight = typedArray.getDimension(R.styleable.GradiantView_topRight, 0f);
        bottomLeft = typedArray.getDimension(R.styleable.GradiantView_bottomLeft, 0f);
        bottomRight = typedArray.getDimension(R.styleable.GradiantView_bottomRight, 0f);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawRoundBack(canvas);
        if (hasTopTriangle)
            drawRoundTraingle(canvas);
    }

    public void isHasTopTriangle(boolean has) {
        this.hasTopTriangle = has;
        invalidate();
    }

    private void drawRoundBack(Canvas canvas) {
        Paint paint = new Paint();
        Path path = getRoundRectPath();
        Shader shader = getTheShader();
        paint.setShader(shader);
        canvas.drawPath(path, paint);
    }

    private Path getRoundRectPath() {
        Path path  = new Path();
        float[] corners = getTheCorners();
        path.addRoundRect(new RectF(getLeft(), getTop(), getRight(), getBottom()), corners, Path.Direction.CW);
        return path;
    }

    private float[] getTheCorners() {
        return new float[]{
                topLeft, topLeft,   // Top left radius in px
                topRight, topRight,   // Top right radius in px
                bottomRight, bottomRight,     // Bottom right radius in px
                bottomLeft, bottomLeft      // Bottom left radius in px
        };
    }

    private Shader getTheShader() {
        Shader shader = new LinearGradient(getX(), getY(), getWidth(), getHeight(),
                gradientStart, gradientEnd, Shader.TileMode.CLAMP);

        int[] colors = {
                gradientStart,
                imageBackground,
                gradientEnd
        };

        if (hasThreeColor)
            shader = new LinearGradient(0f, 0f, (float) getWidth(), 0f, colors, null, Shader.TileMode.MIRROR);

        return shader;
    }

    private void drawRoundTraingle(Canvas canvas) {
        Paint paint = new Paint();
        Path path = getTrainglePath();
        Shader shader = new LinearGradient(getX(), getY(), getWidth(), getHeight(),
                Color.TRANSPARENT, Color.parseColor("#77FFFFFF"), Shader.TileMode.CLAMP);
        CornerPathEffect cornerPathEffect = new CornerPathEffect(bottomLeft);

        paint.setPathEffect(cornerPathEffect);
        paint.setShader(shader);
        canvas.drawPath(path, paint);
    }

    private Path getTrainglePath() {
        Path roundTriangle = new Path();
        roundTriangle.moveTo(getX(), getY());
        roundTriangle.lineTo(getWidth(), getY());
        roundTriangle.lineTo(getWidth(), getHeight());
        roundTriangle.lineTo(getWidth() - 2 * bottomRight, getHeight());
        roundTriangle.lineTo(getX() + topLeft, getY());
        roundTriangle.close();
        return roundTriangle;
    }

        public void setGradientDef() {
            setGradient(DynamicTheme.gradientStartColor, DynamicTheme.gradientEndColor);
        }

    public void setGradient(int gradientStart, int gradientEnd) {
        this.gradientStart = gradientStart;
        this.gradientEnd = gradientEnd;
        invalidate();
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


}
