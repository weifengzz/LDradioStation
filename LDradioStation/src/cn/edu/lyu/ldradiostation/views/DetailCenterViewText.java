package cn.edu.lyu.ldradiostation.views;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class DetailCenterViewText extends TextView {
	private Paint textPaint = null;// ͷ�����ֻ���
	public DetailCenterViewText(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public DetailCenterViewText(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public DetailCenterViewText(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}
	/**
	 * ��ʼ��Paint
	 * */
	private void initPaint() {
		textPaint = new Paint();
		//imgPaint = new Paint();
	}

}
