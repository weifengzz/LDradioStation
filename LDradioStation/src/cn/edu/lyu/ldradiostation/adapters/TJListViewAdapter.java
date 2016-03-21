package cn.edu.lyu.ldradiostation.adapters;

import java.util.List;
import java.util.Map;

import cn.edu.lyu.ldradiostation.R;
import cn.edu.lyu.ldradiostation.model.TJRadios;
import cn.edu.lyu.ldradiostation.views.AllMbView;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * listview的适配器
 * 以抛弃
 * @author宋熙明
 * */
public class TJListViewAdapter extends BaseAdapter {
	private List<List<TJRadios>> list = null;
	private Context context = null;

	public TJListViewAdapter(List<List<TJRadios>> list, Context context) {
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub

		ViewHolder viewHolder = null;
		if (view == null) {
			viewHolder = new ViewHolder();
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.mb_allviewmb, null);
			viewHolder.textView = (TextView) view.findViewById(R.id.title_tv);
			viewHolder.allMbView1 = (AllMbView) view.findViewById(R.id.amv1);
			viewHolder.allMbView2 = (AllMbView) view.findViewById(R.id.amv2);
			viewHolder.allMbView3 = (AllMbView) view.findViewById(R.id.amv3);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.textView.setText(list.get(arg0).get(0).getCategory());
		// allMbView1的响应事件
		viewHolder.allMbView1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});
		// allMbView2的响应事件
		viewHolder.allMbView2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});
		// allMbView3的响应事件
		viewHolder.allMbView3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});
		return view;
	}

	static class ViewHolder {
		TextView textView = null;
		AllMbView allMbView1 = null;
		AllMbView allMbView2 = null;
		AllMbView allMbView3 = null;
	}
}
