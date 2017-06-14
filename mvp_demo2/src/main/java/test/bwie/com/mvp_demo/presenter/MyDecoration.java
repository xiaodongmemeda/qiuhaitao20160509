package test.bwie.com.mvp_demo.presenter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ${仇海涛} on 2017/5/18.
 * 类的用途 ：
 */

public class MyDecoration extends RecyclerView.ItemDecoration {


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        Paint paint = new Paint();
        paint.setColor(Color.MAGENTA);
        for (int i = 0; i < childCount; i++) {

            View childAt = parent.getChildAt(i);
            c.drawRect(childAt.getLeft(),childAt.getBottom()+8,childAt.getRight(),childAt.getBottom(),paint);

        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

    }
}
