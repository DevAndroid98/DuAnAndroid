package com.tinhduchung.dev.poly.duanandroid.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tinhduchung.dev.poly.duanandroid.AddProductActivity;
import com.tinhduchung.dev.poly.duanandroid.R;
import com.tinhduchung.dev.poly.duanandroid.fragment.Fragment_Home;
import com.tinhduchung.dev.poly.duanandroid.model.ColorModel;
import com.tinhduchung.dev.poly.duanandroid.user.User;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends BaseAdapter {

    private Fragment_Home context;
    private ArrayList<User.Product> products;

    public GridAdapter(Fragment_Home context, ArrayList<User.Product> products) {
        this.context = context;
        this.products = products;
    }


    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v;
        if (convertView==null){
            LayoutInflater inflater=LayoutInflater.from(parent.getContext());
            v=inflater.inflate(R.layout.item_product,parent,false);

        }else {
            v=convertView;
        }

         ImageView imgProduct;
         TextView tvNameproduct;
         TextView tvPrice;
        imgProduct =  v.findViewById(R.id.btn_product);
        tvNameproduct =  v.findViewById(R.id.tvNameproduct);
        tvPrice =  v.findViewById(R.id.tvPrice);
        User.Product product=products.get(position);
        tvNameproduct.setText(product.getNameproduct());
        tvPrice.setText("Giá:"+product.getPriceproduct()+"\tVND");
        Picasso.get().load(product.getUri()).resize(300,300).centerCrop(Gravity.TOP).into(imgProduct);
        return v;
    }
}
