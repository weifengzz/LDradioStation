package cn.edu.lyu.ldradiostation.adapters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.lyu.ldradiostation.R;
import cn.edu.lyu.ldradiostation.adapters.CategoryItemAdapter.ViewHolder;
import cn.edu.lyu.ldradiostation.model.Paths;
import cn.edu.lyu.ldradiostation.model.Radios;
import cn.edu.lyu.ldradiostation.views.CategoryItemView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ProgramAdapter extends BaseAdapter {

	private Context context;
	private List<Radios> Radios = null;
	private BitmapUtils bitmapUtils;

	public ProgramAdapter(Context context, List<Radios> Radios) {
		super();
		this.context = context;
		this.Radios = Radios;
		bitmapUtils = new BitmapUtils(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Radios.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return Radios.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		final ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.mb_media_item, null);
			viewHolder.categoryItemView = (CategoryItemView) convertView
					.findViewById(R.id.mb_civ);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.categoryItemView.setTitleText(Radios.get(arg0)
				.getRadioTitle());
		viewHolder.categoryItemView.setAuthorText(Radios.get(arg0).getAuthor());
		long days = Math.abs(betwwenDays(Radios.get(arg0).getDate1()));
		if (days == 0) {
			viewHolder.categoryItemView.setClockText("今天");
		} else if (days / 30 > 0) {
			long month = days / 30;
			viewHolder.categoryItemView.setClockText(month + "月前");
		} else if (days / 30 > 11) {
			long year = days / 30;
			viewHolder.categoryItemView.setClockText(year + "年前");
		} else {
			viewHolder.categoryItemView.setClockText(days + "天前");
		}
		viewHolder.categoryItemView.setNumText(Radios.get(arg0).getListenNum());

		bitmapUtils.display(viewHolder.categoryItemView, Paths.RADIO_IMG_PATH
				+ Radios.get(arg0).getImage(),
				new BitmapLoadCallBack<CategoryItemView>() {

					@Override
					public void onLoadCompleted(CategoryItemView container,
							String uri, Bitmap bitmap,
							BitmapDisplayConfig config, BitmapLoadFrom from) {
						// TODO Auto-generated method stub
						viewHolder.categoryItemView.setImg(bitmap);
					}

					@Override
					public void onLoadFailed(CategoryItemView container,
							String uri, Drawable drawable) {
						// TODO Auto-generated method stub

					}

				});

		return convertView;
	}

	class ViewHolder {
		CategoryItemView categoryItemView;
	}

	/**
	 * 计两个时间之间的天数
	 * */
	private long betwwenDays(String day1) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		long to = 0;
		try {
			to = df.parse(day1).getTime();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Date dt = new Date();
		String day2 = df.format(dt);
		long from = 0;
		try {
			from = df.parse(day2).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (to - from) / (1000 * 60 * 60 * 24);
	}
}
