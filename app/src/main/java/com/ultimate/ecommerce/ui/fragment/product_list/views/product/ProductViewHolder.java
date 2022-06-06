package com.ultimate.ecommerce.ui.fragment.product_list.views.product;

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
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.app.DynamicTheme;
import com.ultimate.ecommerce.databinding.ViewProductBinding;
import com.ultimate.ecommerce.repository.local.tables.cart.ProductCart;
import com.ultimate.ecommerce.repository.server.response.get_products.ProductData;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    ProductAdapterData data;
    ProductViewListener listener;
    ViewProductBinding binding;

    public ProductViewHolder(@NonNull View itemView, ProductViewListener listener) {
        super(itemView);
        binding = ViewProductBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    public void bind(boolean isInDetail, ProductAdapterData data) {
        this.data = data;
        if (isInDetail) {
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
            );
            binding.cardBody.setLayoutParams(lp);
        }
        ProductData productData = data.getData();
        binding.productNameTV.setText(productData.getTitle());

        Glide.with(itemView.getContext())
                .load(productData.getImageUrl())
                .error(R.drawable.ic_baseline_error_24)
                .into(binding.productImage);

        binding.priceTV.setText(productData.getPrice());
        binding.oldPriceTV.setText(productData.getPrice());
        binding.discountPercentageTV.setText(productData.getDiscountPercentage());
        binding.rateTV.setText(String.valueOf(productData.getRatingCount()));

        String cartQuantityStr = String.valueOf(data.getCartQuantity());
        binding.countTV.setText(cartQuantityStr);

        if (data.isInFavorite()) {
            binding.favBtn.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_favourite));
            listener.removeFromFavorite(data);
        } else {
            setInFavorite();
            listener.addToFavorite(data);
        }

        if (data.isInFavorite()) {
            setInFavorite();
        } else {
            binding.favBtn.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_favourite));
        }

        boolean hasDiscount = !productData.getSalePrice().equalsIgnoreCase("0") || !productData.getSalePrice().equalsIgnoreCase("0.0");
        if (hasDiscount) {
            binding.priceTV.setText(productData.getSalePrice());
            binding.oldPriceTV.setText(productData.getRegularPrice());
        } else {
            binding.priceTV.setText(String.valueOf(productData.getRegularPrice()));
            binding.oldPriceTV.setVisibility(View.GONE);
            binding.discountTile.setVisibility(View.GONE);
        }
    }

    private void initEvent() {
        binding.favBtn.setOnClickListener(view -> {
            if (data.isInFavorite()) {
                binding.favBtn.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_favourite));
                listener.removeFromFavorite(data);
            } else {
                setInFavorite();
                listener.addToFavorite(data);
            }
            data.setInFavorite(!data.isInFavorite());
        });

        binding.addToCartBtn.setOnClickListener(view -> {
            binding.countTV.setVisibility(View.VISIBLE);
            String countStr = String.valueOf(data.increaseQuantity());
            binding.countTV.setText(countStr);
            ProductData productData = this.data.getData();
            ProductCart productCart = new ProductCart(productData.getId(), productData.getTitle()
                    , productData.getImageUrl(), productData.getPrice()
                    , productData.getShortDescription(), productData.getDiscountPercentage()
                    , productData.getRatingCount(),data.getCartQuantity());
            listener.onAddToCart(productCart);
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