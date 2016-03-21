package cn.edu.lyu.ldradiostation.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * ������������Ƽ�ҳ��㲥�����ŵĽ���
 * 
 * @author ������
 * 
 * */
public class AllMbView extends View {
	private Paint imgPaint = null;// ͼƬ����
	private Paint txtPaint = null;// ���ֻ���
	private Paint linePaint = null;// ����͸���Ĵ���
	private Bitmap tjBitmap = null;// ͼƬ
	private String text = "";// ����

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
	 * ��ʼ������
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
	 * ��ͼƬ
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
			// setText("���ڼ���...");
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
	 * �����ֺ���
	 * */
	private void drawLineAndText(Canvas canvas) {
		int bottom = (int) txtPaint.getFontMetrics().bottom;
		// ������
		canvas.drawLine(5, getMeasuredHeight() / 15
				+ (getMeasuredHeight() - 2 * 5) - 20, getMeasuredWidth() - 5,
				getMeasuredHeight() / 15 + (getMeasuredHeight() - 2 * 5) - 20,
				linePaint);
		// д����
		canvas.drawText(getText(),
				getMeasuredWidth() / 2 - txtPaint.measureText(text) / 2,
				getMeasuredHeight() / 15 + (getMeasuredHeight() - 10) - 20
						+ txtPaint.getFontMetrics().bottom, txtPaint);
	}

	/**
	 * ����ͼƬ
	 * */
	public void setImg(Bitmap tjBitmap) {
		this.tjBitmap = tjBitmap;
		invalidate();
	}

	/**
	 * �õ�ͼƬ
	 * */
	public Bitmap getImg() {
		return tjBitmap;
	}

	/**
	 * ��������
	 * 
	 * @param text��������
	 * */
	public void setText(String text) {
		this.text = text;
		invalidate();
	}

	/**
	 * ��������
	 * 
	 * @param text�õ�����
	 * */
	public String getText() {
		return text;
	}

	/**
	 * �жϵ�ǰ�Ŀ�߱�
	 * */

}
