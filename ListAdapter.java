package com.example.presentation;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<com.example.presentation.ListViewHolder> {

    private ArrayList<ListItem> data;

    public ListAdapter(ArrayList<ListItem> data){
        this.data = data;
    }

    @NonNull
    @Override
    public com.example.presentation.ListViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);


        final com.example.presentation.ListViewHolder holder = new com.example.presentation.ListViewHolder(v);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = parent.getContext();
                int position = holder.getAdapterPosition();
                Intent i = new Intent(context, InputActivity.class);
                i.putExtra(context.getString(R.string.request_code),ConfirmationActivity.EDIT_REQ_CODE);
                i.putExtra(context.getString(R.string.alarm_id),data.get(position).getAlarmID());
                ((Activity) context).startActivityForResult(i,ConfirmationActivity.EDIT_REQ_CODE);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.presentation.ListViewHolder holder, int position) {
        holder.alarmName.setText(this.data.get(position).getAlarmName());
        holder.time.setText(this.data.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }
}
