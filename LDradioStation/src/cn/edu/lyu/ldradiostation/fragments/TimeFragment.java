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
 * �����ǽ�Ŀ����fragment
 * */
public class TimeFragment extends Fragment {
	private LinearLayout layout = null;// ��ȡlayout������ӿؼ�

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_time, container, false);
		initView(view);
		// ��layout����Ŀؼ������¼���Ӧ
		// Ĭ���ǵ�ǰ����һ��ѡ��
		Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
		initWeek(mWay);
		// ��HorizontalScrollView��ÿһ���ӿؼ���ӵ���¼�
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
							// ��������
							android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager
									.beginTransaction();
							TimeDetailFragement timeDetailFragement = new TimeDetailFragement();
							Bundle bundle = new Bundle();
							bundle.putString("msg", layout.indexOfChild(view)
									+ "");// �����ݷ�װ��Bundle��
							timeDetailFragement.setArguments(bundle);
							fragmentTransaction.replace(R.id.time_ll,
									timeDetailFragement);
							// �ύ����
							fragmentTransaction.commit();
						}
					});
		}
		return view;
	}

	/**
	 * 
	 * ��ʼ��view�ؼ�
	 * */
	private void initView(View view) {
		layout = (LinearLayout) view.findViewById(R.id.time_sv);
		// ��LearLayout�µ�ÿһ���ӿؼ����ı���ֵ
		for (int i = 0; i < layout.getChildCount(); i++) {
			if (i == 0) {
				((TimeView) (layout.getChildAt(i))).setText("��һ");
			} else if (i == 1) {
				((TimeView) (layout.getChildAt(i))).setText("�ܶ�");
			} else if (i == 2) {
				((TimeView) (layout.getChildAt(i))).setText("����");
			} else if (i == 3) {
				((TimeView) (layout.getChildAt(i))).setText("����");
			} else if (i == 4) {
				((TimeView) (layout.getChildAt(i))).setText("����");
			} else if (i == 5) {
				((TimeView) (layout.getChildAt(i))).setText("����");
			} else if (i == 6) {
				((TimeView) (layout.getChildAt(i))).setText("����");
			}
			
		}
	}

	/**
	 * ���ݵ�ǰ����������Ĭ��ѡ����һ��
	 * 
	 * */

	private void initWeek(String week) {
		FragmentManager fragmentManager = getActivity()
				.getSupportFragmentManager();
		// ��������
		android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		TimeDetailFragement timeDetailFragement = new TimeDetailFragement();
		Bundle bundle = new Bundle();
		if ("2".equals(week)) {
			((TimeView) (layout.getChildAt(0))).setFlag(true);
			bundle.putString("msg", 0+"");// �����ݷ�װ��Bundle��
		} else if ("3".equals(week)) {
			((TimeView) (layout.getChildAt(1))).setFlag(true);
			bundle.putString("msg", 1+"");// �����ݷ�װ��Bundle��
		} else if ("4".equals(week)) {
			((TimeView) (layout.getChildAt(2))).setFlag(true);
			bundle.putString("msg", 2+"");// �����ݷ�װ��Bundle��
		} else if ("5".equals(week)) {
			((TimeView) (layout.getChildAt(3))).setFlag(true);
			bundle.putString("msg", 3+"");// �����ݷ�װ��Bundle��
		} else if ("6".equals(week)) {
			((TimeView) (layout.getChildAt(4))).setFlag(true);
			bundle.putString("msg", 4+"");// �����ݷ�װ��Bundle��
		} else if ("7".equals(week)) {
			((TimeView) (layout.getChildAt(5))).setFlag(true);
			bundle.putString("msg", 5+"");// �����ݷ�װ��Bundle��
		} else if ("1".equals(week)) {
			((TimeView) (layout.getChildAt(6))).setFlag(true);
			bundle.putString("msg", 6+"");// �����ݷ�װ��Bundle��
		}		
		timeDetailFragement.setArguments(bundle);
		fragmentTransaction.replace(R.id.time_ll, timeDetailFragement);
		// �ύ����
		fragmentTransaction.commit();
	}
}
