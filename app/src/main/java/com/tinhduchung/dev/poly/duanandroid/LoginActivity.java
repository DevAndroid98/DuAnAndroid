package com.tinhduchung.dev.poly.duanandroid;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tinhduchung.dev.poly.duanandroid.base.BaseActivity;

import io.rmiri.buttonloading.ButtonLoading;
import spencerstudios.com.bungeelib.Bungee;

public class LoginActivity extends BaseActivity {







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtPass =  findViewById(R.id.txtPass);
        imgPreview = findViewById(R.id.imgPreview);
        imgDeletetext =findViewById(R.id.imgDeletetext);
        RunAble runAble=new RunAble(1,this);
        new Thread(runAble).start();
        TextView textGround=findViewById(R.id.txtGround);
        textGround.setTypeface(Typeface.createFromAsset(getAssets(),"font_1.ttf"));

        imgPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (xxx==false){
                    txtPass.setInputType(InputType.TYPE_CLASS_TEXT);
                    imgPreview.setImageResource(R.drawable.ic_preview);
                    xxx=true;
                }else {
                    txtPass.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    imgPreview.setImageResource(R.drawable.ic_unpreview);
                    xxx=false;
                }
            }
        });

        imgDeletetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtPass.setText("");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    public class RunAble implements Runnable {
        int seconds;
        Context context;

        public RunAble(int seconds, Context context) {
            this.seconds = seconds;
            this.context = context;
        }

        @Override
        public void run() {
            for (int i = 0; i <=1; i++) {
                Handler handler = new Handler(Looper.getMainLooper());
                final int intI = i;
                handler.post(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void run() {
                        if (intI==1){

                            if (!txtPass.getText().toString().trim().equals("")){
                                imgPreview.setVisibility(View.VISIBLE);
                                imgDeletetext.setVisibility(View.VISIBLE);
                                Log.e("A","Tình");

                            }else {
                                imgPreview.setVisibility(View.INVISIBLE);
                                imgDeletetext.setVisibility(View.INVISIBLE);
                                Log.e("A","Đức");
                                txtPass.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            }

                            RunAble runAble=new RunAble(1,LoginActivity.this);
                            new Thread(runAble).start();

                        }

                    }

                });

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


        }
    }
}
