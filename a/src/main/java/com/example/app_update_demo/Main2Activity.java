package com.example.app_update_demo;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {

    ImageView imageView;

     PointF startPoint = new PointF();
     Matrix matrix = new Matrix();
     Matrix currentMaritx = new Matrix();
     boolean firstTimeZoom = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView = (ImageView) findViewById(R.id.imageview);
        imageView.setOnTouchListener(new TounchListener());
    }

    private class TounchListener implements View.OnTouchListener {

        private int mode = 0;// 用于标记模式
        private static final int DRAG = 1;// 拖动
        private static final int ZOOM = 2;// 放大
        private float startDis = 0;

        // private PointF midPoint;// 中心点

        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    mode = DRAG;
                    imageView.setScaleType(ImageView.ScaleType.MATRIX);//设置ImageView的缩放模式为矩阵缩放
                    currentMaritx.set(imageView.getImageMatrix());// 记录ImageView当期的移动位置
                    if(firstTimeZoom){
                        currentMaritx.postScale(1, 1);
                        firstTimeZoom = false;
                    }
                    startPoint.set(event.getX(), event.getY());// 开始点
                    break;
                case MotionEvent.ACTION_MOVE:// 移动事件
                    if (mode == DRAG) {// 图片拖动事件
                        float dx = event.getX() - startPoint.x;// x轴移动距离
                        float dy = event.getY() - startPoint.y;
                        matrix.set(currentMaritx);// 在当前的位置基础上移动
                        matrix.postTranslate(dx, dy);

                    } else if (mode == ZOOM) {// 图片放大事件
                        float endDis = distance(event);// 结束距离
                        if (endDis > 10f) {
                            float scale = endDis / startDis;// 放大倍数
                            matrix.set(currentMaritx);
                            float currentX = imageView.getWidth() / 2;
                            float currentY = imageView.getHeight() / 2;
                            matrix.postScale(scale, scale, currentX, currentY);
                        }

                    }
                    break;

                case MotionEvent.ACTION_UP:
                    mode = 0;
                    break;
                // 有手指离开屏幕，但屏幕还有触点(手指)
                case MotionEvent.ACTION_POINTER_UP:
                    mode = 0;
                    break;
                // 当屏幕上已经有触点（手指）,再有一个手指压下屏幕
                case MotionEvent.ACTION_POINTER_DOWN:
                    mode = ZOOM;
                    startDis = distance(event);

                    if (startDis > 10f) {// 避免手指上有两个茧
                        // midPoint = mid(event);
                        currentMaritx.set(imageView.getImageMatrix());// 记录当前的缩放倍数
                    }

                    break;

            }
            // Log.i("x", "");
            imageView.setImageMatrix(matrix);
            return true;
        }

    }

    /**
     * 两点之间的距离
     *
     * @param event
     * @return
     */
    private static float distance(MotionEvent event) {
        // 两根线的距离
        float dx = event.getX(1) - event.getX(0);
        float dy = event.getY(1) - event.getY(0);
        return (float) Math.sqrt(dx * dx + dy * dy);
    }




    }
