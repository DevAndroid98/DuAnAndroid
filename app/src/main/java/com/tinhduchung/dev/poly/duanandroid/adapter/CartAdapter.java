package com.tinhduchung.dev.poly.duanandroid.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tinhduchung.dev.poly.duanandroid.R;
import com.tinhduchung.dev.poly.duanandroid.fragment.Fragment_Cart;

import com.tinhduchung.dev.poly.duanandroid.user.User;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {
    private Fragment_Cart context;
    private ArrayList<User.Product> products;
    private ArrayList<User.cartsp> cartsps;

    public CartAdapter(Fragment_Cart context, ArrayList<User.Product> products, ArrayList<User.cartsp> cartsps) {
        this.context = context;
        this.products = products;
        this.cartsps = cartsps;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_cart,parent,false);
       return new CartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
         User.Product product=products.get(position);
         holder.txtColor.setText(product.getColorproduct());
         holder.txtgia.setText(product.getPriceproduct());
         holder.txtnameproduct.setText(product.getNameproduct());
         holder.txtnameshop.setText(product.getNameshop());
         holder.txtsoluong.setText(cartsps.get(position).getSoluong());
         Picasso.get().load(product.getUri()).into(holder.imgnameproduct);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class CartHolder extends RecyclerView.ViewHolder {
        public CheckBox cbok;
        public TextView txtnameshop;
        public ImageView btndeletecart;
        public ImageView imgnameproduct;
        public TextView txtnameproduct;
        public TextView txtColor;
        public ImageView trusl;
        public TextView txtsoluong;
        public ImageView themsl;
        public TextView txtgia;



        public CartHolder(View itemView) {
            super(itemView);
            cbok = itemView.findViewById(R.id.cbok);
            txtnameshop =  itemView.findViewById(R.id.txtnameshop);
            btndeletecart =  itemView.findViewById(R.id.btndeletecart);
            imgnameproduct =  itemView.findViewById(R.id.imgnameproduct);
            txtnameproduct =  itemView.findViewById(R.id.txtnameproduct);
            txtColor =  itemView.findViewById(R.id.txtColor);
            trusl =  itemView.findViewById(R.id.trusl);
            txtsoluong = itemView.findViewById(R.id.txtsoluong);
            themsl =  itemView.findViewById(R.id.themsl);
            txtgia = itemView.findViewById(R.id.txtgia);
        }
    }
}
