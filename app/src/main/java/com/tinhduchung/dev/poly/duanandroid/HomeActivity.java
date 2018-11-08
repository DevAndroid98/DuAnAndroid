package com.tinhduchung.dev.poly.duanandroid;

import android.drm.DrmStore;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;
import com.tinhduchung.dev.poly.duanandroid.adapter.FragmentAdapter;
import com.tinhduchung.dev.poly.duanandroid.base.BaseActivity;
import com.tinhduchung.dev.poly.duanandroid.fragment.Fragment_Cart;
import com.tinhduchung.dev.poly.duanandroid.fragment.Fragment_Home;
import com.tinhduchung.dev.poly.duanandroid.fragment.Fragment_Menu;
import com.tinhduchung.dev.poly.duanandroid.fragment.Fragment_Notification;

public class HomeActivity extends BaseActivity {
    private EditText edtSearch;
    private FrameLayout fragment;

    private BottomBarTab nearby;
    private BottomBarTab nearby1;
    private BottomBarTab nearby2;
    private BottomBarTab nearby3;
    private Toolbar toolbar;






    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtSearch =  findViewById(R.id.edtSearch);
        fragment =  findViewById(R.id.viewpager);
         //toolbar=findViewById(R.id.toolbar);
        onclickView();

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
         nearby = bottomBar.getTabWithId(R.id.tab_cart);
         nearby1 = bottomBar.getTabWithId(R.id.tab_home);
         nearby2 = bottomBar.getTabWithId(R.id.tab_menu);
        nearby3 = bottomBar.getTabWithId(R.id.tab_notification);
        nearby.setBadgeCount(50);
        nearby2.setBadgeCount(60);
        nearby3.setBadgeCount(1);


         bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
             @Override
             public void onTabSelected(int tabId) {
                 fragment(tabId);
             }
         });

        if (edtSearch.getImeOptions()==EditorInfo.IME_ACTION_DONE){
            Toast.makeText(this, "Oki", Toast.LENGTH_SHORT).show();
        }



        edtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtSearch.setFocusable(true);
                edtSearch.setText("");
                edtSearch.setHint(R.string.seach_store);

            }
        });


        }

        private void onclickView(){

        }



    public void fragment(int id){

        switch (id) {
            case R.id.tab_home:

                getSupportFragmentManager().beginTransaction().replace(R.id.viewpager,
                        new Fragment_Home()).commit();
                break;
            case R.id.tab_cart:
                getSupportFragmentManager().beginTransaction().replace(R.id.viewpager,
                        new Fragment_Cart()).commit();

                break;
            case R.id.tab_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.viewpager,
                        new Fragment_Menu()).commit();
                break;
            case R.id.tab_notification:
                getSupportFragmentManager().beginTransaction().replace(R.id.viewpager,
                        new Fragment_Notification()).commit();
                break;


        }
}

    }
