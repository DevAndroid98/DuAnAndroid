package com.tinhduchung.dev.poly.duanandroid.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;

public class BaseActivity extends AppCompatActivity {

    //Biến màn hình HomeActivity
    public Animation animationIMG;

    public Animation animationGR;

    public EditText txtPass;

    public ImageView imgPreview;

    public boolean xxx=false;

    public ImageView imgDeletetext;

    public BottomBar bottomBar;

    public BottomBarTab nearby;

    public BottomBarTab nearby1;

    public BottomBarTab nearby2;

    public BottomBarTab nearby3;

    public TextView edtSearch;


    //Biến của màn hình login

    public LoginButton loginButton;
    public CallbackManager callbackManager;
    public FirebaseAuth mAuth;
    public FirebaseAuth.AuthStateListener authStateListener;
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;


}
