package com.example.weightseekbar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weightseekbar.adapter.MyRecyclerViewAdapter;
import com.example.weightseekbar.circlerecyclerview.CircleRecyclerView;
import com.example.weightseekbar.circlerecyclerview.CircularHorizontalMode;
import com.example.weightseekbar.circlerecyclerview.ItemViewMode;

public class MainActivity extends AppCompatActivity {

    int displayItemCount = 5;
    RecyclerView rvNumList;
    int scrollX = 0;
    private View centerView;
    private CircleRecyclerView mCircleRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvNumList = findViewById(R.id.rv_num_list);

        ConstraintLayout root = findViewById(R.id.root);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        View view = LayoutInflater.from(this).inflate(R.layout.layout_list_item, root, false);

        int viewWidth = getViewMeasuredWidth(view);


        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(viewWidth * displayItemCount, ViewGroup.LayoutParams.WRAP_CONTENT);

        rvNumList.setLayoutParams(layoutParams);

        SliderLayoutManager sliderLayoutManager = new SliderLayoutManager(this, LinearLayoutManager.HORIZONTAL, false, rvNumList);

        new LinearSnapHelper().attachToRecyclerView(rvNumList);

        rvNumList.setLayoutManager(linearLayoutManager);
        //rvNumList.setLayoutManager(new CircularLayoutManager(this, 200, -200));
        rvNumList.setAdapter(new MyRecyclerViewAdapter());

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(root);
        constraintSet.connect(R.id.rv_num_list, ConstraintSet.RIGHT, R.id.root, ConstraintSet.RIGHT, 0);
        constraintSet.connect(R.id.rv_num_list, ConstraintSet.LEFT, R.id.root, ConstraintSet.LEFT, 0);
        constraintSet.connect(R.id.rv_num_list, ConstraintSet.TOP, R.id.root, ConstraintSet.TOP, 0);
        constraintSet.connect(R.id.rv_num_list, ConstraintSet.BOTTOM, R.id.root, ConstraintSet.BOTTOM, 0);
        constraintSet.applyTo(root);

        rvNumList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//
//                    int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
//
//                    if (linearLayoutManager != null) {
//                        // views on the screen
//                        int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
//                        View lastView = linearLayoutManager.findViewByPosition(lastVisibleItemPosition);
//                        //int firstVisibleItemPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
//                        int firstVisibleItemPosition = lastVisibleItemPosition - 4;
//                        View firstView = linearLayoutManager.findViewByPosition(firstVisibleItemPosition);
//
//                        if (firstView != null && lastView != null) {
//                            // distance we need to scroll
//                            int leftMargin = (screenWidth - lastView.getWidth()) / 2;
//                            int rightMargin = (screenWidth - firstView.getWidth()) / 2 + firstView.getWidth();
//                            int leftEdge = lastView.getLeft();
//                            int rightEdge = firstView.getRight();
//                            int scrollDistanceLeft = leftEdge - leftMargin;
//                            int scrollDistanceRight = rightMargin - rightEdge;
//
//                            if (leftEdge > screenWidth / 2) {
//                                recyclerView.smoothScrollBy(-scrollDistanceRight, 0);
//                            } else if (rightEdge < screenWidth / 2) {
//                                recyclerView.smoothScrollBy(scrollDistanceLeft, 0);
//                            }
//                        }
//                    }

                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //Log.i("RecyclerViewTAG", String.valueOf(dx));
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (linearLayoutManager != null) {
                    int firstItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                    int lastItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();

                    int centerItem = (firstItem + lastItem - 4) / 2;

                    View view = linearLayoutManager.findViewByPosition(centerItem);
                    View view1 = linearLayoutManager.findViewByPosition(centerItem - 1);
                    View view2 = linearLayoutManager.findViewByPosition(centerItem - 2);
                    View view3 = linearLayoutManager.findViewByPosition(centerItem + 1);
                    View view4 = linearLayoutManager.findViewByPosition(centerItem + 2);

                    view.setTranslationY(100);
                }
            }
        });

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayoutManager linearLayoutManager1 = (LinearLayoutManager) rvNumList.getLayoutManager();
                if (linearLayoutManager1 != null) {
                    int firstItem = linearLayoutManager1.findFirstCompletelyVisibleItemPosition();
                    int lastItem = linearLayoutManager1.findLastCompletelyVisibleItemPosition();

                    Log.i("BTN TAG", "First Item: " + firstItem + " Last Item: " + lastItem);

                    int selectedItem = (firstItem + lastItem - 4) / 2;

                    Toast.makeText(MainActivity.this, selectedItem + "", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mCircleRecyclerView = findViewById(R.id.circle_rv);

        ItemViewMode mItemViewMode = new CircularHorizontalMode();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        mCircleRecyclerView.setLayoutManager(mLayoutManager);
        mCircleRecyclerView.setViewMode(mItemViewMode);
        mCircleRecyclerView.setNeedCenterForce(true);
        mCircleRecyclerView.setNeedLoop(false);

        new LinearSnapHelper().attachToRecyclerView(mCircleRecyclerView);

        mCircleRecyclerView.setAdapter(new MyRecyclerViewAdapter());
        mCircleRecyclerView.smoothScrollToPosition(4);

        mCircleRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (centerView != null) {
                    TextView textView = centerView.findViewById(R.id.tv_num);
                    View longLine = centerView.findViewById(R.id.long_line);
                    View highlightedCircle = centerView.findViewById(R.id.highlighted_circle);

                    highlightedCircle.setVisibility(View.GONE);

                    textView.setTextColor(getResources().getColor(R.color.gray));
                    longLine.setBackgroundColor(getResources().getColor(R.color.gray));
                }

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) mCircleRecyclerView.getLayoutManager();

                    if (layoutManager != null) {
                        centerView = mCircleRecyclerView.findViewAtCenter();

                        View longLine = centerView.findViewById(R.id.long_line);
                        TextView textView = centerView.findViewById(R.id.tv_num);

                        View highlightedCircle = centerView.findViewById(R.id.highlighted_circle);

                        highlightedCircle.setVisibility(View.VISIBLE);

                        textView.setTextColor(getResources().getColor(R.color.blue));
                        longLine.setBackgroundColor(getResources().getColor(R.color.blue));

                        Toast.makeText(mCircleRecyclerView.getContext(), textView.getText(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private int getViewMeasuredWidth(View view) {
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
        view.measure(width, expandSpec);
        return view.getMeasuredWidth();
    }
}
