package com.itri.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomViewGroup extends View {

    Context context;
    private CustomViewGroup rootLayout;
    CirclePointer circlePointer_1, circlePointer_2;

    public CustomViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initial();
    }

    private void initial(){
        //rootLayout = (RelativeLayout) findViewById(R.id.GroupView_root);
        circlePointer_1 = (CirclePointer) findViewById(R.id.circle_1);
        circlePointer_2 = (CirclePointer) findViewById(R.id.circle_2);
    }

    @Override
    protected void onDraw(Canvas canvas){

    }
/*
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = this.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = this.getChildAt(i);
            this.measureChild(child, widthMeasureSpec, heightMeasureSpec);
            int cw = child.getMeasuredWidth();
            // int ch = child.getMeasuredHeight();
        }
    }
*/
    private int getChildCount() {
        return 0;
    }
}
