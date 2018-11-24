package com.tinhduchung.dev.poly.duanandroid.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.tinhduchung.dev.poly.duanandroid.R;
import com.tinhduchung.dev.poly.duanandroid.adapter.GridAdapter;
import com.tinhduchung.dev.poly.duanandroid.adapter.ImageAdapter;
import com.tinhduchung.dev.poly.duanandroid.adapter.ProductAdapter;
import com.tinhduchung.dev.poly.duanandroid.user.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Fragment_Home extends BaseFragment {
    private RecyclerView recyclerviewProductBoy;
    private RecyclerView recyclerviewProductGirl;
    private RecyclerView recyclerviewProductPhone;
    private RecyclerView recyclerviewProductHouseware;
    private RecyclerView recyclerviewProductnew;
    private ProductAdapter productAdapter;
    private ProductAdapter productAdaptergirl;
    private ProductAdapter productAdapterphone;
    private ProductAdapter productAdapterhouse;
    private ProductAdapter productAdapternew;
    private GridAdapter gridAdapter;

    private LinearLayoutManager linearLayoutManager,linearLayoutManager1,getLinearLayoutManager2,getLinearLayoutManager3,getGetLinearLayoutManager4;


    private ArrayList<User.Product> products = new ArrayList<>();
    private ArrayList<User.Product> productsgirl = new ArrayList<>();
    private ArrayList<User.Product> productsphone = new ArrayList<>();
    private ArrayList<User.Product> productshouse= new ArrayList<>();
    private ArrayList<User.Product> productnew= new ArrayList<>();
   private List<User.Product> list=new ArrayList<>();


    private DatabaseReference mDatabase;
    private StorageReference storageRef;
    private FirebaseStorage storage;
    private ArrayList<String> path = new ArrayList<>();
    private CardView cvForMan;
    private TextView btnMoreman;

    private  int i=0;


  private TextView textView;






    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        mapped();
        getiduser();

        productAdapter=new ProductAdapter(products,Fragment_Home.this);
        productAdaptergirl=new ProductAdapter(productsgirl,Fragment_Home.this);
        productAdapterphone=new ProductAdapter(productsphone,Fragment_Home.this);
        productAdapterhouse=new ProductAdapter(productshouse,Fragment_Home.this);
        productAdapternew=new ProductAdapter(productnew,Fragment_Home.this);
        linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        linearLayoutManager1=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        getLinearLayoutManager2=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        getLinearLayoutManager3=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        getGetLinearLayoutManager4=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);

        recyclerviewProductBoy.setLayoutManager(linearLayoutManager);
        recyclerviewProductGirl.setLayoutManager(linearLayoutManager1);
        recyclerviewProductPhone.setLayoutManager(getLinearLayoutManager2);
        recyclerviewProductHouseware.setLayoutManager(getLinearLayoutManager3);
        recyclerviewProductnew.setLayoutManager(getGetLinearLayoutManager4);
        recyclerviewProductBoy.setAdapter(productAdapter);
        recyclerviewProductGirl.setAdapter(productAdaptergirl);
        recyclerviewProductPhone.setAdapter(productAdapterphone);
        recyclerviewProductHouseware.setAdapter(productAdapterhouse);
        recyclerviewProductnew.setAdapter(productAdapternew);


        btnMoreman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reviews();
            }
        });
        return view;
    }

    private void mapped() {
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://onlinestore-3ac1a.appspot.com");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        cvForMan = view.findViewById(R.id.cvForMan);
        btnMoreman =view.findViewById(R.id.btnMoreman);
        btnMoreman =view.findViewById(R.id.btnMoreman);
        recyclerviewProductGirl = view.findViewById(R.id.recyclerviewProductGirl);
        recyclerviewProductBoy = view.findViewById(R.id.recyclerviewProductBoy);
        recyclerviewProductPhone = view.findViewById(R.id.recyclerviewProductPhone);
        recyclerviewProductHouseware = view.findViewById(R.id.recyclerviewProductHouseware);
        recyclerviewProductnew = view.findViewById(R.id.recycylerviewnew);
        btnMoreman =view.findViewById(R.id.btnMoreman);


    }



    private void getiduser(){

        path.clear();
        final String nam="Quần áo nam";
        final String nu="Quần áo nữ";
        final String dienthoai="Điện thoại";
        final String dogiadung="Đồ gia dụng";
        products.clear();
        productsgirl.clear();
        productnew.clear();
        mDatabase.child("id").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                if (dataSnapshot.getKey() !=null && dataSnapshot.getKey().startsWith("sp:")) {
                    mDatabase.child("id").child(dataSnapshot.getKey()).child("product").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            User.Product product=dataSnapshot.getValue(User.Product.class);
                            Log.e("TAG",product.toString());
                            products.add(0,product);
                            productsgirl.add(0,product);
                            productsphone.add(0,product);
                            productshouse.add(0,product);
                            list.add(0,product);
                            if (productnew.size()<50){
                                productnew.add(0,product);
                                i++;
                            }
                            for (i=0;i<products.size();i++){
                                if (products!=null ){
                                    if (!nam.equalsIgnoreCase(products.get(i).getLoaisp())){
                                        products.remove(i);

                                        }
                                        }
                            }
                            for (i=0;i<productsgirl.size();i++){
                                if (productsgirl!=null ){
                                    if (!nu.equalsIgnoreCase(productsgirl.get(i).getLoaisp())){
                                        productsgirl.remove(i);
                                    }

                                }
                            }

                            for (i=0;i<productshouse.size();i++){
                                if (productshouse!=null ){
                                    if (!dogiadung.equalsIgnoreCase(productshouse.get(i).getLoaisp())){
                                        productshouse.remove(i);
                                    }

                                }
                            }

                            for (i=0;i<productsphone.size();i++){
                                if (productsphone!=null ){
                                    if (!dienthoai.equalsIgnoreCase(productsphone.get(i).getLoaisp())){
                                        productsphone.remove(i);
                                    }
                                    }
                            }

                            Log.e("TAG",products.toString());
                            Log.e("TAGGIRL",productsgirl.toString());
                            productAdaptergirl.notifyDataSetChanged();
                            productAdapter.notifyDataSetChanged();
                            productAdapterphone.notifyDataSetChanged();
                            productAdapterhouse.notifyDataSetChanged();
                            productAdapternew.notifyDataSetChanged();
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
                    Log.e("TAG",products.size()+"");


                }


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


    }


    public void getproductboy(){
         products.clear();

            mDatabase.child("id").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

               //     products.add(0,product);
//                    productAdapter.notifyDataSetChanged();
//                    Log.e("SIZE",products.size()+"");
                   Log.e("TAG",dataSnapshot.toString());



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





    }

    public void reviews(){

        final Dialog dialog=new Dialog(getActivity(),R.style.PauseDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.item_for_boy);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;

        //dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialog;

        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        ImageView left;

          left =  dialog.findViewById(R.id.left);
           left.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   dialog.cancel();
               }
           });
           GridView gif=dialog.findViewById(R.id.gif);
           gridAdapter=new GridAdapter(Fragment_Home.this,products);
           gif = dialog.findViewById(R.id.gif);
           gif.setAdapter(gridAdapter);


        dialog.show();

    }


    public void clickproduct(User.Product product,int prosion){
        final Dialog dialog=new Dialog(getActivity(),R.style.PauseDialog1);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.item_click);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.TOP;

        //dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialog;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);

         TextView txtProduct;
         RecyclerView recyclerviewimg;
         TextView txProduct;
         TextView txtprice;
         TextView txtnameshop;
         TextView txtSanPham;
         TextView txtdate;
         TextView txtloai;
         TextView txttinhtrang;
         TextView txttrangthai;
         TextView txtsoluong;
         TextView txtmota;

         txtloai = dialog.findViewById(R.id.txtloai);
        txttinhtrang = dialog.findViewById(R.id.txttinhtrang);
        txttrangthai =  dialog.findViewById(R.id.txttrangthai);
        txtsoluong = dialog.findViewById(R.id.txtsoluong);
        txtmota =  dialog.findViewById(R.id.txtmota);

        LinearLayoutManager imglayout;
        txtProduct = dialog.findViewById(R.id.txtProduct);
        recyclerviewimg =  dialog.findViewById(R.id.recyclerviewimg);
        txProduct =  dialog.findViewById(R.id.txProduct);
        txtprice =  dialog.findViewById(R.id.txtprice);
        txtnameshop = dialog.findViewById(R.id.txtnameshop);
        txtSanPham =  dialog.findViewById(R.id.txtSanPham);
        txtdate =  dialog.findViewById(R.id.txtdate);
        textView=dialog.findViewById(R.id.posion);
        imglayout=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        ArrayList<String>  uri=new ArrayList<>();
        uri.clear();
        ImageAdapter imageAdapter=new ImageAdapter(Fragment_Home.this,uri);
        recyclerviewimg.setLayoutManager(imglayout);
        recyclerviewimg.setAdapter(imageAdapter);

        uri.add(product.getUri());
        uri.add(product.getUri());
        uri.add(product.getUri());
        uri.add(product.getUri());
        imageAdapter.notifyDataSetChanged();
        txProduct.setText(product.getNameproduct());
        txtProduct.setText(product.getNameproduct());
        txtnameshop.setText("Tên shop:"+product.getNameshop());
        txtprice.setText("đ"+product.getPriceproduct());
        txtloai.setText(product.getLoaisp());
        txttrangthai.setText(product.getStatus());
        txttinhtrang.setText(product.getLovestatus());
        txtmota.setText(product.getDescribe());
        txtsoluong.setText(product.getSoluong());
        String idsp=product.getIdsp();

        String thoigia=product.getThoigian();
        Calendar calendar=Calendar.getInstance();
        int date= (int) ((calendar.getTimeInMillis()-Long.valueOf(thoigia))/(1000*60*60*24))+1;
        if (date==0){
            txtdate.setText("Hôm nay");
        }else if (date<=30){
            txtdate.setText(date+"ngày");
        }else {

        }

        dialog.show();
    }

}
