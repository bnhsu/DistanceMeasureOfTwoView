package com.itri.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CircleCanvas extends View {

    Canvas canvas;
    Paint paint;
    private float x, y;
    private boolean touching, drawingTouch;
    int drawingpic_x = 0, drawingpic_y = 0;
    int drawingPicOffset_x = 0, drawingPicOffset_y = 0;
    int drawingPicWidth =20;
    int drawingPicHeight =20 ;
    private Path drawPath;

    private Bitmap canvasBitmap;

    public CircleCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);
        paint.setAntiAlias(true);
        canvas.drawCircle(convertDpToPixel(20, getContext()), convertDpToPixel(20,getContext()), 10, paint);

    }

    public static float convertDpToPixel(float dp, Context context){
        float px = dp * getDensity(context);
        return px;
    }

    public static float getDensity(Context context){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.density;
    }
/*
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width, height;
        if(widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
        }else
    }

*/
/*

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                touching = true;
                if ((x > drawingpic_x) && (x < drawingpic_x + drawingPicWidth)
                        && (y > drawingpic_y) && (y < drawingpic_y + drawingPicHeight)) {
                    drawingPicOffset_x = (int) x - drawingpic_x;
                    drawingPicOffset_y = (int) y - drawingpic_y;
                    drawingTouch = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                x = event.getX();
                y = event.getY();
                touching = true;
                if (drawingTouch) {
                    drawingpic_x = (int) x-drawingPicOffset_x;
                    drawingpic_y = (int) y-drawingPicOffset_y;
                }
                break;
            case MotionEvent.ACTION_UP:
                    //drawingpic_x=0;
                    //drawingpic_y=0;
                break;
            default:
                drawingTouch = false;
                touching = false;
        }
        invalidate();
        return true;
    }

*/
}
