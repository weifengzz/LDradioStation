package cn.edu.lyu.ldradiostation.views;

import cn.edu.lyu.ldradiostation.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 本类自定义view画主界面
 * 
 * @author宋熙明
 * */
public class DetailCenterView extends View {
	private Paint textPaint = null;// 头部文字画笔
	private Paint imgPaint = null;// 中部图片画笔
	private Paint linePaint = null; // 划线
	private String text = null;// 文字
	private int imgId = 0;// 图片

	public DetailCenterView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		initPaint();
		setText("暂无内容");
		setImg(R.drawable.play_mrbg);
	}

	public DetailCenterView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public DetailCenterView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		drawLineAndText(canvas);
		drawImg(canvas);
	}

	/**
	 * 初始化Paint
	 * */
	private void initPaint() {
		// textPaint
		textPaint = new Paint();
		textPaint.setAntiAlias(true);
		textPaint.setTextSize(20);
		textPaint.setColor(Color.WHITE);
		// linePaint
		linePaint = new Paint();
		linePaint.setStrokeWidth(2);
		linePaint.setColor(Color.WHITE);
		linePaint.setAntiAlias(true);
		// imagePaint
		imgPaint = new Paint();
		imgPaint.setAntiAlias(true);

	}

	/**
	 * 画文字和线
	 * */
	private void drawLineAndText(Canvas canvas) {
		int bottom = (int) textPaint.getFontMetrics().bottom;
		// 画左边的线
		canvas.drawLine(getMeasuredWidth() / 2 - textPaint.measureText(text)
				/ 2 - 30, getMeasuredHeight() / 15, getMeasuredWidth() / 2
				- textPaint.measureText(text) / 2 - 80,
				getMeasuredHeight() / 15, linePaint);
		// 写文字
		canvas.drawText(text,
				getMeasuredWidth() / 2 - textPaint.measureText(text) / 2,
				getMeasuredHeight() / 15 + textPaint.getFontMetrics().bottom,
				textPaint);
		// 画右边的线
		canvas.drawLine(getMeasuredWidth() / 2 + textPaint.measureText(text)
				/ 2 + 30, getMeasuredHeight() / 15, getMeasuredWidth() / 2
				+ textPaint.measureText(text) / 2 + 80,
				getMeasuredHeight() / 15, linePaint);
	}

	/**
	 * 画图片
	 * */
	private void drawImg(Canvas canvas) {
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), getImg());
		RectF rectF = new RectF(getMeasuredWidth() / 8, getMeasuredHeight()
				/ 15 + getMeasuredWidth() / 6, getMeasuredWidth()
				- getMeasuredWidth() / 8, getMeasuredHeight() / 15
				+ getMeasuredWidth() / 6
				+ (getMeasuredWidth() - getMeasuredWidth() / 4));
		canvas.drawBitmap(bitmap, null, rectF, imgPaint);
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

	/**
	 * 设置图片
	 * */
	public void setImg(int imgId) {
		this.imgId =imgId;
		invalidate();
	}

	/**
	 * 得到图片
	 * */
	public int getImg() {
		return imgId;
	}
	
	
	
}
