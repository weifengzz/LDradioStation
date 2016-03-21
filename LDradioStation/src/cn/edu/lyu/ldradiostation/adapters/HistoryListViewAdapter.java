package cn.edu.lyu.ldradiostation.adapters;

import java.util.ArrayList;
import java.util.List;

import cn.edu.lyu.ldradiostation.R;
import cn.edu.lyu.ldradiostation.model.HistoryRadios;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HistoryListViewAdapter extends BaseAdapter {
	List<HistoryRadios> infos = new ArrayList<>();
	Context ctx;

	public HistoryListViewAdapter(Context ctx) {
		this.ctx = ctx;
	}

	public void setData(List<HistoryRadios> infos1, int type) {
		if (type == 0) {
			// 下拉，数据追加到顶部
			infos.addAll(0, infos1);
		} else {
			// 上拉，数据追加到低部
			infos.addAll(infos1);
		}

	}

	@Override
	public int getCount() {
		return infos.size();
	}

	@Override
	public Object getItem(int position) {
		return infos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder vh;
		if (convertView == null) {
			convertView = LayoutInflater.from(ctx).inflate(R.layout.mb_history,
					null);
			vh = new ViewHolder();
			vh.textView1 = (TextView) convertView
					.findViewById(R.id.history_tv1);
			vh.textView2 = (TextView) convertView
					.findViewById(R.id.history_tv2);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		vh.textView1.setText(infos.get(position).getRadioTitle());
		vh.textView2.setText(infos.get(position).getAuthor());
		return convertView;
	}

	class ViewHolder {
		TextView textView1;
		TextView textView2;
	}
}
