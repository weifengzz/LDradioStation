package cn.edu.lyu.ldradiostation.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 本类的作用是推荐页面广播和新闻的界面
 * 
 * @author 宋熙明
 * 
 * */
public class AllMbView extends View {
	private Paint imgPaint = null;// 图片画笔
	private Paint txtPaint = null;// 文字画笔
	private Paint linePaint = null;// 画半透明的粗线
	private Bitmap tjBitmap = null;// 图片
	private String text = "";// 文字

	public AllMbView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		initPaint();
	}

	public AllMbView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public AllMbView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		drawImg(canvas);
	}

	/**
	 * 初始化画笔
	 * */
	private void initPaint() {
		imgPaint = new Paint();
		imgPaint.setAntiAlias(true);
		txtPaint = new Paint();
		txtPaint.setAntiAlias(true);
		txtPaint.setTextSize(20);
		txtPaint.setColor(0xffffffff);
		linePaint = new Paint();
		linePaint.setAntiAlias(true);
		linePaint.setStrokeWidth(35);
		linePaint.setAlpha(90);
	}

	/**
	 * 画图片
	 * */
	private void drawImg(Canvas canvas) {

		if (tjBitmap == null) {
			// Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
			// R.drawable.tj_bg);
			// RectF rectF = new RectF(
			// getMeasuredWidth() / 3,
			// getMeasuredHeight() / 15,
			// getMeasuredWidth() - getMeasuredWidth() / 9,
			// getMeasuredHeight()
			// / 15
			// + (getMeasuredHeight() - 2 * getMeasuredWidth() / 9));
			// canvas.drawBitmap(bitmap, null, rectF, imgPaint);
			// setText("正在加载...");
			drawLineAndText(canvas);
		} else {
			RectF rectF = new RectF(5, getMeasuredHeight() / 15,
					getMeasuredWidth() - 5, getMeasuredHeight() / 15
							+ (getMeasuredHeight() - 2 * 5));
			canvas.drawBitmap(getImg(), null, rectF, imgPaint);
			drawLineAndText(canvas);
		}

	}

	/**
	 * 画文字和线
	 * */
	private void drawLineAndText(Canvas canvas) {
		int bottom = (int) txtPaint.getFontMetrics().bottom;
		// 画粗线
		canvas.drawLine(5, getMeasuredHeight() / 15
				+ (getMeasuredHeight() - 2 * 5) - 20, getMeasuredWidth() - 5,
				getMeasuredHeight() / 15 + (getMeasuredHeight() - 2 * 5) - 20,
				linePaint);
		// 写文字
		canvas.drawText(getText(),
				getMeasuredWidth() / 2 - txtPaint.measureText(text) / 2,
				getMeasuredHeight() / 15 + (getMeasuredHeight() - 10) - 20
						+ txtPaint.getFontMetrics().bottom, txtPaint);
	}

	/**
	 * 设置图片
	 * */
	public void setImg(Bitmap tjBitmap) {
		this.tjBitmap = tjBitmap;
		invalidate();
	}

	/**
	 * 得到图片
	 * */
	public Bitmap getImg() {
		return tjBitmap;
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
	 * 判断当前的宽高比
	 * */

}
