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
    ViewProductBinding bd;
    boolean isInFavorite = false;
    int productQuantity = 0;

    public ProductViewHolder(@NonNull View itemView, ProductViewListener listener) {
        super(itemView);
        bd = ViewProductBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    public void bind(ProductAdapterData data) {
        this.data = data;
        bd.productNameTV.setText(data.getData().getTitle());

        Glide.with(itemView.getContext())
                .load(data.getData().getImageUrl())
                .error(R.drawable.ic_baseline_error_24)
                .into(bd.productImage);

        bd.priceTV.setText(data.getData().getPrice());
        bd.oldPriceTV.setText(data.getData().getPrice());
        bd.discountPercentageTV.setText(data.getData().getDiscountPercentage());
        bd.rateTV.setText(String.valueOf(data.getData().getRatingCount()));

//        //todo here should calculate the discount and show the oldPrice or the price but the response don't give a percentage
//        String discountMsg = itemView.getContext().getString(R.string.discountBy) + " " + data.getData().getDiscountPercentage() + "%";
//        bd.discountPercentageTV.setText(discountMsg);
//        int discountPrice = Integer.parseInt(data.getData().getPrice()) * Integer.parseInt(data.getData().getDiscountPercentage()) / 100;
//        bd.priceTV.setText(String.valueOf(discountPrice));
//        bd.oldPriceTV.setText(data.getData().getPrice());
//
    }

    private void initEvent() {
        bd.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isInFavorite) {
                    setInFavorite();
                    isInFavorite = true;
                } else {
                    isInFavorite = false;
                    bd.favBtn.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_favourite));
                }
            }
        });

        bd.addToCartBtn.setOnClickListener(view -> {
            bd.countTV.setVisibility(View.VISIBLE);
            String countStr = String.valueOf(++productQuantity);
            bd.countTV.setText(countStr);
            ProductData productData = this.data.getData();
            ProductCart productCart = new ProductCart(productData.getId(), productData.getTitle()
                    , productData.getRatingCount(), productData.getDiscountPercentage()
                    , productData.getPrice(), productQuantity);
            listener.onAddToCart(productCart);
        });
    }

    public void setInFavorite() {
        Bitmap oldBitmap = getBitmapFromVectorDrawable(itemView.getContext(), R.drawable.ic_favourite_fill);
        Bitmap newBitmap = setGradientBackground(oldBitmap);
        bd.favBtn.setImageDrawable(new BitmapDrawable(itemView.getResources(), newBitmap));
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