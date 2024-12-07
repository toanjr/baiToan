package com.example.baitest1.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.baitest1.MenuFragment;
import com.example.baitest1.Price2Fragment;
import com.example.baitest1.PriceFragment;
import com.example.baitest1.UserFragment;

public class Viewadapter extends FragmentStateAdapter {


    public Viewadapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            return new MenuFragment();
        } else if (position == 1) {
            return new PriceFragment();
        } else if (position == 2) {
            return new Price2Fragment();
        }else if (position == 3){
            return new UserFragment();
        } else {
            return new MenuFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
