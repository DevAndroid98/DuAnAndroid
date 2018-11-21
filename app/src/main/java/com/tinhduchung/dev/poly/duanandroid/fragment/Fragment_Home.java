package com.tinhduchung.dev.poly.duanandroid.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.tinhduchung.dev.poly.duanandroid.adapter.ProductAdapter;
import com.tinhduchung.dev.poly.duanandroid.user.User;

import java.util.ArrayList;

public class Fragment_Home extends BaseFragment {
    private RecyclerView recyclerviewProductBoy;
    private ProductAdapter productAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<String> strings = new ArrayList<>();

    private ArrayList<User.Product> products = new ArrayList<>();

    private ArrayList<User.Uriimg> uriimgs = new ArrayList<>();
    private ArrayList<String> uri = new ArrayList<>();

    private DatabaseReference mDatabase;
    private StorageReference storageRef;
    private FirebaseStorage storage;
    private ArrayList<String> path = new ArrayList<>();
    private CardView cvForMan;
    private TextView btnMoreman;

    private  int i=0;






    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        mapped();
        getiduser();

        productAdapter=new ProductAdapter(products,getActivity());
        linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerviewProductBoy.setLayoutManager(linearLayoutManager);
        recyclerviewProductBoy.setAdapter(productAdapter);
        return view;
    }

    private void mapped() {
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://onlinestore-3ac1a.appspot.com");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        cvForMan = view.findViewById(R.id.cvForMan);
        btnMoreman =view.findViewById(R.id.btnMoreman);
        recyclerviewProductBoy = view.findViewById(R.id.recyclerviewProductBoy);


    }



    private void getiduser(){

        path.clear();
        mDatabase.child("id").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                if (dataSnapshot.getKey() !=null && dataSnapshot.getKey().startsWith("sp:")){
                    path.add(dataSnapshot.getKey());
                    i++;
                    }
                    Log.e("I",i+"");
                    strings.add(dataSnapshot.getKey());
                     if (i==strings.size()-1){
                         getproductboy();
                     }
                     Log.e("SIZE",path.size()+"");

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
         for (int i=0;i<path.size();i++){
            mDatabase.child("id").child(path.get(i)).child("product").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    final User.Product product; product =dataSnapshot.getValue(User.Product.class);
                    products.add(0,product);
                    productAdapter.notifyDataSetChanged();
                    Log.e("SIZE",products.size()+"");

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




    }


}
