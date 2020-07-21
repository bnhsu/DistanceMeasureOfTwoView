package com.itri.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Dottedline extends View {

    Paint paint;

    private int _xDelta;
    private int _yDelta;

    //CircleCanvas circleCanvas,circleCanvas2;

    PointF point1 = new PointF();
    PointF point2 = new PointF();

    Context context;

    public Dottedline(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        paint.setAntiAlias(true);
        paint.setPathEffect(new DashPathEffect(new float[]{8, 8}, 0));
        setPoints(SystemValue.pointF1, SystemValue.pointF2);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        //setPoints(SystemValue.pointF1, SystemValue.pointF2);
        canvas.drawLine(point1.x, point1.y, point2.x, point2.y, paint);
    }


    public void setPoints(PointF pointf1, PointF pointf2){
        point1.set(pointf1);
        point2.set(pointf2);
    }

    @Override
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
