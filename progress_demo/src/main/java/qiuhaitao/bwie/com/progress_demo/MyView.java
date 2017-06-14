package qiuhaitao.bwie.com.progress_demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import static android.R.attr.radius;



public class MyView extends View{
    private SingleTapThread singleTapThread;
    private Paint paint,paint1,paint2;
    private int windowWidth;
    private int windowHeight;
    private int ii;
    private GestureDetector detector;
    /**
     * 最后滑动的X坐标
     */
    private int lastX;
    /**
     * 最后滑动的Y坐标
     */
    private int lastY;

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //测量规格大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width=widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width=Math.min(widthSize,radius*2);
        } else {
            width=windowWidth;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height=heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height=Math.min(heightSize,radius*2);
        } else {
            height=windowHeight;
        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(90);
        paint.setStrokeWidth(10);
        canvas.drawCircle(getWidth()/2,getHeight()/2,300,paint);

        paint1 = new Paint();
        paint1.setColor(Color.GRAY);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, ii, paint1);
        int i = ii / 3;
        canvas.drawText(i+"%",getWidth() / 2-100,getHeight() / 2,paint);

        detector = new GestureDetector(new MyGestureDetector());
            setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return detector.onTouchEvent(event);
                }
            });

}
    public class MyGestureDetector extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            singleTapThread = new SingleTapThread();
            getHandler().postDelayed(singleTapThread, 100);
            return super.onSingleTapConfirmed(e);
        }
    }
    private class SingleTapThread implements Runnable {
        @Override
        public void run() {
            if (ii < 300) {
            invalidate();
            getHandler().postDelayed(singleTapThread, 100);
            ii = ii + 1;
//            } else {
//                getHandler().removeCallbacks(singleTapThread);
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取当前手指的坐标
        int currentX = (int) event.getX();
        int currentY = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = currentX;
                lastY = currentY;
                break;
            case MotionEvent.ACTION_MOVE:
                //获取当前偏移的坐标
                int biasX = currentX - lastX;
                int biasY = currentY - lastY;
                //重写layout方法改变控件所在屏幕的位置
                layout(getLeft()+biasX, getTop()+biasY, getRight()+biasX, getBottom()+biasY);
                break;
        }
        return true;
    }
}

