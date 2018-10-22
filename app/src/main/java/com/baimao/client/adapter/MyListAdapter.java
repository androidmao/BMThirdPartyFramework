package com.baimao.client.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baimao.client.R;
import com.baimao.client.adapter.holder.MyListHolder;
import com.baimao.client.bean.UserInfo;

import java.util.List;

public class MyListAdapter extends BaseAdapter<UserInfo, MyListHolder> {

    public MyListAdapter(@NonNull Context context, @NonNull List<UserInfo> data) {
        super(context, data);
    }

    @Override
    public MyListHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new MyListHolder(LayoutInflater.from(context).inflate(R.layout.item_userinfo, parent, false));
    }

    @Override
    public void onBindBaseViewHolder(MyListHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("这里是点击每一行item的响应事件", "" + position);
            }
        });

    }
}
