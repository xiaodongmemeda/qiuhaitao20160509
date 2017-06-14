package qiuhaitao.bwie.com.progress_demo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ASUS on 2017/6/11.
 */

public class Progress extends View {

    private Paint paint;


    public float x;
    public float y;
    private int out_circle_color;
    private int out_circle_width;
    private float in_circle_width = 0;
    private String drawText="0";

    public Progress(Context context) {
        super(context);
    }
    public void  setIn_circle_width(float in_circle_width){
        this.in_circle_width = in_circle_width;
    }
    public  void setDrawText(String text){
        this.drawText = text;
    }

    public Progress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Progress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Progress, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++)
        {
            int attr = a.getIndex(i);
            switch (attr)
            {
                case R.styleable.Progress_out_circle_color:
                    out_circle_color = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.Progress_out_circle_width:
                    // 默认颜色设置为黑色
                    out_circle_width = a.getInteger(attr, 0);
                    break;
            }

        }
        a.recycle();


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        x = MeasureSpec.getSize(widthMeasureSpec)/2;
        y = MeasureSpec.getSize(heightMeasureSpec)/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(x,y);
        onDrawOutCircle(canvas);
        onDrawInCircle(canvas);
        onDrawText(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public void onDrawOutCircle(Canvas canvas) {

        //paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(9);
        canvas.drawCircle(0,0,out_circle_width,paint);

    }
    public void onDrawInCircle(Canvas canvas){

        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(9);
        canvas.drawCircle(0,0,in_circle_width,paint);

    }

    public void  onDrawText(Canvas canvas){

        paint.setColor(Color.BLACK);
        paint.setTextSize(66);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(drawText,0,33,paint);

    }

}
