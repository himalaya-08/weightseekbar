package com.example.weightseekbar.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weightseekbar.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView tvNum;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        tvNum = itemView.findViewById(R.id.tv_num);
    }
}
