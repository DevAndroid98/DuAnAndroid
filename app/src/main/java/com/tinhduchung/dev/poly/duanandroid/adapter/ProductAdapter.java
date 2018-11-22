package com.tinhduchung.dev.poly.duanandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.tinhduchung.dev.poly.duanandroid.R;
import com.tinhduchung.dev.poly.duanandroid.fragment.Fragment_Home;
import com.tinhduchung.dev.poly.duanandroid.hodel.ProductHolder;
import com.tinhduchung.dev.poly.duanandroid.user.User;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductHolder> {

    private ArrayList<User.Product> products;
    private Fragment_Home context;

    public ProductAdapter(ArrayList<User.Product> products, Fragment_Home context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_productt,parent,false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
       User.Product product=products.get(position);
       holder.tvNameproduct.setText(product.getNameproduct());
       holder.tvPrice.setText("Giá:"+product.getPriceproduct()+"\tVND");
       Picasso.get().load(product.getUri()).resize(300,300).centerCrop(Gravity.TOP).into(holder.imgProduct);
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               context.clickproduct();
           }
       });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
