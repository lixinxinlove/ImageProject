package com.lixinxin.imageproject.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;
import android.widget.Scroller;

public class LeeScrollView extends ScrollView {

	ScrollViewListener scrollListener;

	private Scroller mScroller;

	public LeeScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mScroller = new Scroller(context);
	}

	public LeeScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mScroller = new Scroller(context);
	}

	public LeeScrollView(Context context) {
		super(context);
		mScroller = new Scroller(context);
	}

	// 调用此方法滚动到目标位置 duration滚动时间
	public void smoothScrollToSlow(int fx, int fy, int duration) {
		int dx = fx - getScrollX();// mScroller.getFinalX(); 普通view使用这种方法
		int dy = fy - getScrollY(); // mScroller.getFinalY();
		smoothScrollBySlow(dx, dy, duration);
	}

	// 调用此方法设置滚动的相对偏移
	public void smoothScrollBySlow(int dx, int dy, int duration) {

		// 设置mScroller的滚动偏移量
		mScroller.startScroll(getScrollX(), getScrollY(), dx, dy, duration);// scrollView使用的方法（因为可以触摸拖动）
		// mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(),
		// dx, dy, duration); //普通view使用的方法
		invalidate();// 这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面，看不到滚动效果
	}

	@Override
	public void computeScroll() {

		// 先判断mScroller滚动是否完成
		if (mScroller.computeScrollOffset()) {

			// 这里调用View的scrollTo()完成实际的滚动
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());

			// 必须调用该方法，否则不一定能看到滚动效果
			postInvalidate();
		}
		super.computeScroll();
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if (null != scrollListener) {
			scrollListener.onScrollChanged(this, l, t, oldl, oldt);
		}
	}

	public ScrollViewListener getScrollListener() {
		return scrollListener;
	}

	public void setScrollListener(ScrollViewListener scrollListener) {
		this.scrollListener = scrollListener;
	}

	public interface ScrollViewListener {

		void onScrollChanged(ScrollView scrollView, int x, int y, int oldx, int oldy);

	}

}
