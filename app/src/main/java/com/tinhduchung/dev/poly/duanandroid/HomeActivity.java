package com.tinhduchung.dev.poly.duanandroid;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.roughike.bottombar.OnTabSelectListener;
import com.tinhduchung.dev.poly.duanandroid.base.BaseActivity;
import com.tinhduchung.dev.poly.duanandroid.fragment.Fragment_Cart;
import com.tinhduchung.dev.poly.duanandroid.fragment.Fragment_Home;
import com.tinhduchung.dev.poly.duanandroid.fragment.Fragment_Menu;
import com.tinhduchung.dev.poly.duanandroid.fragment.Fragment_Notification;
import com.tinhduchung.dev.poly.duanandroid.user.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import spencerstudios.com.bungeelib.Bungee;

public class HomeActivity extends BaseActivity {
    private DatabaseReference mDatabase;
    private ArrayList<User.cartsp> giohangArray = new ArrayList<>();
    private String id="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bungee.zoom(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.tinhduchung.dev.poly.duanandroid",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        mapped();
        onclickView();
        getcart();
    }

    //ánh xạ view
    private void mapped() {
        edtSearch = findViewById(R.id.edtSearch);
        bottomBar = findViewById(R.id.bottomBar);
        nearby = bottomBar.getTabWithId(R.id.tab_cart);
        nearby1 = bottomBar.getTabWithId(R.id.tab_home);
        nearby2 = bottomBar.getTabWithId(R.id.tab_menu);
        nearby3 = bottomBar.getTabWithId(R.id.tab_notification);

    }

    //các sự kiện click

    private void onclickView() {
        edtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtSearch.setText("");
                edtSearch.setHint(R.string.seach_store);

            }
        });
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                fragment(tabId);
            }
        });
    }

    //thông báo của bottom navigation

    private void notification() {

        nearby2.setBadgeCount(60);
        nearby3.setBadgeCount(1);
    }

    //bottom cilck chuyển đổi giữa các fragment

    public void fragment(int id) {

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

    @Override
    public void onBackPressed() {
        HomeActivity.this.finish();
    }

    private void getcart(){
        if (id!=null){
            giohangArray.clear();
            mDatabase.child("id").child("User").child(id).child("cart").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    User.cartsp cartsp=dataSnapshot.getValue(User.cartsp.class);
                    giohangArray.add(cartsp);
                    nearby.setBadgeCount(giohangArray.size());
                    Log.e("KEY",giohangArray.size()+"");
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Log.e("KEY","a");
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    Log.e("KEY","b");
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Log.e("KEY","c");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e("KEY","d");
                }
            }) ;
            }
    }
}
