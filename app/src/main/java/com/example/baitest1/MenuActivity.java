package com.example.baitest1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.example.baitest1.adapter.Viewadapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    ViewPager2 viewPager2;
    Toolbar toolbar;
    DrawerLayout drawerLayout;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu1){
            viewPager2.setCurrentItem(0);
        } else if (item.getItemId() == R.id.price) {
            viewPager2.setCurrentItem(1);
        } else if (item.getItemId() == R.id.price2) {
            viewPager2.setCurrentItem(2);
        } else if (item.getItemId() == R.id.user) {
            viewPager2.setCurrentItem(3);
        } else {
            viewPager2.setCurrentItem(0);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        bottomNavigationView = findViewById(R.id.dacda);
        viewPager2 = findViewById(R.id.viewparger2);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar,R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle); // an vao de dong mo
        toggle.syncState();// cap nhap trang thai dong mo
        NavigationView navigationView = findViewById(R.id.na_view);
        Menu menu = navigationView.getMenu();
        MenuItem itemLogout = menu.findItem(R.id.logourtwerw);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu1){
                    viewPager2.setCurrentItem(0);
                } else if (item.getItemId() == R.id.price) {
                    viewPager2.setCurrentItem(1);
                } else if (item.getItemId() == R.id.price2) {
                    viewPager2.setCurrentItem(2);
                }else if (item.getItemId() == R.id.user){
                    viewPager2.setCurrentItem(3);
                }
                return true;
            }
        });
        setupViewPager();


    }
    private void setupViewPager(){
        Viewadapter viewadapter = new Viewadapter(getSupportFragmentManager(),getLifecycle());
        viewPager2.setAdapter(viewadapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 0 ){
                    bottomNavigationView.getMenu().findItem(R.id.menu1).setChecked(true);
                }else if (position == 1){
                    bottomNavigationView.getMenu().findItem(R.id.price).setChecked(true);
                }else if (position == 2) {
                    bottomNavigationView.getMenu().findItem(R.id.price2).setChecked(true);
                }else if (position == 3){
                    bottomNavigationView.getMenu().findItem(R.id.user).setChecked(true);
                } else {
                    bottomNavigationView.getMenu().findItem(R.id.menu1).setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

}
