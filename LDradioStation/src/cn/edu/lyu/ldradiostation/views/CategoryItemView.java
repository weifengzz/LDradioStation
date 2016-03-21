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
 * ��ÿ����Ŀ����Ŀ
 * 
 * @author������
 * 
 * */
public class CategoryItemView extends View {
	private Paint titleTextPaint = null;// ��������
	private Paint headImgPaint = null;// ͷ��ͼƬ
	private Paint numPaint = null;// ������������
	private Paint authorTextPaint = null;// ��������
	private Paint datePaint = null;// ʱ������
	private String headText = "��������";// ��������
	private Bitmap imgBitmap = null;// ͼƬ
	private Paint imgplayPaint = null;// ��������ͼƬ
	private String numText = "1232";// ��������
	private String colockText = "����";// ʱ������
	private String authorText = "����";// ��������

	public CategoryItemView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		initPaint();
	}

	public CategoryItemView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public CategoryItemView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int width;
		int height;
		if (widthMode == MeasureSpec.EXACTLY) {
			width = widthSize;
		} else {
			width = 500;
		}

		if (heightMode == MeasureSpec.EXACTLY) {
			height = heightSize;
		} else {
			height = width / 4;
		}
		setMeasuredDimension(width, height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		drawTitleText(canvas);
		drawImg(canvas);
		drawImgplay(canvas);
		drawNumText(canvas);
		drawClockplay(canvas);
		drawClockText(canvas);
		drawAuthorplay(canvas);
		drawAuthorText(canvas);
	}

	/**
	 * ��ʼ������
	 * */
	private void initPaint() {
		titleTextPaint = new Paint();

		titleTextPaint.setAntiAlias(true);
		titleTextPaint.setColor(Color.BLACK);
		headImgPaint = new Paint();
		headImgPaint.setAntiAlias(true);
		numPaint = new Paint();
		numPaint.setAntiAlias(true);

		numPaint.setColor(Color.rgb(127, 127, 127));
		authorTextPaint = new Paint();
		authorTextPaint.setAntiAlias(true);
		datePaint = new Paint();
		datePaint.setAntiAlias(true);
		imgplayPaint = new Paint();
		imgplayPaint.setAntiAlias(true);
	}

	/**
	 * ��ͷ��ͼƬ
	 * */
	private void drawImg(Canvas canvas) {
		RectF rectF = new RectF(getMeasuredWidth() / 30,
				getMeasuredHeight() / 15, getMeasuredHeight() - 2
						* getMeasuredHeight() / 15 + getMeasuredWidth() / 30,
				getMeasuredHeight() - getMeasuredHeight() / 15);
		canvas.drawBitmap(getImg(), null, rectF, headImgPaint);
	}

	/**
	 * ����ͼƬ
	 * */
	public void setImg(Bitmap imgBitmap) {
		this.imgBitmap = imgBitmap;
		invalidate();
	}

	/**
	 * �õ�ͼƬ
	 * */
	public Bitmap getImg() {
		if (this.imgBitmap == null) {
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.play_mrbg);
			return bitmap;
		} else {
			return imgBitmap;
		}
	}

	/**
	 * ����������
	 * */
	private void drawTitleText(Canvas canvas) {
		titleTextPaint.setTextSize(getMeasuredHeight() / 5);
		float bottom = titleTextPaint.getFontMetrics().bottom;
		canvas.drawText(getTitleText(), getMeasuredWidth() / 30
				+ getMeasuredHeight() - 2 * getMeasuredHeight() / 15
				+ getMeasuredWidth() / 30,
				bottom * 3 + getMeasuredHeight() / 8, titleTextPaint);
	}

	/**
	 * ���ñ�������
	 * */
	public void setTitleText(String headText) {
		this.headText = headText;
		invalidate();
	}

	/**
	 * �õ���������
	 * */
	public String getTitleText() {
		return headText;
	}

	/**
	 * ����������ͼƬ
	 * */
	private void drawImgplay(Canvas canvas) {
		float x1 = getMeasuredWidth() / 30 + getMeasuredHeight() - 2
				* getMeasuredHeight() / 15 + getMeasuredWidth() / 30;
		float y1 = getMeasuredHeight() / 4 * 2 + getMeasuredHeight() / 10;
		float x2 = getMeasuredWidth() / 30 + getMeasuredHeight() - 2
				* getMeasuredHeight() / 15 + getMeasuredWidth() / 30
				+ getMeasuredHeight() / 5;
		float y2 = getMeasuredHeight() / 4 * 2 + getMeasuredHeight() / 10
				+ getMeasuredHeight() / 5;
		RectF rectF = new RectF(x1, y1, x2, y2);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.detail_item_play);
		canvas.drawBitmap(bitmap, null, rectF, imgplayPaint);
	}

	/**
	 * ��ʱ��ͼƬ
	 * */
	private void drawClockplay(Canvas canvas) {
		float x1 = getMeasuredWidth() / 2;
		float y1 = getMeasuredHeight() / 2 + getMeasuredHeight() / 10;
		float x2 = getMeasuredWidth() / 2 + getMeasuredHeight() / 5;
		float y2 = y1 + getMeasuredHeight() / 5;
		RectF rectF = new RectF(x1, y1, x2, y2);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.detail_colock);
		canvas.drawBitmap(bitmap, null, rectF, imgplayPaint);
	}

	/**
	 * ������ͼƬ
	 * */
	private void drawAuthorplay(Canvas canvas) {
		float x1 = getMeasuredWidth() / 4 * 3;
		float y1 = getMeasuredHeight() / 2 + getMeasuredHeight() / 10;
		float x2 = getMeasuredWidth() / 4 * 3 + getMeasuredHeight() / 5;
		float y2 = y1 + getMeasuredHeight() / 5;
		RectF rectF = new RectF(x1, y1, x2, y2);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.detail_author);
		canvas.drawBitmap(bitmap, null, rectF, imgplayPaint);
	}

	/**
	 * ������
	 * */
	private void drawNumText(Canvas canvas) {
		float y1 = getMeasuredHeight() / 4 * 2 + getMeasuredHeight() / 10;
		float y2 = getMeasuredHeight() / 4 * 2 + getMeasuredHeight() / 10
				+ getMeasuredHeight() / 5;
		numPaint.setTextSize(y2 - y1);
		float bottom = numPaint.getFontMetrics().bottom;
		float x = getMeasuredWidth() / 30 + getMeasuredHeight() - 2
				* getMeasuredHeight() / 15 + getMeasuredWidth() / 30
				+ getMeasuredHeight() / 5;
		float y = getMeasuredHeight() / 2 + getMeasuredHeight() / 10 + 3
				* bottom;

		canvas.drawText(getNumText(), x, y, numPaint);
	}

	/**
	 * ���ñ�������
	 * */
	public void setNumText(String numText) {
		this.numText = numText;
		invalidate();
	}

	/**
	 * �õ���������
	 * */
	public String getNumText() {
		return numText;
	}

	/**
	 * ������
	 * */
	private void drawClockText(Canvas canvas) {
		float y1 = getMeasuredHeight() / 2 + getMeasuredHeight() / 10;
		float y2 = getMeasuredHeight() / 2 + getMeasuredHeight() / 10
				+ getMeasuredHeight() / 5;
		numPaint.setTextSize(y2 - y1);
		float bottom = numPaint.getFontMetrics().bottom;
		float x = getMeasuredWidth() / 2 + getMeasuredHeight() / 5;
		float y = getMeasuredHeight() / 2 + getMeasuredHeight() / 10 + 3
				* bottom;

		canvas.drawText(getClockText(), x, y, numPaint);
	}

	/**
	 * ���ñ�������
	 * */
	public void setClockText(String colockText) {
		this.colockText = colockText;
		invalidate();
	}

	/**
	 * �õ���������
	 * */
	public String getClockText() {
		return colockText;
	}

	/**
	 * ������
	 * */
	private void drawAuthorText(Canvas canvas) {
		float y1 = getMeasuredHeight() / 2 + getMeasuredHeight() / 10;
		float y2 = getMeasuredHeight() / 2 + getMeasuredHeight() / 10
				+ getMeasuredHeight() / 5;
		numPaint.setTextSize(y2 - y1);
		float bottom = numPaint.getFontMetrics().bottom;
		float x = getMeasuredWidth() / 4 * 3 + getMeasuredHeight() / 5;
		float y = getMeasuredHeight() / 2 + getMeasuredHeight() / 10 + 3
				* bottom;

		canvas.drawText(getAuthorText(), x, y, numPaint);
	}

	/**
	 * ���ñ�������
	 * */
	public void setAuthorText(String authorText) {
		this.authorText = authorText;
		invalidate();
	}

	/**
	 * �õ���������
	 * */
	public String getAuthorText() {
		return authorText;
	}
}
