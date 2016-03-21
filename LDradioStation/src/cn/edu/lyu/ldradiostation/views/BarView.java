package cn.edu.lyu.ldradiostation.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class BarView extends View {
	private Paint barAllLinePaint = null;// �ܵĽ�����
	private Paint barLinePaint = null;// ���ŵ�ǰ������
	private Paint bar2LinePaint = null;// ���������
	private Paint startLine = null;// ��ͷ��һС������ɫ
	private Paint jxPaint = null;// ��һ��Բ�Ǿ���
	private float nowJD = 0.0f;// ��ǰ�Ľ���
	private float nowHC = 0.0f;// ��ǰ����Ľ���

	public BarView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		initPaint();
	}

	public BarView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public BarView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	/**
	 * ��ʼ��Paint
	 * */
	private void initPaint() {
		barAllLinePaint = new Paint();
		barAllLinePaint.setAntiAlias(true);
		barAllLinePaint.setStrokeWidth(8);
		barAllLinePaint.setColor(Color.WHITE);
		barLinePaint = new Paint();
		barLinePaint.setAntiAlias(true);
		barLinePaint.setStrokeWidth(8);
		barLinePaint.setColor(Color.GREEN);
		startLine = new Paint();
		startLine.setAntiAlias(true);
		startLine.setStrokeWidth(8);
		startLine.setColor(Color.GREEN);
		bar2LinePaint = new Paint();
		bar2LinePaint.setAntiAlias(true);
		bar2LinePaint.setStrokeWidth(8);
		bar2LinePaint.setColor(Color.GRAY);
		jxPaint = new Paint();
		jxPaint.setAntiAlias(true);
		jxPaint.setColor(Color.WHITE);
		jxPaint.setStyle(Paint.Style.FILL);// ����

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		drawView(canvas);
	}

	/**
	 * 
	 * ��Բ�Ǿ��κ���
	 * */
	private void drawView(Canvas canvas) {
		setNowJD(0.3f);
		setNowHC(0.4f);
		canvas.drawLine(0, getMeasuredHeight() / 2, getMeasuredWidth(),
				getMeasuredHeight() / 2, barAllLinePaint);
		canvas.drawLine(0, getMeasuredHeight() / 2, getNowHC()
				* getMeasuredWidth(), getMeasuredHeight() / 2, bar2LinePaint);
		canvas.drawLine(20, getMeasuredHeight() / 2, getNowJD()
				* getMeasuredWidth() - 20, getMeasuredHeight() / 2,
				barLinePaint);
		canvas.drawLine(0, getMeasuredHeight() / 2, 20,
				getMeasuredHeight() / 2, startLine);
		RectF oval3 = new RectF(getNowJD() * getMeasuredWidth() - 20,
				getMeasuredHeight() / 2 - 10, getNowJD() * getMeasuredWidth()
						+ 20, getMeasuredHeight() / 2 + 10);// ���ø��µĳ�����
		canvas.drawRoundRect(oval3, 10, 10, jxPaint);
	}

	/**
	 * ��ǰ�Ľ���
	 * 
	 * @param����ռ��Ļ�İٷֱ�
	 * */
	public void setNowJD(float nowJD) {
		this.nowJD = nowJD;
		invalidate();
	}

	/**
	 * �õ���ǰ�Ľ���
	 * */
	public float getNowJD() {
		return nowJD;
	}

	/**
	 * ����Ľ���
	 * 
	 * @param����ռ��Ļ�İٷֱ�
	 */
	public void setNowHC(float nowHC) {
		this.nowHC = nowHC;
		invalidate();
	}

	/**
	 * �õ���ǰ����ĵĽ���
	 * */
	public float getNowHC() {
		return nowHC;
	}

}
