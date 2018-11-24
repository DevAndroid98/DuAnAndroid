package com.tinhduchung.dev.poly.duanandroid.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tinhduchung.dev.poly.duanandroid.R;
import com.tinhduchung.dev.poly.duanandroid.adapter.CartAdapter;
import com.tinhduchung.dev.poly.duanandroid.user.User;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class Fragment_Cart extends BaseFragment {

    private RecyclerView recyclerviewcart;
    private DatabaseReference mDatabase;
    private ArrayList<User.cartsp> giohangArray = new ArrayList<>();
    private ArrayList<User.Product> products = new ArrayList<>();
    private String id = "";
    private ArrayList<String> idspham = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    private CartAdapter cartAdapter;
    private ScrollView scrollView;
    private GifImageView loading;
    private CardView cart;
    private TextView pricetong;
    private Button btnxacnhan;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Intent intent = getActivity().getIntent();
        id = intent.getStringExtra("id");
        recyclerviewcart = view.findViewById(R.id.recyclerviewcart);
        loading = view.findViewById(R.id.loading);
        scrollView = view.findViewById(R.id.scrollView);
        cart =  view.findViewById(R.id.cart);
        pricetong =  view.findViewById(R.id.pricetong);
        btnxacnhan =  view.findViewById(R.id.btnxacnhan);
        loading.setVisibility(View.VISIBLE);
        scrollView.setAlpha(0.2f);
        cartAdapter = new CartAdapter(Fragment_Cart.this, products, giohangArray);
        recyclerviewcart.setLayoutManager(linearLayoutManager);
        recyclerviewcart.setHasFixedSize(true);
        recyclerviewcart.setAdapter(cartAdapter);
        getcart();


        return view;
    }

    private void getcart() {
        if (id != null) {
            giohangArray.clear();
            products.clear();
            idspham.clear();
            mDatabase.child("id").child("User").child(id).child("cart").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    User.cartsp cartsp = dataSnapshot.getValue(User.cartsp.class);
                    if (dataSnapshot.getKey() != null) {
                        Log.e("CHECK", dataSnapshot.getKey());
                        idspham.add(dataSnapshot.getKey());
                        loading.setVisibility(View.INVISIBLE);
                        scrollView.setAlpha(1f);
                    }
                    giohangArray.add(cartsp);
                    mDatabase.child("id").child(cartsp.getIdsp()).child("product").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            User.Product product = dataSnapshot.getValue(User.Product.class);
                            products.add(0, product);
                            cartAdapter.notifyDataSetChanged();
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
                    Log.e("KEY", giohangArray.size() + "");
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Log.e("KEY", "a");
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    Log.e("KEY", "b");
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Log.e("KEY", "c");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e("KEY", "d");
                }
            });
        }
    }

    public void themcart(User.cartsp cartsp, int posion) {
        loading.setVisibility(View.VISIBLE);
        scrollView.setAlpha(0.2f);
        int i = (Integer.parseInt(cartsp.getSoluong()) + 1);
        Log.e("KT", i + "");
        for (String a : idspham) {
            Log.e("KT", a + "");
        }
        User.cartsp cartsp1 = new User.cartsp();
        cartsp1.setSoluong(String.valueOf(i));
        cartsp1.setIdsp(cartsp.getIdsp());
        mDatabase.child("id").child("User").child(id).child("cart").child(idspham.get(posion)).setValue(cartsp1)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.e("KT", "THÀNH CÔNG");
                        loading.setVisibility(View.INVISIBLE);
                        scrollView.setAlpha(1f);
                        cartAdapter.notifyDataSetChanged();
                    }
                });
    }


    public void trucart(User.cartsp cartsp, int posion) {

        int i = (Integer.parseInt(cartsp.getSoluong()));
        int ii = 0;
        if (i == 1) {
            Toast.makeText(getActivity(), "Sô lượng ít nhất là 1", Toast.LENGTH_SHORT).show();
        } else if (i > 1) {
            ii = (Integer.parseInt(cartsp.getSoluong()) - 1);
            loading.setVisibility(View.VISIBLE);
            scrollView.setAlpha(0.2f);
            Log.e("KT", i + "");
            for (String a : idspham) {
                Log.e("KT", a + "");
            }
            User.cartsp cartsp1 = new User.cartsp();
            cartsp1.setSoluong(String.valueOf(ii));
            cartsp1.setIdsp(cartsp.getIdsp());
            mDatabase.child("id").child("User").child(id).child("cart").child(idspham.get(posion)).setValue(cartsp1)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.e("KT", "THÀNH CÔNG");
                            loading.setVisibility(View.INVISIBLE);
                            scrollView.setAlpha(1f);
                            cartAdapter.notifyDataSetChanged();
                        }
                    });
        }
    }


    public void tongtien(String tongtien){
        cart.setVisibility(View.VISIBLE);
        pricetong.setText(tongtien);
    }




}
