package com.tinhduchung.dev.poly.duanandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.tinhduchung.dev.poly.duanandroid.AddProductActivity;
import com.tinhduchung.dev.poly.duanandroid.R;
import com.tinhduchung.dev.poly.duanandroid.model.ColorModel;

import java.util.ArrayList;
import java.util.List;

public class ColorAdapter extends BaseAdapter {

    private AddProductActivity context;
    private List<ColorModel> models;

    public ColorAdapter(AddProductActivity context, List<ColorModel> models) {
        this.context = context;
        this.models = models;
    }


    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int position) {
        return models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v;
        if (convertView==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.item_color_cardview,parent,false);

        }else {
            v=convertView;
        }

        TextView textView=v.findViewById(R.id.txtcolor);
        textView.setText(models.get(position).getColor());
        final CheckBox checkBox=v.findViewById(R.id.check);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    context.addcolor(position);
                }else {
                    context.deletecolor(models.get(position).getColor());
                }
            }
        });

        return v;
    }
}
