package test.qht.com.test_view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by ${仇海涛} on 2017/5/24.
 * 类的用途 ：
 */

public class TesstViewGroup extends ViewGroup {

    private int marginTop = 50;
    private int marginLeft = 50;
    private int marginRight = 50;
    private int marginBottom = 50;
    // 0 是 horizontal 1 是 vertital
    private int orientation = 1;

    public TesstViewGroup(Context context) {
        super(context);
    }

    public TesstViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TesstViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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

        if (widthmode == MeasureSpec.EXACTLY & heightmode == MeasureSpec.EXACTLY) {
            width = widthsize;
            height = heightsize;
        } else {

          /*  width = 300;
            height = 100;*/

            for (int i = 0; i < getChildCount(); i++) {
                int measuredWidth = getChildAt(i).getMeasuredWidth();
                int measuredHeight = getChildAt(i).getMeasuredHeight();
                if (orientation == 0) {
                    width += measuredWidth + marginLeft + marginRight;
                    height = measuredHeight + marginTop + marginBottom;
                } else if (orientation == 1) {
                    width = measuredWidth + marginLeft + marginRight;
                    if (i == 0) {
                        height = measuredHeight + marginTop + marginBottom;
                    } else {
                        height += measuredHeight + marginTop;
                    }

                }
                measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
            }

        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int childCount = getChildCount();

        int left = marginLeft;
        int top = marginTop;
        int bottom = 0;
        int right = 0;

        for (int j = 0; j < childCount; j++) {

            int measuredWidth = getChildAt(j).getMeasuredWidth();
            int measuredHeight = getChildAt(j).getMeasuredHeight();




            if (j == 0){
                top = bottom + top;
                bottom = measuredHeight + top;
                getChildAt(j).layout(left, top, measuredWidth + left, bottom);
            } else {
                int top1 = getChildAt(j-1).getBottom()+ top;
                int bottom1 = top1+measuredHeight;
                getChildAt(j).layout(left, top1, measuredWidth + left, bottom1);
            }



        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

}
