package com.tinhduchung.dev.poly.duanandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.tinhduchung.dev.poly.duanandroid.base.BaseActivity;

import spencerstudios.com.bungeelib.Bungee;

public class SplashScreen extends BaseActivity {
    private ImageView imageView2;
    private TextView txtLogo;
    private Typeface typeface;
    private LinearLayout liner;
    private TextView txtGround;
    private NumberProgressBar numberProgressBar;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        imageView2 = findViewById(R.id.imageView2);
        txtLogo = findViewById(R.id.txtLogo);
        liner = findViewById(R.id.liner);
        txtGround = findViewById(R.id.txtGround);
        numberProgressBar = findViewById(R.id.number_progress_bar);
        typeface=Typeface.createFromAsset(this.getAssets(),"font_1.ttf");
        animationIMG= AnimationUtils.loadAnimation(this,R.anim.anim3);
        animationGR= AnimationUtils.loadAnimation(this,R.anim.anim1);
        txtLogo.setTypeface(typeface);
        imageView2.setAnimation(animationIMG);
        txtLogo.setAnimation(animationIMG);
        txtGround.setTypeface(typeface);
        RunAble runAble=new RunAble(5,this);
        new Thread(runAble).start();



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
            for (int i = 0; i <=4; i++) {
                Handler handler = new Handler(Looper.getMainLooper());
                final int intI = i;
                handler.post(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void run() {
                        if (intI==2){

                           liner.setVisibility(View.VISIBLE);
                           liner.setAnimation(animationGR);
                        }
                        if (intI==4){

                            RunAble1 runAble1=new RunAble1(5,SplashScreen.this);
                            new Thread(runAble1).start();
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

    public class RunAble1 implements Runnable {
        int seconds;
        Context context;

        public RunAble1(int seconds, Context context) {
            this.seconds = seconds;
            this.context = context;
        }

        @Override
        public void run() {
            for (int i = 0; i <=100; i++) {
                Handler handler = new Handler(Looper.getMainLooper());
                final int intI = i;
                handler.post(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void run() {
                        numberProgressBar.setVisibility(View.VISIBLE);
                        numberProgressBar.setProgress(intI);
                        if (intI==100){
                            startActivity(new Intent(SplashScreen.this,LoginActivity.class));
                            Bungee.zoom(context);
                            finish();
                        }
                    }

                });

                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
