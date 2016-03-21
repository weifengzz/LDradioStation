package cn.edu.lyu.ldradiostation.fragments;

import cn.edu.lyu.ldradiostation.R;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainFragment extends Fragment {
	/**
	 * 创建主页面的fragment
	 * 
	 * @author 宋熙明
	 * 
	 * */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_main, container, false);
		Bundle bundle = getArguments();
		int pageno = Integer.parseInt(bundle.getString("pageno"));
		switch (pageno) {
		case 1:
			TextView textView = new TextView(getActivity());
			textView.setText(pageno + "");
			textView.setTextColor(Color.RED);
			return textView;

		default:
			TextView textView1 = new TextView(getActivity());
			textView1.setText(pageno + "");
			textView1.setTextColor(Color.RED);
			return textView1;
		}
		//return view;
	}
}
