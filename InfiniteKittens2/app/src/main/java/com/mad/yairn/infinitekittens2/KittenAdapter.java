package com.mad.yairn.infinitekittens2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class KittenAdapter extends RecyclerView.Adapter<KittenItemHolder> {

    private int size;

    public KittenAdapter(int s){
        size = s;
    }


    @Override
    public KittenItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View kittenView = layoutInflater.inflate(R.layout.kitten_item, parent, false);
        return new KittenItemHolder(kittenView);
    }

    @Override
    public void onBindViewHolder(@NonNull KittenItemHolder holder, int pos){
        //gotta write all this
    }


    @Override
    public int getItemCount() {
        return size;
    }
}
