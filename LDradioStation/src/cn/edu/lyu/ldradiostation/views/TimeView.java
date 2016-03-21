package cn.edu.lyu.ldradiostation.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 本类的作用是画时刻表
 * 
 * @author宋熙明
 * */
public class TimeView extends View {
	private Paint linePaint1 = null;// 画长粗线
	private Paint linePaint2 = null;// 画细线
	private String text = "";// 文字
	private Paint textPaint = null;// 写文字
	private boolean flag = false;// 文字是否被选中

	public TimeView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		initPaint();
	}

	public TimeView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public TimeView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		drawLine1(canvas);
	}

	/**
	 * 
	 * 选中的状态
	 * */
	public void setFlag(boolean flag) {
		this.flag = flag;
		invalidate();
	}

	/**
	 * 
	 * 初始化画笔
	 * */
	private void initPaint() {
		linePaint1 = new Paint();
		linePaint1.setStrokeWidth(2);
		linePaint1.setColor(Color.BLACK);
		linePaint1.setAntiAlias(true);
		linePaint2 = new Paint();
		linePaint2.setColor(Color.BLACK);
		linePaint2.setAntiAlias(true);
		linePaint2.setStrokeWidth(5);
		textPaint = new Paint();
		textPaint.setAntiAlias(true);
		textPaint.setTextSize(25);
	}

	/**
	 * 
	 * 画线
	 * */
	private void drawLine1(Canvas canvas) {
		for (int i = 0; i < 5; i++) {
			if (i == 3) {

				if (flag) {
					linePaint2.setColor(Color.BLUE);
					canvas.drawLine(getWidth() / 2 - 50, i * getHeight() / 5,
							getWidth() / 2, i * getHeight() / 5, linePaint2);
					textPaint.setColor(Color.BLUE);
					canvas.drawText(getText(), getWidth() / 2 + 10,
							(float) (i * getHeight() / 5 + textPaint
									.getFontMetrics().bottom * 1.5), textPaint);
				} else {
					textPaint.setColor(Color.BLACK);
					linePaint2.setColor(Color.BLACK);
					canvas.drawLine(getWidth() / 2 - 50, i * getHeight() / 5,
							getWidth() / 2, i * getHeight() / 5, linePaint2);
					canvas.drawText(getText(), getWidth() / 2 + 10,
							(float) (i * getHeight() / 5 + textPaint
									.getFontMetrics().bottom * 1.5), textPaint);
				}

			} else {
				canvas.drawLine(getWidth() / 2 - 30, i * getHeight() / 5,
						getWidth() / 2, i * getHeight() / 5, linePaint1);
			}
		}

	}

	/**
	 * 设置文字
	 * 
	 * @param text设置文字
	 * */
	public void setText(String text) {
		this.text = text;
		invalidate();
	}

	/**
	 * 设置文字
	 * 
	 * @param text得到文字
	 * */
	public String getText() {
		return text;
	}

}
