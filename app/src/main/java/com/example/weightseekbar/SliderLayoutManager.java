package com.example.weightseekbar;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SliderLayoutManager extends LinearLayoutManager {

    private RecyclerView recyclerView;

    public SliderLayoutManager(Context context) {
        super(context);
    }

    public SliderLayoutManager(Context context, int orientation, boolean reverseLayout, RecyclerView recyclerView) {
        super(context, orientation, reverseLayout);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            int recyclerViewCenterX = getRecyclerViewCenterX();
            int minDistance = recyclerView.getWidth();
            int position = -1;

            for (int i = 0; i < recyclerView.getChildCount(); i++) {
                View child = recyclerView.getChildAt(i);
                int childCenterX = getDecoratedLeft(child) + (getDecoratedRight(child) - getDecoratedLeft(child)) / 2;
                int childDistanceFromCenter = Math.abs(childCenterX - recyclerViewCenterX);
                if (childDistanceFromCenter < minDistance) {
                    minDistance = childDistanceFromCenter;
                    position = recyclerView.getChildLayoutPosition(child);
                }
            }
        }
    }

    private int getRecyclerViewCenterX() {
        return (recyclerView.getRight() - recyclerView.getLeft())/2 + recyclerView.getLeft();
    }
}
