package com.tinhduchung.dev.poly.duanandroid;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.graphics.Color;


import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.tinhduchung.dev.poly.duanandroid.adapter.Adapter;


import com.tinhduchung.dev.poly.duanandroid.adapter.ColorAdapter;
import com.tinhduchung.dev.poly.duanandroid.model.ColorModel;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.common.ImageLoader;
import com.yuyh.library.imgsel.config.ISListConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import pl.droidsonroids.gif.GifImageView;

public class AddProductActivity extends AppCompatActivity {
    private ScrollView layout;
    private EditText edtTenshop;
    private RecyclerView recyclerviewimg;
    private EditText edtTensp;
    private EditText edtGia;
    private Spinner spinner;
    private EditText edtSoluong;
    private Button btnChonAnh;
    private Button btnChonmau;
    private Button btnTinhtrang;
    private Button btnTrangthai;
    private EditText edtMota;
    private Button btnDangsp;
    private GifImageView loading;
    private String id = "";
    private static final int REQUEST_LIST_CODE = 0;
    private DatabaseReference mDatabase;
    private StorageReference storageRef;
    private FirebaseStorage storage;

    private Adapter adapter;
    private int i = 0;
    private ArrayList<String> uri = new ArrayList<>();

    private ArrayList<String> path = new ArrayList<>();

    private List<ColorModel> models = new ArrayList<>();

    private List<String> listcolor = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://onlinestore-3ac1a.appspot.com");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        mapped();
        onclick();
        ISNav.getInstance().init(new ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerviewimg);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddProductActivity.this, LinearLayoutManager.HORIZONTAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(AddProductActivity.this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new Adapter(AddProductActivity.this, path);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        addproduct();


    }


    private void mapped() {
        layout = findViewById(R.id.layout);
        edtTenshop = findViewById(R.id.edt_tenshop);
        recyclerviewimg = findViewById(R.id.recyclerviewimg);
        edtTensp = findViewById(R.id.edt_tensp);
        edtGia = findViewById(R.id.edt_gia);
        spinner = findViewById(R.id.spinner);
        edtSoluong = findViewById(R.id.edt_soluong);
        btnChonmau = findViewById(R.id.btn_chonmau);
        btnTinhtrang = findViewById(R.id.btn_tinhtrang);
        btnTrangthai = findViewById(R.id.btn_trangthai);
        edtMota = findViewById(R.id.edt_mota);
        btnDangsp = findViewById(R.id.btn_dangsp);
        loading = findViewById(R.id.loading);
        btnChonAnh = findViewById(R.id.btn_themanh);
    }

    private void onclick() {
        btnDangsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnChonmau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addlistcolor();
            }
        });

        btnTinhtrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qualitysp();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_LIST_CODE && resultCode == RESULT_OK && data != null) {
            ArrayList<String> pathList = data.getStringArrayListExtra("result");
            uri.clear();
            path.clear();
            i = 0;
            path.addAll(pathList);
            uploadImage();
            adapter.notifyDataSetChanged();
        }

    }


    public void imgselector(View view) {
        ISListConfig config = new ISListConfig.Builder()
                .multiSelect(true)

                .rememberSelected(false)

                .statusBarColor(Color.parseColor("#3F51B5")).build();
        ISNav.getInstance().toListActivity(this, config, REQUEST_LIST_CODE);
    }

    private void uploadImage() {

        if (path != null && i < path.size()) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Tải ảnh lên...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            Calendar calendar = Calendar.getInstance();
            final StorageReference mountainsRef = storageRef.child("image" + calendar.getTimeInMillis() + "");

            mountainsRef.putFile(Uri.fromFile(new File(path.get(i))))
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful()) ;
                            Uri downloadUrl = urlTask.getResult();
                            Log.e("URI", downloadUrl + "");
                            boolean b = uri.add(String.valueOf(downloadUrl));
                            Log.e("B", b + "");
                            Log.e("C", uri.size() + "");
                            i++;
                            uploadImage();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Log.e("Failed", e.getMessage());
                            return;
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Đang tải" + "(" + (i + 1) + "/" + (path.size()) + ")" + (int) progress + "%");

                            if (i == path.size() - 1 && progress == 100) {
                                {

                                    Toast.makeText(AddProductActivity.this, "Tải thành công", Toast.LENGTH_SHORT).show();

                                }
                            }
                        }
                    });


        }

    }

    private void addproduct() {

        ArrayList<String> listLoai = new ArrayList<>();

        listLoai.add("Chọn loại");
        listLoai.add("Quần áo nam");
        listLoai.add("Quần áo nữ");
        listLoai.add("Điện thoại");
        listLoai.add("Đồ gia dụng");


        ArrayAdapter adapter = new ArrayAdapter(AddProductActivity.this, android.R.layout.simple_spinner_item, listLoai);
        spinner.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                btnDangsp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nameshop = edtTenshop.getText().toString().trim();
                        String nameproduc = edtTensp.getText().toString().trim();
                        String price = edtGia.getText().toString().trim();
                        String des = edtMota.getText().toString().trim();
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void addlistcolor() {
        models.clear();
        listcolor.clear();
        models.add(new ColorModel("Xanh"));
        models.add(new ColorModel("Đỏ"));
        models.add(new ColorModel("Tím"));
        models.add(new ColorModel("Vàng"));
        models.add(new ColorModel("Lục"));
        models.add(new ColorModel("Lam"));
        models.add(new ColorModel("Chàm"));
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.item_color);
        dialog.setCanceledOnTouchOutside(false);
        GridView girdlayout;
        girdlayout = dialog.findViewById(R.id.girdlayout);
        final ColorAdapter colorAdapter = new ColorAdapter(AddProductActivity.this, models);
        girdlayout.setAdapter(colorAdapter);
        final Button btnthemmau;
        final Button btnThem;
        final EditText edtthemmau;
        btnthemmau = dialog.findViewById(R.id.txtThem);
        btnThem = dialog.findViewById(R.id.btn_them);
        edtthemmau = dialog.findViewById(R.id.edtthemmau);

        btnthemmau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtthemmau.getText().toString().trim().equals("")) {
                    return;
                }
                boolean b = false;
                for (int i = 0; i < models.size(); i++) {
                    if (edtthemmau.getText().toString().trim().equalsIgnoreCase(models.get(i).getColor())) {
                        b = true;
                        break;
                    }
                }
                if (b == false) {
                    models.add(new ColorModel(edtthemmau.getText().toString().trim()));
                    colorAdapter.notifyDataSetChanged();
                }
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (listcolor==null){
                    return;
                }else {
                    String data="";
                    for (int i=0;i<listcolor.size();i++){
                        data+=listcolor.get(i)+",";
                        btnChonmau.setText("Màu:"+data);
                        btnChonmau.setTextColor(R.color.greenDark);
                        btnChonmau.setTextSize(10f);
                    }
                    dialog.dismiss();
                }
            }
        });


        dialog.show();
    }

    public void addcolor(int position){
        boolean a= listcolor.add(models.get(position).getColor());
        Log.e("TAG",a+"");
        Log.e("TAG",listcolor.size()+"");
        }

    public void deletecolor(String color){

       for (int i=0;i<listcolor.size();i++){
           if (color.equals(listcolor.get(i))){
               listcolor.remove(i);
           }
       }
       Log.e("TAG",listcolor.size()+"");
       }

       private void qualitysp(){
           PopupMenu popupMenu=new PopupMenu(this,btnTinhtrang);
           popupMenu.getMenuInflater().inflate(R.menu.qualitysp,popupMenu.getMenu());
           popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
               @Override
               public boolean onMenuItemClick(MenuItem item) {
                   return true;
               }
           });
           popupMenu.show();
       }
}
