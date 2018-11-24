package com.tinhduchung.dev.poly.duanandroid.hodel;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.tinhduchung.dev.poly.duanandroid.R;

public class ImageViewHodel extends RecyclerView.ViewHolder {
    public ImageView img;




    public ImageViewHodel(View itemView) {
        super(itemView);
        img=itemView.findViewById(R.id.my_image_view);

    }
}
