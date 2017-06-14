package com.example.app_update_demo;

import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView img;
    private float xl;
    private float yl;
    private final int MODE_DRAG = 1;
    private final int MODE_ZOOM = 2;
    private final int MODE_NONE = 0;
    private int currentMode = MODE_NONE;
    private double distanceStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    Matrix matrix = new Matrix();
    Matrix newMatrix = new Matrix();
    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        currentMode = MODE_DRAG;
                        xl = event.getX();
                        yl = event.getY();
                        matrix.set(img.getImageMatrix());

                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        //计算俩个手指之间的距离
                        distanceStart = spacing(event);
                        if (distanceStart > 20) {
                            currentMode = MODE_ZOOM;
                            matrix.set(img.getImageMatrix()) ;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (currentMode == MODE_DRAG){
                            newMatrix.set(matrix);
                            float xn = event.getX();
                            float yn = event.getY();
                            newMatrix.postTranslate(xn - xl,yn - yl);

                        } else if (currentMode == MODE_ZOOM){
                            if (spacing(event)>20){
                                newMatrix.set(matrix);
                                double distance = spacing(event);
                                float scale = (float) (distance/distanceStart);
                                PointF pointF = getP(event);
                                newMatrix.postScale(scale,scale,pointF.x,pointF.y);
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                            currentMode = MODE_NONE;
                        break;
                    case MotionEvent.ACTION_POINTER_UP:

                        break;
                    case MotionEvent.ACTION_MASK:

                        break;

                    default:
                        break;
                }
                img.setImageMatrix(newMatrix);
                return true;
            }
        });
    }
    //计算俩点之间的距离
    private double spacing(MotionEvent event){

        float x = 0;
        float y = 0;

        x = event.getX(0) - event.getX(1);
        y = event.getY(0) - event.getY(1);

        return Math.sqrt(x * x - y * y);

    }

    private PointF getP(MotionEvent event){
        float x0 = event.getX(0);
        float x1 = event.getX(1);
        float y0 = event.getY(0);
        float y1 = event.getY(1);

        float x = (x0 + x1) / 2;
        float y = (y0 + y1) / 2;

        PointF pointF = new PointF(x,y);
        return pointF;
    }
}
