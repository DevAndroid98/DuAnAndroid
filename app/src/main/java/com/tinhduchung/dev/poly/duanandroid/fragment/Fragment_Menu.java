package com.tinhduchung.dev.poly.duanandroid.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.Login;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;
import com.tinhduchung.dev.poly.duanandroid.HomeActivity;
import com.tinhduchung.dev.poly.duanandroid.LoginActivity;
import com.tinhduchung.dev.poly.duanandroid.R;

import spencerstudios.com.bungeelib.Bungee;

public class Fragment_Menu extends BaseFragment {
    private CircularImageView imgUser;
    private TextView txtUsername;
    private Button btnLogin;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu, container, false);
        intent = getActivity().getIntent();
        mapped();
       String name="";
       String uri="";
       name=intent.getStringExtra("name");
       uri=intent.getStringExtra("uri");

        if (name==null && uri==null){

            btnLogin.setVisibility(View.VISIBLE);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                   // getActivity().finish();
                }
            });
        }else {
            btnLogin.setText(R.string.logoutfb);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoginManager.getInstance().logOut();
                    FirebaseAuth.getInstance().signOut();
                    txtUsername.setText("");
                    imgUser.setImageResource(R.drawable.ic_users);
                    btnLogin.setText(R.string.btn_login);
                    startActivity(new Intent(getActivity(), HomeActivity.class));
                    getActivity().finish();
                }
            });
            Picasso.get().load(Uri.parse(intent.getStringExtra("uri"))).resize(50,50).centerCrop().into(imgUser);
            txtUsername.setText(intent.getStringExtra("name"));
        }
        return view;
    }


    //ánh xạ

    private void mapped(){
        imgUser = view.findViewById(R.id.imgUser);
        txtUsername = view.findViewById(R.id.txtUsername);
        btnLogin = view.findViewById(R.id.btnLogin);
    }
}
