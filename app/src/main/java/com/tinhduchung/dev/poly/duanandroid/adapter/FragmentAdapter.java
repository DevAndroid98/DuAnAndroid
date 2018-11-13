package com.tinhduchung.dev.poly.duanandroid.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.tinhduchung.dev.poly.duanandroid.fragment.Fragment_Cart;
import com.tinhduchung.dev.poly.duanandroid.fragment.Fragment_Home;
import com.tinhduchung.dev.poly.duanandroid.fragment.Fragment_Menu;
import com.tinhduchung.dev.poly.duanandroid.fragment.Fragment_Notification;

public class FragmentAdapter extends FragmentPagerAdapter {
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new Fragment_Cart();
            case 0:

                return new Fragment_Home();

                case 2:

                return new Fragment_Notification();

            case 3:
                return new Fragment_Menu();

                default:
                    return new Fragment_Home();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }


}
