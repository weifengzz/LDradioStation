package cn.edu.lyu.ldradiostation.fragments;

import cn.edu.lyu.ldradiostation.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NewsFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_news, container, null);
		return super.onCreateView(inflater, container, savedInstanceState);
	}
}
