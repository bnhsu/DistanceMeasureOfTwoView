package com.itri.canvas;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private ImageView img;
    private ViewGroup rootLayout;
    private TextView textView;
    private int _xDelta;
    private int _yDelta;

    int x1,y1, x2,y2;



    private CircleCanvas circleCanvas,circleCanvas2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootLayout = (ViewGroup) findViewById(R.id.view_root);
        //img = (ImageView) rootLayout.findViewById(R.id.imageView);
        circleCanvas =(CircleCanvas)rootLayout.findViewById(R.id.circle_1);
        circleCanvas2 =(CircleCanvas)rootLayout.findViewById(R.id.circle_2);
        textView =(TextView)rootLayout.findViewById(R.id.text);

        //RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(500, 150);
        //layoutParams.leftMargin = 100;
        //layoutParams.topMargin = 100;
        //textView.setLayoutParams(layoutParams);

        RelativeLayout.LayoutParams lParams1 = (RelativeLayout.LayoutParams) circleCanvas.getLayoutParams();
        RelativeLayout.LayoutParams lParams2 = (RelativeLayout.LayoutParams) circleCanvas2.getLayoutParams();
        x1 = lParams1.leftMargin;
        y1 = lParams1.topMargin;
        x2 = lParams2.leftMargin;
        y2 = lParams2.topMargin;
        textView.setText("Circle 1: " +x1+", "+y1 +"\n"
                +"Circle 2: " +x2+", "+y2 +"\n"
                +"Distance: "+getDistance(x1,y1,x2,y2));



        //img.setLayoutParams(layoutParams);
        //img.setOnTouchListener(new ChoiceTouchListener());
        circleCanvas.setOnTouchListener(new ChoiceTouchListener());
        circleCanvas2.setOnTouchListener(new ChoiceTouchListener());
        //circleCanvas.setLayoutParams(layoutParams);
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

                    RelativeLayout.LayoutParams lParams1 = (RelativeLayout.LayoutParams) circleCanvas.getLayoutParams();
                    RelativeLayout.LayoutParams lParams2 = (RelativeLayout.LayoutParams) circleCanvas2.getLayoutParams();
                    x1 = lParams1.leftMargin;
                    y1 = lParams1.topMargin;
                    x2 = lParams2.leftMargin;
                    y2 = lParams2.topMargin;
                    textView.setText("Circle 1: " +x1+", "+y1 +"\n"
                            +"Circle 2: " +x2+", "+y2 +"\n"
                            +"Distance: "+getDistance(x1,y1,x2,y2));

                    break;
            }
            rootLayout.invalidate();
            return true;
        }
    }

    public double getDistance(int x1, int y1, int x2, int y2){
        double distance = Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2));
        return distance;
    }
}