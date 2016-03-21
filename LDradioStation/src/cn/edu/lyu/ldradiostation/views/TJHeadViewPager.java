package cn.edu.lyu.ldradiostation.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class TJHeadViewPager extends ViewPager {

	int downX;
	int downY;

	public TJHeadViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {

		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downX = (int) ev.getX();
			downY = (int) ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			int moveX = (int) ev.getX();
			int moveY = (int) ev.getY();
			if (Math.abs(moveX - downX) > Math.abs(moveY - downY)) {
				// 水平移动
				// 参数值为true：外层ViewPager不干扰里层ViewPager滑动
				// 参数值为false
				getParent().requestDisallowInterceptTouchEvent(true);
			} else {
				getParent().requestDisallowInterceptTouchEvent(false);
			}
			break;
		}
		return super.dispatchTouchEvent(ev);
	}
}
