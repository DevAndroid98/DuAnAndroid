package com.tinhduchung.dev.poly.duanandroid.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tinhduchung.dev.poly.duanandroid.R;
import com.tinhduchung.dev.poly.duanandroid.fragment.Fragment_Notification;

import java.util.List;

public class NotifiAdapter extends RecyclerView.Adapter<NotifiAdapter.NotifiHolder> {
    private Fragment_Notification context;
    private List<String> strings;

    public NotifiAdapter(Fragment_Notification context, List<String> strings){
        this.context = context;
        this.strings = strings;
    }
    @NonNull
    @Override
    public NotifiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_promotion, parent, false);
        return new NotifiHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotifiHolder holder, final int position) {
        holder.tvNotifi.setText(strings.get(position));

        holder.cvNotifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.dialogNotifi(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public class NotifiHolder extends RecyclerView.ViewHolder {
        TextView tvNotifi;
        CardView cvNotifi;
        public NotifiHolder(View itemView) {
            super(itemView);

            tvNotifi = itemView.findViewById(R.id.tvHihi);
            cvNotifi = itemView.findViewById(R.id.cvPromotion);
        }
    }
}
