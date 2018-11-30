package com.tinhduchung.dev.poly.duanandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tinhduchung.dev.poly.duanandroid.ProductManagementActivity;
import com.tinhduchung.dev.poly.duanandroid.R;
import com.tinhduchung.dev.poly.duanandroid.fragment.Fragment_Cart;
import com.tinhduchung.dev.poly.duanandroid.user.User;

import java.util.ArrayList;

public class ProductuserAdapter extends RecyclerView.Adapter<ProductuserAdapter.CartHolder> {
    private ProductManagementActivity context;
    private ArrayList<User.Product> products;


    public ProductuserAdapter(ProductManagementActivity context, ArrayList<User.Product> products) {
        this.context = context;
        this.products = products;
        ;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_manage_product, parent, false);
        return new CartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartHolder holder, final int position) {
        final User.Product product = products.get(position);
        holder.txtColor.setText("Màu:" + product.getColorproduct());
        holder.txtgia.setText("Giá:" + Double.parseDouble(product.getPriceproduct()) + "đ");
        holder.txtnameproduct.setText("Tên sản phẩm:" + product.getNameproduct());
        holder.txtsoluong.setText("Số lượng:" + product.getSoluong());

        Picasso.get().load(product.getUri()).into(holder.imgnameproduct);

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               context.clickprodut();
           }
       });
        holder.btndeletecart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.deletesp(product, products.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class CartHolder extends RecyclerView.ViewHolder {


        public ImageView btndeletecart;
        public ImageView imgnameproduct;
        public TextView txtnameproduct;
        public TextView txtColor;
        public TextView txtsoluong;
        public TextView txtgia;


        public CartHolder(View itemView) {
            super(itemView);
            imgnameproduct = itemView.findViewById(R.id.imgnameproduct);
            txtColor = itemView.findViewById(R.id.txtColor);
            txtnameproduct = itemView.findViewById(R.id.txtnameproduct);
            txtgia = itemView.findViewById(R.id.txtgia);
            txtsoluong = itemView.findViewById(R.id.txtsoluong);
            btndeletecart = itemView.findViewById(R.id.btndeletecprodut);


        }
    }


}
