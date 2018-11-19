package com.tinhduchung.dev.poly.duanandroid.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tinhduchung.dev.poly.duanandroid.R;
import com.tinhduchung.dev.poly.duanandroid.hodel.ImageViewHodel;
import com.tinhduchung.dev.poly.duanandroid.model.ImageModel;

import java.util.ArrayList;


public class ImageAdapter  extends RecyclerView.Adapter<ImageViewHodel> {

     private Context context;
     private ArrayList<ImageModel> models;

    public ImageAdapter(Context context, ArrayList<ImageModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ImageViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image,parent,false);
        return new ImageViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHodel holder, int position) {

        holder.img.setImageURI(Uri.parse(models.get(position).uri));

    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
