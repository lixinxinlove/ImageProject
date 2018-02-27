package com.lixinxin.imageproject.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lixinxin.imageproject.R;

/**
 * Created by android on 2018/2/27.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private Bitmap mIcon;
    private Paint mPaint;

    public DividerItemDecoration(Context context) {
        mPaint = new Paint();
        // 画笔颜色设置为红色
        mPaint.setColor(Color.RED);
        // 获取图片资源
        mIcon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_round);
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        // 获得每个Item的位置
        int itemPosition = parent.getChildAdapterPosition(view);
        // 第1个Item不绘制分割线
        if (itemPosition != 0) {
            // 设置间隔区域为10px,即onDraw()可绘制的区域为10px
            outRect.set(0, 0, 0, 10);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        // 获取RecyclerView的Child view的个数
        int childCount = parent.getChildCount();
        // 遍历每个Item，分别获取它们的位置信息，然后再绘制对应的分割线
        for (int i = 0; i < childCount; i++) {
            // 获取每个Item的位置
            final View child = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(child);
            // 第1个Item不需要绘制
//            if (index == 0) {
//                continue;
//            }
            // 获取布局参数
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            // 设置矩形(分割线)的宽度为10px
            final int mDivider = 10;
            // 根据子视图的位置 & 间隔区域，设置矩形（分割线）的2个顶点坐标(左上 & 右下)
            // 矩形左上顶点 = (ItemView的左边界,ItemView的下边界)
            // ItemView的左边界 = RecyclerView 的左边界 + paddingLeft距离 后的位置
            final int left = parent.getPaddingLeft();
            // ItemView的下边界：ItemView 的 bottom坐标 + 距离RecyclerView底部距离 +translationY
            final int top = child.getBottom() + params.bottomMargin + Math.round(ViewCompat.getTranslationY(child));
            // 矩形右下顶点 = (ItemView的右边界,矩形的下边界)
            // ItemView的右边界 = RecyclerView 的右边界减去 paddingRight 后的坐标位置
            final int right = parent.getWidth() - parent.getPaddingRight();
            // 绘制分割线的下边界 = ItemView的下边界+分割线的高度
            final int bottom = top + mDivider;
            // 通过Canvas绘制矩形（分割线）
            c.drawRect(left, top, right, bottom, mPaint);
        }
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        // 获取Item的总数
        int childCount = parent.getChildCount();
        // 遍历Item
        for (int i = 0; i < childCount; i++) {
            // 获取每个Item的位置
            View view = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(view);

            // 设置绘制内容的坐标(ItemView的左边界,ItemView的上边界)
            // ItemView的左边界 = RecyclerView 的左边界 = paddingLeft距离 后的位置
            final int left = parent.getWidth() / 2 - mIcon.getWidth() / 2;
            // ItemView的上边界
            float top = view.getTop() + 40;

            // 第1个ItemView不绘制
//            if ( index == 0 ) {
//                continue;
//            }
            // 通过Canvas绘制角标
            c.drawBitmap(mIcon, left, top, mPaint);
        }
    }
}
