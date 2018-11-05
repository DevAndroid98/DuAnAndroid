package com.tinhduchung.dev.poly.duanandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CreateAnAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_an_account);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.btn_sign_up));
    }
}
