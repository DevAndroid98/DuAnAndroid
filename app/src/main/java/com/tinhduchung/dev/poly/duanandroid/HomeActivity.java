package com.tinhduchung.dev.poly.duanandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tinhduchung.dev.poly.duanandroid.base.BaseActivity;

public class HomeActivity extends BaseActivity {
    private EditText edtSearch;






    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtSearch =  findViewById(R.id.edtSearch);

        edtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtSearch.setText("");
                edtSearch.setHint(R.string.seach_store);

            }
        });



    }
}
