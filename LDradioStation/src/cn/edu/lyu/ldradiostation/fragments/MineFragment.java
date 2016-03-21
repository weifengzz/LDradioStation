package cn.edu.lyu.ldradiostation.fragments;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import cn.edu.lyu.ldradiostation.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MineFragment extends Fragment {
	@ViewInject(R.id.share)
	private Button shareButton;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_mine, container, false);
		return view;
	}
	@OnClick(R.id.share)
	public void share(View view){
		
	}
}
