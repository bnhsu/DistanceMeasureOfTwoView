package com.itri.canvas;


import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends Activity {

    private ImageView img;
    private ViewGroup rootLayout;
    private Dottedline dottedline;
    private TextView textView;
    private int _xDelta;
    private int _yDelta;

    int x1,y1, x2,y2;

    PointF point1 = new PointF();
    PointF point2 = new PointF();

    private Button RecordButton;


    private CirclePointer circlePointer, circlePointer2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout = (ViewGroup) findViewById(R.id.view_root);

        //img = (ImageView) rootLayout.findViewById(R.id.imageView);
        circlePointer =(CirclePointer)rootLayout.findViewById(R.id.circle_1);
        circlePointer2 =(CirclePointer)rootLayout.findViewById(R.id.circle_2);
        textView =(TextView)rootLayout.findViewById(R.id.text);
        dottedline = (Dottedline) rootLayout.findViewById(R.id.background_view);

        //RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(500, 150);
        //layoutParams.leftMargin = 100;
        //layoutParams.topMargin = 100;
        //textView.setLayoutParams(layoutParams);


        RelativeLayout.LayoutParams lParams1 = (RelativeLayout.LayoutParams) circlePointer.getLayoutParams();
        RelativeLayout.LayoutParams lParams2 = (RelativeLayout.LayoutParams) circlePointer2.getLayoutParams();
        point1.set(lParams1.leftMargin+convertDpToPixel(20, getApplicationContext()), lParams1.topMargin+convertDpToPixel(20, getApplicationContext()));
        point2.set(lParams2.leftMargin+convertDpToPixel(20, getApplicationContext()), lParams2.topMargin+convertDpToPixel(20, getApplicationContext()));
        SystemValue.pointF1.set(point1);
        SystemValue.pointF2.set(point2);
        textView.setText("Circle 1: " +point1.x+", "+point1.y +"\n"
                +"Circle 2: " +point2.x+", "+point2.y+"\n"
                +"Distance: "+getDistance(point1, point2));
        dottedline.setPoints(point1, point2);
        dottedline.invalidate();

        //img.setLayoutParams(layoutParams);
        //img.setOnTouchListener(new ChoiceTouchListener());
        circlePointer.setOnTouchListener(new ChoiceTouchListener());
        circlePointer2.setOnTouchListener(new ChoiceTouchListener());
        //circleCanvas.setLayoutParams(layoutParams);


        RecordButton = findViewById(R.id.button);
        RecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(rootLayout, "Start Record.", Snackbar.LENGTH_LONG).show();
            }
        });

    }



    private final class ChoiceTouchListener implements OnTouchListener {
        public boolean onTouch(View view, MotionEvent event) {
                final int X = (int) event.getRawX();
                final int Y = (int) event.getRawY();
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    _xDelta = X - lParams.leftMargin;
                    _yDelta = Y - lParams.topMargin;
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    layoutParams.leftMargin = X - _xDelta;
                    layoutParams.topMargin = Y - _yDelta;
                    view.setLayoutParams(layoutParams);

                    RelativeLayout.LayoutParams lParams1 = (RelativeLayout.LayoutParams) circlePointer.getLayoutParams();
                    RelativeLayout.LayoutParams lParams2 = (RelativeLayout.LayoutParams) circlePointer2.getLayoutParams();
                    point1.set(lParams1.leftMargin+convertDpToPixel(20, getApplicationContext()), lParams1.topMargin+convertDpToPixel(20, getApplicationContext()));
                    point2.set(lParams2.leftMargin+convertDpToPixel(20, getApplicationContext()), lParams2.topMargin+convertDpToPixel(20, getApplicationContext()));
                    int distance = (int)getDistance(point1, point2);
                    textView.setText("Circle 1: " +point1.x+", "+point1.y +"\n"
                            +"Circle 2: " +point2.x+", "+point2.y+"\n"
                            +"Distance: "+distance);
                    dottedline.setPoints(point1, point2);
                    dottedline.invalidate();

                    break;
            }
            rootLayout.invalidate();
            return true;
        }
    }

    public double getDistance(PointF pointF1, PointF pointF2){
        double distance = Math.sqrt(Math.pow((pointF2.x-pointF1.x),2) + Math.pow((pointF2.y-pointF1.y),2));
        return distance;
    }

    public static float convertDpToPixel(float dp, Context context){
        float px = dp * getDensity(context);
        return px;
    }

    public static float getDensity(Context context){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.density;
    }

}