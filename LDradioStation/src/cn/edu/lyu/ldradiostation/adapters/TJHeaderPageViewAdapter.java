package cn.edu.lyu.ldradiostation.adapters;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class TJHeaderPageViewAdapter extends PagerAdapter {
	List<View> list;

	public TJHeaderPageViewAdapter(List<View> list) {
		this.list = list;
	}

	@Override
	public int getCount() {

		return list.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	// ¶¯Ì¬Ôö¼ÓView
	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		container.addView(list.get(position));
		list.get(position).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.e("====", "====" + position);
			}
		});
		return list.get(position);
	}

	// ÒÆ³ýView
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// super.destroyItem(container, position, object);
		 container.removeView(list.get(position));
	}
}
