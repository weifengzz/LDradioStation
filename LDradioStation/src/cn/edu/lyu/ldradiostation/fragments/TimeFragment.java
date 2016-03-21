package cn.edu.lyu.ldradiostation.fragments;

import java.util.Calendar;
import java.util.TimeZone;

import cn.edu.lyu.ldradiostation.R;
import cn.edu.lyu.ldradiostation.views.TimeView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * 本类是节目表单的fragment
 * */
public class TimeFragment extends Fragment {
	private LinearLayout layout = null;// 获取layout下面的子控件

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_time, container, false);
		initView(view);
		// 给layout下面的控件设置事件响应
		// 默认是当前的那一天选中
		Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
		initWeek(mWay);
		// 给HorizontalScrollView的每一个子控件添加点击事件
		for (int i = 0; i < layout.getChildCount(); i++) {
			((TimeView) (layout.getChildAt(i)))
					.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View view) {
							// TODO Auto-generated method stub
							for (int i = 0; i < layout.getChildCount(); i++) {
								((TimeView) (layout.getChildAt(i)))
										.setFlag(false);
							}
							((TimeView) view).setFlag(true);
							FragmentManager fragmentManager = getActivity()
									.getSupportFragmentManager();
							// 开启事务
							android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager
									.beginTransaction();
							TimeDetailFragement timeDetailFragement = new TimeDetailFragement();
							Bundle bundle = new Bundle();
							bundle.putString("msg", layout.indexOfChild(view)
									+ "");// 将数据封装在Bundle中
							timeDetailFragement.setArguments(bundle);
							fragmentTransaction.replace(R.id.time_ll,
									timeDetailFragement);
							// 提交事务
							fragmentTransaction.commit();
						}
					});
		}
		return view;
	}

	/**
	 * 
	 * 初始化view控件
	 * */
	private void initView(View view) {
		layout = (LinearLayout) view.findViewById(R.id.time_sv);
		// 给LearLayout下的每一个子控件的文本赋值
		for (int i = 0; i < layout.getChildCount(); i++) {
			if (i == 0) {
				((TimeView) (layout.getChildAt(i))).setText("周一");
			} else if (i == 1) {
				((TimeView) (layout.getChildAt(i))).setText("周二");
			} else if (i == 2) {
				((TimeView) (layout.getChildAt(i))).setText("周三");
			} else if (i == 3) {
				((TimeView) (layout.getChildAt(i))).setText("周四");
			} else if (i == 4) {
				((TimeView) (layout.getChildAt(i))).setText("周五");
			} else if (i == 5) {
				((TimeView) (layout.getChildAt(i))).setText("周六");
			} else if (i == 6) {
				((TimeView) (layout.getChildAt(i))).setText("周日");
			}
			
		}
	}

	/**
	 * 根据当前的日期设置默认选中哪一个
	 * 
	 * */

	private void initWeek(String week) {
		FragmentManager fragmentManager = getActivity()
				.getSupportFragmentManager();
		// 开启事务
		android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		TimeDetailFragement timeDetailFragement = new TimeDetailFragement();
		Bundle bundle = new Bundle();
		if ("2".equals(week)) {
			((TimeView) (layout.getChildAt(0))).setFlag(true);
			bundle.putString("msg", 0+"");// 将数据封装在Bundle中
		} else if ("3".equals(week)) {
			((TimeView) (layout.getChildAt(1))).setFlag(true);
			bundle.putString("msg", 1+"");// 将数据封装在Bundle中
		} else if ("4".equals(week)) {
			((TimeView) (layout.getChildAt(2))).setFlag(true);
			bundle.putString("msg", 2+"");// 将数据封装在Bundle中
		} else if ("5".equals(week)) {
			((TimeView) (layout.getChildAt(3))).setFlag(true);
			bundle.putString("msg", 3+"");// 将数据封装在Bundle中
		} else if ("6".equals(week)) {
			((TimeView) (layout.getChildAt(4))).setFlag(true);
			bundle.putString("msg", 4+"");// 将数据封装在Bundle中
		} else if ("7".equals(week)) {
			((TimeView) (layout.getChildAt(5))).setFlag(true);
			bundle.putString("msg", 5+"");// 将数据封装在Bundle中
		} else if ("1".equals(week)) {
			((TimeView) (layout.getChildAt(6))).setFlag(true);
			bundle.putString("msg", 6+"");// 将数据封装在Bundle中
		}		
		timeDetailFragement.setArguments(bundle);
		fragmentTransaction.replace(R.id.time_ll, timeDetailFragement);
		// 提交事务
		fragmentTransaction.commit();
	}
}
