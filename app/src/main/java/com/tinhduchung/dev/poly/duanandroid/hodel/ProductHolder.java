package com.tinhduchung.dev.poly.duanandroid.hodel;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tinhduchung.dev.poly.duanandroid.R;

public class ProductHolder extends RecyclerView.ViewHolder {
    public ImageView imgProduct;
    public TextView tvNameproduct;
    public TextView tvPrice;



    public ProductHolder(View itemView) {
        super(itemView);
        imgProduct =  itemView.findViewById(R.id.btn_product);
        tvNameproduct =  itemView.findViewById(R.id.tvNameproduct);
        tvPrice =  itemView.findViewById(R.id.tvPrice);
    }
}
