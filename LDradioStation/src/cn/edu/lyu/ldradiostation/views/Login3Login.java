package cn.edu.lyu.ldradiostation.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
/**
 * 
 * 第三方登录的文字
 * */
public class Login3Login extends View{
	private Paint linePaint ;
	private Paint textPaint;
	public Login3Login(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		initView();
	}

	public Login3Login(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		// TODO Auto-generated constructor stub
	}

	public Login3Login(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
		
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawLine(0, getMeasuredHeight()/2, getMeasuredWidth()/2-textPaint.measureText("第三方登录")/2-20, getMeasuredHeight()/2, linePaint);
		canvas.drawLine(getMeasuredWidth()/2+textPaint.measureText("第三方登录")/2+20, getMeasuredHeight()/2, getMeasuredWidth(), getMeasuredHeight()/2, linePaint);
		canvas.drawText("第三方登录",getMeasuredWidth()/2-textPaint.measureText("第三方登录")/2, getMeasuredHeight()/2+textPaint.getFontMetrics().bottom, textPaint);
	}
	private void initView(){
		linePaint = new Paint();
		linePaint.setAntiAlias(true);
		linePaint.setStrokeWidth(3);
		linePaint.setColor(Color.rgb(114, 142, 163));
		textPaint = new Paint();
		textPaint.setTextSize(20);
		textPaint.setAntiAlias(true);
		textPaint.setColor(Color.rgb(114, 142, 163));
	}

}
