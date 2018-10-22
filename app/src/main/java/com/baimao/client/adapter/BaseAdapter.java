package com.baimao.client.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseAdapter<RVD, RVH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public Context context;

    public List<RVD> data;

    public BaseAdapter(@NonNull Context context, @NonNull List<RVD> data) {
        this.context = context;
        this.data = data;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return onCreateBaseViewHolder(viewGroup, i);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        onBindBaseViewHolder((RVH) viewHolder, i);
    }

    public abstract RVH onCreateBaseViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindBaseViewHolder(RVH holder, int position);

}
