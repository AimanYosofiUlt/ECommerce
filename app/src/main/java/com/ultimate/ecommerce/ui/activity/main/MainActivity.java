package com.ultimate.ecommerce.ui.activity.main;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.app.DynamicTheme;
import com.ultimate.ecommerce.databinding.ActivityMainBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            bd = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());
        Window window = this.getWindow();
//        GradientDrawable background = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,
//                new int[]{
//                        DynamicTheme.gradientStartColor,
//                        DynamicTheme.gradientEndColor
//                });
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(DynamicTheme.gradientStartColor);
//        window.setNavigationBarColor(this.getResources().getColor(android.R.color.transparent));
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}