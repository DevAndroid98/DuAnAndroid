package com.tinhduchung.dev.poly.duanandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tinhduchung.dev.poly.duanandroid.base.BaseActivity;
import com.tinhduchung.dev.poly.duanandroid.fragment.Fragment_Menu;


public class LoginActivity extends BaseActivity {
    private Button btn_create_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mapped();
        method();
        onclick();

    }

    //Ánh xạ
    private void mapped() {
        txtPass = findViewById(R.id.txtPass);
        imgPreview = findViewById(R.id.imgPreview);
        imgDeletetext = findViewById(R.id.imgDeletetext);
        loginButton = findViewById(R.id.login_button);
        btn_create_account = findViewById(R.id.btn_create_account);
        callbackManager = CallbackManager.Factory.create();
        mAuth = FirebaseAuth.getInstance();
        sharedPreferences=getSharedPreferences("Data",MODE_PRIVATE);

    }

    //Các sự kiện onClick
    private void onclick() {
        imgPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (xxx == false) {
                    txtPass.setInputType(InputType.TYPE_CLASS_TEXT);
                    imgPreview.setImageResource(R.drawable.ic_preview);
                    xxx = true;
                } else {
                    txtPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    imgPreview.setImageResource(R.drawable.ic_unpreview);
                    xxx = false;
                }
            }
        });

        imgDeletetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtPass.setText("");
            }
        });
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }


        });
        btn_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, CreateAnAccountActivity.class));
            }
        });
    }

    private void method() {
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                        Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                        intent.putExtra("name",user.getDisplayName());
                        intent.putExtra("uri",String.valueOf(user.getPhotoUrl()));
                        intent.putExtra("id",user.getUid());
                        startActivity(intent);
                         finish();
                } else {



                }
            }
        };

        RunAble runAble = new RunAble(1, this);
        new Thread(runAble).start();
        TextView textGround = findViewById(R.id.txtGround);
        textGround.setTypeface(Typeface.createFromAsset(getAssets(), "font_1.ttf"));
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
            for (int i = 0; i <= 1; i++) {
                Handler handler = new Handler(Looper.getMainLooper());
                final int intI = i;
                handler.post(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void run() {
                        if (intI == 1) {

                            if (!txtPass.getText().toString().trim().equals("")) {
                                imgPreview.setVisibility(View.VISIBLE);
                                imgDeletetext.setVisibility(View.VISIBLE);


                            } else {
                                imgPreview.setVisibility(View.INVISIBLE);
                                imgDeletetext.setVisibility(View.INVISIBLE);

                                txtPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            }

                            RunAble runAble = new RunAble(1, LoginActivity.this);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("TAG", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d("TAG", user.getUid());

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }
}
