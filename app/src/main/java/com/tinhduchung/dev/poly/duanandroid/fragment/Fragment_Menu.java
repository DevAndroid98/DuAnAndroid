package com.tinhduchung.dev.poly.duanandroid.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.Login;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;
import com.tinhduchung.dev.poly.duanandroid.HomeActivity;
import com.tinhduchung.dev.poly.duanandroid.LoginActivity;
import com.tinhduchung.dev.poly.duanandroid.R;
import com.tinhduchung.dev.poly.duanandroid.SplashScreen;
import com.tinhduchung.dev.poly.duanandroid.user.User;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;
import spencerstudios.com.bungeelib.Bungee;

import static android.app.Activity.RESULT_OK;

public class Fragment_Menu extends BaseFragment {
    String name = "";
    String uri = "";
    String provice ="";
    String phone = "";
    String id = "";
    private  Dialog dialog;
    private DatabaseReference mDatabase;


    private LinearLayout linearLayout;
    private GifImageView loading;

    ImageView imginfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu, container, false);
        intent = getActivity().getIntent();
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://onlinestore-3ac1a.appspot.com");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        sharedPreferences=getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);
        mapped();

        name = intent.getStringExtra("name");
        uri = intent.getStringExtra("uri");
        provice = intent.getStringExtra("provider");
        id = intent.getStringExtra("id");

        if (provice == null) {
            btnLogin.setVisibility(View.VISIBLE);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finish();

                }
            });
        } else {
            if (provice.equals("facebook.com")) {
                btnLogin.setText(R.string.logoutfb);
                btnLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LoginManager.getInstance().logOut();
                        FirebaseAuth.getInstance().signOut();
                        txtUsername.setText("");
                        imgUser.setImageResource(R.drawable.ic_users);
                        btnLogin.setText(R.string.btn_login);
                        startActivity(new Intent(getActivity(),HomeActivity.class));
                        getActivity().finish();
                    }
                });
                Picasso.get().load(Uri.parse(uri)).resize(50, 50).centerCrop().into(imgUser);
                txtUsername.setText(name);
            } else {

                mDatabase.child(id).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        User user=dataSnapshot.getValue(User.class);
                             name=user.getName();
                             uri=user.getUri();
                            txtUsername.setText(name);
                            Picasso.get().load(Uri.parse(uri)).resize(50, 50).centerCrop().into(imgUser);
                            }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                btnLogin.setText(R.string.logoutfb);
                btnLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LoginManager.getInstance().logOut();
                        FirebaseAuth.getInstance().signOut();
                        txtUsername.setText("");
                        imgUser.setImageResource(R.drawable.ic_users);
                        btnLogin.setText(R.string.btn_login);
                        startActivity(new Intent(getActivity(),HomeActivity.class));
                        getActivity().finish();

                    }
                });
                if (name==null && uri==null){
                    dialog();
                }

            }

        }
        return view;
    }


    //ánh xạ

    private void mapped() {
        imgUser = view.findViewById(R.id.imgUser);
        txtUsername = view.findViewById(R.id.txtUsername);
        btnLogin = view.findViewById(R.id.btnLogin);
    }

    public void dialog() {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_user);
        final EditText edtphone;
        final EditText edtname;

        Button btninfo;
        loading = dialog.findViewById(R.id.loading);
        linearLayout = dialog.findViewById(R.id.layout);
        edtphone = dialog.findViewById(R.id.edtphone);
        edtname = dialog.findViewById(R.id.edtname);
        imginfo = dialog.findViewById(R.id.imginfo);
        btninfo = dialog.findViewById(R.id.btninfo);
        edtphone.setText(intent.getStringExtra("phone"));
        edtphone.setEnabled(true);
        imginfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = edtphone.getText().toString();
                final String name1 = edtname.getText().toString();
                if (phone.equals("")) {
                    edtphone.setError(getString(R.string.error));
                    return;
                }
                if (!phone.startsWith("+84") && !phone.startsWith("0")) {
                    edtphone.setError(getString(R.string.error_1));
                    return;
                }
                if (phone.length() != 11 && phone.length() != 12) {
                    edtphone.setError(getString(R.string.error_2));
                    return;
                }
                if (name1.equals("")) {
                    edtname.setError(getString(R.string.error));
                    return;
                }
                if (uri == null) {
                    Toast.makeText(getActivity(), "Chọn ảnh", Toast.LENGTH_SHORT).show();
                    return;
                }
                User user = new User(name1, phone, uri);
                loading.setVisibility(View.VISIBLE);
                linearLayout.setAlpha(0.3f);
                mDatabase.child(id).child("info").setValue(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                mDatabase.child(id).addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                        User user1 = dataSnapshot.getValue(User.class);
                                        uri = user1.getUri();
                                        name = user1.getName();
                                        txtUsername.setText(name);
                                        Picasso.get().load(Uri.parse(uri)).resize(50, 50).centerCrop().into(imgUser);
                                    }

                                    @Override
                                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                    }

                                    @Override
                                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                                    }

                                    @Override
                                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                dialog.dismiss();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });

            }
        });
        dialog.show();
    }

    public void uploadAnh(ImageView imageView) {
        Calendar calendar = Calendar.getInstance();
        final StorageReference mountainsRef = storageRef.child("image" + calendar.getTimeInMillis() + "");
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        final UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(getActivity(), "Hình chưa được lưu", Toast.LENGTH_SHORT).show();
                loading.setVisibility(View.INVISIBLE);
                linearLayout.setAlpha(1);
                imginfo.setImageResource(R.drawable.ic_user);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!urlTask.isSuccessful()) ;
                Uri downloadUrl = urlTask.getResult();
                uri = String.valueOf(downloadUrl);
                loading.setVisibility(View.INVISIBLE);
                linearLayout.setAlpha(1);
                Toast.makeText(getActivity(), "Hình đã được lưu", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_CODE_FOLDER);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            imginfo.setImageURI(uri);
            uploadAnh(imginfo);
            loading.setVisibility(View.VISIBLE);
            linearLayout.setAlpha(0.3f);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
