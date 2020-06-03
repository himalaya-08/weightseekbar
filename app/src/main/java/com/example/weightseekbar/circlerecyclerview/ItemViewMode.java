package com.example.weightseekbar.circlerecyclerview;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public interface ItemViewMode {
    void applyToView(View v, RecyclerView parent);
}
