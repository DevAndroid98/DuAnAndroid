package com.tinhduchung.dev.poly.duanandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CreateAnAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_an_account);

        getSupportActionBar().setTitle(getString(R.string.btn_signup));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
