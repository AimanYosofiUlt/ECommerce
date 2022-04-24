package com.ultimate.ecommerce.ui.activity.main;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.gson.Gson;
import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.databinding.ActivityMainBinding;
import com.ultimate.ecommerce.repository.server.request.base.ProductRequest;

import java.util.ArrayList;
import java.util.List;

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
        List<ProductRequest> products = new ArrayList<>();
        products.add(new ProductRequest(5, 5, 5));
        products.add(new ProductRequest(5, 5, 5));
        products.add(new ProductRequest(5, 5, 5));
        products.add(new ProductRequest(5, 5, 5));
        Gson gson = new Gson();
        Log.d("DELETE", "onCreate: "+gson.toJson(products).toString());
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}