package cn.edu.lyu.ldradiostation.fragments;

import cn.edu.lyu.ldradiostation.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TimeDetailFragement extends Fragment {
	private TextView textView = null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_timedetail, container,
				false);
		textView = (TextView) view.findViewById(R.id.textView1);
		Bundle bundle = getArguments();
		String string = bundle.getString("msg");
		textView.setText(string);
		return view;
	}
}
