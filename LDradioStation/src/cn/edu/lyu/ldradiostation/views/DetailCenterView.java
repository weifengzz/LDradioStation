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
 * �����Զ���view��������
 * 
 * @author������
 * */
public class DetailCenterView extends View {
	private Paint textPaint = null;// ͷ�����ֻ���
	private Paint imgPaint = null;// �в�ͼƬ����
	private Paint linePaint = null; // ����
	private String text = null;// ����
	private int imgId = 0;// ͼƬ

	public DetailCenterView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		initPaint();
		setText("��������");
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
	 * ��ʼ��Paint
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
	 * �����ֺ���
	 * */
	private void drawLineAndText(Canvas canvas) {
		int bottom = (int) textPaint.getFontMetrics().bottom;
		// ����ߵ���
		canvas.drawLine(getMeasuredWidth() / 2 - textPaint.measureText(text)
				/ 2 - 30, getMeasuredHeight() / 15, getMeasuredWidth() / 2
				- textPaint.measureText(text) / 2 - 80,
				getMeasuredHeight() / 15, linePaint);
		// д����
		canvas.drawText(text,
				getMeasuredWidth() / 2 - textPaint.measureText(text) / 2,
				getMeasuredHeight() / 15 + textPaint.getFontMetrics().bottom,
				textPaint);
		// ���ұߵ���
		canvas.drawLine(getMeasuredWidth() / 2 + textPaint.measureText(text)
				/ 2 + 30, getMeasuredHeight() / 15, getMeasuredWidth() / 2
				+ textPaint.measureText(text) / 2 + 80,
				getMeasuredHeight() / 15, linePaint);
	}

	/**
	 * ��ͼƬ
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
	 * ����ͼƬ
	 * */
	public void setImg(int imgId) {
		this.imgId =imgId;
		invalidate();
	}

	/**
	 * �õ�ͼƬ
	 * */
	public int getImg() {
		return imgId;
	}
	
	
	
}
