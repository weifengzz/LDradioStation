package cn.edu.lyu.ldradiostation.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
/**
 * ����¼����ı���
 * @author������
 * 
 * */
public class LoginBG extends View{
	private Paint paintRect = null;
	public LoginBG(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		initView();
	}

	public LoginBG(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		// TODO Auto-generated constructor stub
	}

	public LoginBG(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		drawRect(canvas);
	}
	private void initView(){
		paintRect = new Paint();
		paintRect.setAntiAlias(true);
		paintRect.setColor(Color.WHITE);
	}
	/**
	 * ������
	 * */
	private void drawRect(Canvas canvas) {
		RectF oval3 = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());// ���ø��µĳ�����
		canvas.drawRoundRect(oval3, 30, 30, paintRect);// �ڶ���������x�뾶��������������y�뾶
	}
}
