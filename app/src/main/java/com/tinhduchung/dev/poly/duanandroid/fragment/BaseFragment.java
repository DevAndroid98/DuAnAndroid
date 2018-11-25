package com.tinhduchung.dev.poly.duanandroid.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mikhaellopez.circularimageview.CircularImageView;

public class BaseFragment extends Fragment {
    public View view;
    public Intent intent;
    public int REQUEST_CODE_FOLDER = 123;
    public StorageReference storageRef;
    public CircularImageView imgUser;
    public TextView txtUsername;
    public Button btnLogin;
    public FirebaseStorage storage;
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    public LinearLayout cvUserinfor;
    public LinearLayout cvCart;
    public LinearLayout cvAddProduct;
    public LinearLayout cvManageProduct;
    public LinearLayout cvHelp;
    public LinearLayout cvQuit;
    public LinearLayout cvManage;



}
