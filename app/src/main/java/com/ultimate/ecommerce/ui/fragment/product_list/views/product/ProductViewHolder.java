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
    boolean isInFavorite = false;

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
        binding.productNameTV.setText(data.getData().getTitle());

        Glide.with(itemView.getContext())
                .load(data.getData().getImageUrl())
                .error(R.drawable.ic_baseline_error_24)
                .into(binding.productImage);

        binding.priceTV.setText(data.getData().getPrice());
        binding.oldPriceTV.setText(data.getData().getPrice());
        binding.discountPercentageTV.setText(data.getData().getDiscountPercentage());
        binding.rateTV.setText(String.valueOf(data.getData().getRatingCount()));

        String cartQuantityStr = String.valueOf(data.getCartQuantity());
        binding.countTV.setText(cartQuantityStr);

//      todo here should calculate the discount and show the oldPrice or the price but the response don't give a percentage
//        String discountMsg = itemView.getContext().getString(R.string.discountBy) + " " + data.getData().getDiscountPercentage() + "%";
//        bd.discountPercentageTV.setText(discountMsg);
//        int discountPrice = Integer.parseInt(data.getData().getPrice()) * Integer.parseInt(data.getData().getDiscountPercentage()) / 100;
//        bd.priceTV.setText(String.valueOf(discountPrice));
//        bd.oldPriceTV.setText(data.getData().getPrice());
    }

    private void initEvent() {
        binding.favBtn.setOnClickListener(view -> {
            if (!isInFavorite) {
                setInFavorite();
                isInFavorite = true;
                listener.addToFavorite(data);
            } else {
                binding.favBtn.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_favourite));
                isInFavorite = false;
                listener.removeFromFavorite(data);
            }
        });

        binding.addToCartBtn.setOnClickListener(view -> {
            binding.countTV.setVisibility(View.VISIBLE);
            String countStr = String.valueOf(data.increaseQuantity());
            binding.countTV.setText(countStr);
            ProductData productData = this.data.getData();
            ProductCart productCart = new ProductCart(productData.getId(), productData.getTitle()
                    , productData.getRatingCount(), productData.getDiscountPercentage()
                    , productData.getPrice(), data.getCartQuantity(), productData.getShortDescription());
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