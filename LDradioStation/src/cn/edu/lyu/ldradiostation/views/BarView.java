package cn.edu.lyu.ldradiostation.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class BarView extends View {
	private Paint barAllLinePaint = null;// 总的进度条
	private Paint barLinePaint = null;// 播放当前进度条
	private Paint bar2LinePaint = null;// 缓冲进度条
	private Paint startLine = null;// 开头的一小段是绿色
	private Paint jxPaint = null;// 画一个圆角矩形
	private float nowJD = 0.0f;// 当前的进度
	private float nowHC = 0.0f;// 当前缓存的进度

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
	 * 初始化Paint
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
		jxPaint.setStyle(Paint.Style.FILL);// 充满

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		drawView(canvas);
	}

	/**
	 * 
	 * 画圆角矩形和线
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
						+ 20, getMeasuredHeight() / 2 + 10);// 设置个新的长方形
		canvas.drawRoundRect(oval3, 10, 10, jxPaint);
	}

	/**
	 * 当前的进度
	 * 
	 * @param长度占屏幕的百分比
	 * */
	public void setNowJD(float nowJD) {
		this.nowJD = nowJD;
		invalidate();
	}

	/**
	 * 得到当前的进度
	 * */
	public float getNowJD() {
		return nowJD;
	}

	/**
	 * 缓存的进度
	 * 
	 * @param长度占屏幕的百分比
	 */
	public void setNowHC(float nowHC) {
		this.nowHC = nowHC;
		invalidate();
	}

	/**
	 * 得到当前缓存的的进度
	 * */
	public float getNowHC() {
		return nowHC;
	}

}
