package test.qht.com.test_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ${仇海涛} on 2017/5/15.
 * 类的用途 ：
 */

public class MyView extends View {


    private Paint paint = new Paint();



    public MyView(Context context) {
        //super(context);
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        // super(context, attrs);
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        //super(context, attrs, defStyleAttr);
        this(context, attrs, defStyleAttr, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(9);
        //onDrawy(canvas);
        //canvas.drawArc(getWidth()/3,getHeight()/3*2,getWidth()/3*2,getWidth(),90,270,true,paint);
        paint.setColor(Color.GRAY);
        canvas.drawArc(getWidth()/3,getWidth()/3,getWidth()/3*2,getWidth()/3*2,0,72,true,paint);
        paint.setColor(Color.YELLOW);
        canvas.drawArc(getWidth()/3,getWidth()/3,getWidth()/3*2,getWidth()/3*2,72,72,true,paint);
        paint.setColor(Color.BLUE);
        canvas.drawArc(getWidth()/3,getWidth()/3,getWidth()/3*2,getWidth()/3*2,144,72,true,paint);
        paint.setColor(Color.MAGENTA);
        canvas.drawArc(getWidth()/3,getWidth()/3,getWidth()/3*2,getWidth()/3*2,216,72,true,paint);
        paint.setColor(Color.BLACK);
        canvas.drawArc(getWidth()/3,getWidth()/3,getWidth()/3*2,getWidth()/3*2,288,72,true,paint);

    }
    void onDrawy(Canvas canvas){
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(9);
        canvas.drawCircle(getWidth()/3,getHeight()/3,getWidth()/6,paint);
        canvas.drawCircle(getWidth()/3,getHeight()/3,2,paint);
        canvas.drawLine(getWidth()/3,getHeight()/3,getWidth()/3,getHeight()/3-getWidth()/6,paint);

    }
    void onDrawline(Canvas canvas){
        paint.setStrokeWidth(3);

    }

}
