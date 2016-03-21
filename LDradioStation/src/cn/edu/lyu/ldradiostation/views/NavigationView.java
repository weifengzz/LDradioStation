package cn.edu.lyu.ldradiostation.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 自定义导航栏
 * 
 * @author 宋熙明
 * 
 */
public class NavigationView extends TextView {
	private boolean flag = false;
	private Paint paint1 = null;
	private Paint paint2 = null;
	private Paint paint3 = null;
	private Paint paint4 = null;

	public NavigationView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
		invalidate();
	}

	private void init() {
		// TODO Auto-generated method stub
		//
		paint1 = new Paint();
		paint1.setColor(Color.BLACK);
		paint1.setStrokeWidth(14);
		paint2 = new Paint();
		paint2.setColor(Color.WHITE);
		paint2.setStrokeWidth(14);
		paint3 = new Paint();
		paint3.setStrokeWidth(7);
		paint3.setColor(Color.BLACK);
		paint4 = new Paint();
		paint4.setStrokeWidth(2);
		paint4.setColor(Color.BLACK);

	}

	public NavigationView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public NavigationView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		// 画头部的黑线
		canvas.drawLine(0, 0, getWidth(), 0, paint4);
		// 画白线
		canvas.drawLine(0, getHeight(), getWidth(), getHeight(), paint2);
		// 画细绿线
		canvas.drawLine(0, getHeight(), getWidth(), getHeight(), paint3);
		// 当选中的时候画粗绿线
		if (flag) {
			canvas.drawLine(0, getHeight(), getWidth(), getHeight(), paint1);
		}
	}
}
