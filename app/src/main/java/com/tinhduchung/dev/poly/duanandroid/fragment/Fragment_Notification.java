package com.tinhduchung.dev.poly.duanandroid.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tinhduchung.dev.poly.duanandroid.R;
import com.tinhduchung.dev.poly.duanandroid.adapter.NotifiAdapter;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Notification extends BaseFragment {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private List<String> strings = new ArrayList<>();
    private NotifiAdapter notifiAdapter = new NotifiAdapter(Fragment_Notification.this, strings);
    private RecyclerView rvNotifi;
    private LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notification, container, false);
        rvNotifi = view.findViewById(R.id.rvNotifi);
        rvNotifi.setAdapter(notifiAdapter);
        rvNotifi.setLayoutManager(llm);
        strings.clear();
        mDatabase.child("notifi").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Object o = dataSnapshot.getValue();
                strings.add(o.toString());
                notifiAdapter.notifyDataSetChanged();
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
        return view;
    }
    public void dialogNotifi(int position){
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_notifi);
        TextView tvNoidung = dialog.findViewById(R.id.tvNoidung);
        tvNoidung.setText(strings.get(position));
        dialog.show();
    }
}
