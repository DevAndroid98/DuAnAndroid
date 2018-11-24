package com.tinhduchung.dev.poly.duanandroid.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tinhduchung.dev.poly.duanandroid.R;
import com.tinhduchung.dev.poly.duanandroid.adapter.CartAdapter;
import com.tinhduchung.dev.poly.duanandroid.user.User;

import java.util.ArrayList;

public class Fragment_Cart extends BaseFragment {

    private RecyclerView recyclerviewcart;
    private DatabaseReference mDatabase;
    private ArrayList<User.cartsp> giohangArray = new ArrayList<>();
    private ArrayList<User.Product> products = new ArrayList<>();
    private String id="";
    private LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
    private CartAdapter cartAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.fragment_cart,container,false);
         mDatabase = FirebaseDatabase.getInstance().getReference();
        Intent intent=getActivity().getIntent();
        id=intent.getStringExtra("id");
        recyclerviewcart =view.findViewById(R.id.recyclerviewcart);
        cartAdapter=new CartAdapter(Fragment_Cart.this,products,giohangArray);
        recyclerviewcart.setLayoutManager(linearLayoutManager);
        recyclerviewcart.setHasFixedSize(true);
        recyclerviewcart.setAdapter(cartAdapter);
        getcart();


        return view;
    }

    private void getcart(){
        if (id!=null){
            giohangArray.clear();
            products.clear();
            mDatabase.child("id").child("User").child(id).child("cart").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    User.cartsp cartsp=dataSnapshot.getValue(User.cartsp.class);
                    giohangArray.add(cartsp);
                    mDatabase.child("id").child(cartsp.getIdsp()).child("product").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            User.Product product=dataSnapshot.getValue(User.Product.class);
                            products.add(0,product);
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
                    Log.e("KEY",giohangArray.size()+"");
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Log.e("KEY","a");
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    Log.e("KEY","b");
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Log.e("KEY","c");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e("KEY","d");
                }
            }) ;
        }
    }








}
