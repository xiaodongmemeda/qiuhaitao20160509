package test.qht.com.test_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by ${仇海涛} on 2017/5/24.
 * 类的用途 ：
 */

public class MyTextView extends View {

    private String string;
    private int color;
    private int dimensionPixelSize;

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    private void init(AttributeSet attrs) {
        TypedArray t = getContext().obtainStyledAttributes(attrs, R.styleable.MyTextView);
        string = t.getString(R.styleable.MyTextView_titleText);
        color = t.getColor(R.styleable.MyTextView_titleTextColor, Color.BLACK);
        dimensionPixelSize = t.getDimensionPixelSize(R.styleable.MyTextView_titleTextSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
        t.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = 0;
        int height = 0;

        int widthmode = MeasureSpec.getMode(widthMeasureSpec);
        int widthsize = MeasureSpec.getSize(widthMeasureSpec);

        int heightmode = MeasureSpec.getMode(heightMeasureSpec);
        int heightsize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthmode == MeasureSpec.EXACTLY) {
            width = widthsize;

        } else {
            width = 300;

        }
        if (heightmode == MeasureSpec.EXACTLY) {

            height = heightsize;
        } else {

            height = 100;
        }

        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        onDrawText(canvas);
    }
    public void onDrawText(Canvas canvas){

        int height = getMeasuredHeight() / 2;
        int width = getMeasuredWidth() / 2;

        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawColor(Color.BLACK);
        paint.setTextSize(dimensionPixelSize);
        paint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        int baseLineY = (fm.bottom - fm.top) / 2 - fm.bottom;
        canvas.drawText(string,width,height+baseLineY,paint);

    }
}
