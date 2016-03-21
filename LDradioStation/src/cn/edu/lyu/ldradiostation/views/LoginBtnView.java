package cn.edu.lyu.ldradiostation.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * �Զ����¼��ť��view
 * 
 * */
public class LoginBtnView extends View {
	private Paint paint = null;
	private Paint textPaint = null;

	public LoginBtnView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initPaint();
	}

	public LoginBtnView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public LoginBtnView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		drawRect(canvas);
		drawText(canvas);
	}

	/**
	 * ��ʼ��
	 * 
	 **/
	private void initPaint() {
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.rgb(114, 142, 163));
		textPaint = new Paint();
		textPaint.setAntiAlias(true);
		textPaint.setTextSize(30);
		textPaint.setColor(Color.WHITE);
	}

	/**
	 * ������
	 * */
	private void drawRect(Canvas canvas) {
		RectF oval3 = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());// ���ø��µĳ�����
		canvas.drawRoundRect(oval3, 15, 15, paint);// �ڶ���������x�뾶��������������y�뾶
	}

	/**
	 * 
	 * д����
	 * */
	private void drawText(Canvas canvas) {
		canvas.drawText("��¼",
				getMeasuredWidth() / 2 - textPaint.measureText("��¼")/2,
				getMeasuredHeight() / 2+textPaint.getFontMetrics().bottom*1,
				textPaint);
	}
}
