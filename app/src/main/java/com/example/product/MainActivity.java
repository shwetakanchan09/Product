package com.example.product;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {
    Activity context;
    Toolbar toolbar;
    Fragment fragment;
    String fName;
    FragmentManager fm;
    Products products;
    int id;
    ProductFragment productFragment;
    //= new ProductFragment()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        context = this;
        setSupportActionBar(toolbar);
        toolbar.setTitle("Products");
        toolbar.setTitleTextColor(Color.BLACK);

        replaceFragment(new ProductFragment(products,id));
    }
    @Override
    public void onBackPressed() {

            replaceFragment(productFragment);

            super.onBackPressed();
        }

    public void replaceFragment(Fragment fragment) {
        this.fragment = fragment;
        fName = fragment.getClass().getSimpleName();
        fm = getSupportFragmentManager();
        FragmentTransaction fTransaction = fm.beginTransaction();
        fTransaction.replace(R.id.frame, fragment);
        // overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        fTransaction.addToBackStack(null);
        fTransaction.commit();
    }

}