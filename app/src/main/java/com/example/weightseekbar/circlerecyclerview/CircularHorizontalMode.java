package com.example.weightseekbar.circlerecyclerview;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class CircularHorizontalMode implements ItemViewMode {



    public CircularHorizontalMode() {
    }

    @Override
    public void applyToView(View v, RecyclerView parent) {
        int mCircleOffset = 600;
        float mDegToRad = 1.0f / 180.0f * (float) Math.PI;
        float mTranslationRatio = 0.15f;

        float halfWidth = v.getWidth() * 0.5f;
        float parentHalfWidth = parent.getWidth() * 0.5f;
        float x = v.getX();
        float rot = parentHalfWidth - halfWidth - x;

        v.setRotation(-rot * 0.05f);
        v.setTranslationY((float) (-Math.cos(rot * mTranslationRatio * mDegToRad) + 1) * mCircleOffset);
    }
}
