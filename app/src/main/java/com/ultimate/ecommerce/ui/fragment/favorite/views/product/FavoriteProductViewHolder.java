package com.ultimate.ecommerce.ui.fragment.favorite.views.product;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.app.DynamicTheme;
import com.ultimate.ecommerce.databinding.ViewProductBinding;
import com.ultimate.ecommerce.repository.local.tables.favorite.Favorite;

public class FavoriteProductViewHolder extends RecyclerView.ViewHolder {
    Favorite data;
    FavoriteProductViewListener listener;
    ViewProductBinding binding;


    public FavoriteProductViewHolder(@NonNull View itemView, FavoriteProductViewListener listener) {
        super(itemView);
        binding = ViewProductBinding.bind(itemView);
        this.listener = listener;
        init();
        initEvent();
    }

    private void init() {
        setInFavorite();
    }

    public void bind(Favorite data) {
        this.data = data;
        binding.productNameTV.setText(data.getTitle());

        Glide.with(itemView.getContext())
                .load(data.getImageUrl())
                .error(R.drawable.ic_baseline_error_24)
                .into(binding.productImage);

        binding.priceTV.setText(data.getPrice());
        binding.oldPriceTV.setText(data.getPrice());
        binding.discountPercentageTV.setText(data.getDiscountPercentage());
        binding.rateTV.setText(String.valueOf(data.getRatingCount()));

//        String cartQuantityStr = String.valueOf(data.getCartQuantity());
//        binding.countTV.setText(cartQuantityStr);
    }

    private void initEvent() {
        binding.favBtn.setOnClickListener(view -> {

        });

        binding.addToCartBtn.setOnClickListener(view -> {
            binding.countTV.setVisibility(View.VISIBLE);
//            String countStr = String.valueOf(data.increaseQuantity());
//            binding.countTV.setText(countStr);

//            ProductCart productCart = new ProductCart(data.getId(), data.getTitle()
//                    , data.getRatingCount(), data.getDiscountPercentage()
//                    , data.getPrice(), data.getCartQuantity(), data.getShortDescription());
//            listener.onAddToCart(productCart);
        });

        binding.cardBody.setOnClickListener(view -> listener.onClick(data));
    }

    public void setInFavorite() {
        Bitmap oldBitmap = getBitmapFromVectorDrawable(itemView.getContext(), R.drawable.ic_favourite_fill);
        Bitmap newBitmap = setGradientBackground(oldBitmap);
        binding.favBtn.setImageDrawable(new BitmapDrawable(itemView.getResources(), newBitmap));
    }

    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public Bitmap setGradientBackground(Bitmap originalBitmap) {
        int width = originalBitmap.getWidth();
        int height = originalBitmap.getHeight();
        Bitmap updatedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(updatedBitmap);
        canvas.drawBitmap(originalBitmap, 0, 0, null);

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, 0, 0, height, DynamicTheme.gradientStartColor, DynamicTheme.gradientEndColor, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawRect(0, 0, width, height, paint);
        return updatedBitmap;
    }
}