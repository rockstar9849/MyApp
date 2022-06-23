package com.example.presentation;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ListViewHolder extends RecyclerView.ViewHolder {
    TextView alarmName;
    TextView time;

    ListViewHolder(View itemView){
        super(itemView);
        this.alarmName = itemView.findViewById(R.id.alarmName);
        this.time = itemView.findViewById(R.id.time);
    }
}

