package cn.edu.lyu.ldradiostation.adapters;

import java.util.List;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.view.annotation.event.OnTouch;

import cn.edu.lyu.ldradiostation.R;
import cn.edu.lyu.ldradiostation.activitys.CategoryItemActivity;
import cn.edu.lyu.ldradiostation.model.MyMediaPlayer;
import cn.edu.lyu.ldradiostation.model.Paths;
import cn.edu.lyu.ldradiostation.model.TJRadios;
import cn.edu.lyu.ldradiostation.utils.AddListenNumber;
import cn.edu.lyu.ldradiostation.utils.Player;
import cn.edu.lyu.ldradiostation.utils.HistoryRadio;
import cn.edu.lyu.ldradiostation.views.AllMbView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TJAdapter extends BaseAdapter {
	Context ctx;
	List<List<TJRadios>> tjRadios;
	BitmapUtils bitmapUtils1;
	BitmapUtils bitmapUtils2;
	BitmapUtils bitmapUtils3;

	public TJAdapter(Context ctx, List<List<TJRadios>> tjRadios) {
		this.ctx = ctx;
		this.tjRadios = tjRadios;
		bitmapUtils1 = new BitmapUtils(ctx);
		bitmapUtils2 = new BitmapUtils(ctx);
		bitmapUtils3 = new BitmapUtils(ctx);
		// 开始下载到下载完成之间显示的图片
		bitmapUtils1.configDefaultLoadingImage(R.drawable.loading);
		bitmapUtils2.configDefaultLoadingImage(R.drawable.loading);
		bitmapUtils3.configDefaultLoadingImage(R.drawable.loading);
	}

	@Override
	public int getCount() {
		return tjRadios.size();
	}

	@Override
	public Object getItem(int position) {
		return tjRadios.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;
		if (convertView == null) {
			convertView = LayoutInflater.from(ctx).inflate(
					R.layout.mb_allviewmb, null);
			viewHolder = new ViewHolder();
			viewHolder.allMbView1 = (AllMbView) convertView
					.findViewById(R.id.amv1);
			viewHolder.allMbView2 = (AllMbView) convertView
					.findViewById(R.id.amv2);
			viewHolder.allMbView3 = (AllMbView) convertView
					.findViewById(R.id.amv3);
			viewHolder.titleText = (TextView) convertView
					.findViewById(R.id.title_tv);
			viewHolder.moreTextView = (TextView) convertView
					.findViewById(R.id.more_tv);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.titleText.setText(tjRadios.get(position).get(0)
				.getCategory());
		final String url1 = tjRadios.get(position).get(0).getImage();
		final String url2 = tjRadios.get(position).get(1).getImage();
		final String url3 = tjRadios.get(position).get(2).getImage();
		viewHolder.allMbView1.setTag(url1);
		viewHolder.allMbView2.setTag(url2);
		viewHolder.allMbView3.setTag(url3);
		viewHolder.allMbView1.setText(tjRadios.get(position).get(0)
				.getRadioTitle());
		viewHolder.allMbView2.setText(tjRadios.get(position).get(1)
				.getRadioTitle());
		viewHolder.allMbView3.setText(tjRadios.get(position).get(2)
				.getRadioTitle());

		bitmapUtils1.display(viewHolder.allMbView1, url1,
				new BitmapLoadCallBack<AllMbView>() {

					@Override
					public void onLoadCompleted(AllMbView container,
							String uri, Bitmap bitmap,
							BitmapDisplayConfig config, BitmapLoadFrom from) {
						viewHolder.allMbView1.setImg(bitmap);
					}

					@Override
					public void onLoadFailed(AllMbView container, String uri,
							Drawable drawable) {
						// TODO Auto-generated method stub

					}

				});

		bitmapUtils2.display(viewHolder.allMbView2, url2,
				new BitmapLoadCallBack<AllMbView>() {

					@Override
					public void onLoadCompleted(AllMbView container,
							String uri, Bitmap bitmap,
							BitmapDisplayConfig config, BitmapLoadFrom from) {
						// TODO Auto-generated method stub
						viewHolder.allMbView2.setImg(bitmap);
					}

					@Override
					public void onLoadFailed(AllMbView container, String uri,
							Drawable drawable) {
						// TODO Auto-generated method stub

					}

				});
		bitmapUtils3.display(viewHolder.allMbView3, url2,
				new BitmapLoadCallBack<AllMbView>() {

					@Override
					public void onLoadCompleted(AllMbView container,
							String uri, Bitmap bitmap,
							BitmapDisplayConfig config, BitmapLoadFrom from) {
						// TODO Auto-generated method stub
						viewHolder.allMbView3.setImg(bitmap);
					}

					@Override
					public void onLoadFailed(AllMbView container, String uri,
							Drawable drawable) {
						// TODO Auto-generated method stub

					}

				});

		viewHolder.allMbView1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putSerializable("playRadios", tjRadios.get(position)
						.get(0));
				intent.putExtras(bundle);
				intent.setAction("myradioreceiver");
				ctx.sendBroadcast(intent);
				// 将数据存储到数据库
				HistoryRadio saveRadio = new HistoryRadio(ctx, null);
				saveRadio.saveRadio(tjRadios.get(position).get(0).getRadioId(),
						tjRadios.get(position).get(0).getRadioTitle(), tjRadios
								.get(position).get(0).getRadioContent(),
						tjRadios.get(position).get(0).getAuthor(), tjRadios
								.get(position).get(0).getRadioAddress());
				// 添加收听数量
				new AddListenNumber().addNumber(tjRadios.get(position).get(0)
						.getRadioId(), Paths.ADD_LISTEN_NUMBER);
			}
		});
		viewHolder.allMbView2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putSerializable("playRadios", tjRadios.get(position)
						.get(1));
				intent.putExtras(bundle);
				intent.setAction("myradioreceiver");
				ctx.sendBroadcast(intent);
				// 将数据存储到数据库
				HistoryRadio saveRadio = new HistoryRadio(ctx, null);
				saveRadio.saveRadio(tjRadios.get(position).get(1).getRadioId(),
						tjRadios.get(position).get(1).getRadioTitle(), tjRadios
								.get(position).get(1).getRadioContent(),
						tjRadios.get(position).get(1).getAuthor(), tjRadios
								.get(position).get(1).getRadioAddress());
				// 添加收听数量
				new AddListenNumber().addNumber(tjRadios.get(position).get(0)
						.getRadioId(), Paths.ADD_LISTEN_NUMBER);
			}
		});
		viewHolder.allMbView3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putSerializable("playRadios", tjRadios.get(position)
						.get(2));
				intent.putExtras(bundle);
				intent.setAction("myradioreceiver");
				ctx.sendBroadcast(intent);

				// 将数据存储到数据库
				HistoryRadio saveRadio = new HistoryRadio(ctx, null);
				saveRadio.saveRadio(tjRadios.get(position).get(2).getRadioId(),
						tjRadios.get(position).get(2).getRadioTitle(), tjRadios
								.get(position).get(2).getRadioContent(),
						tjRadios.get(position).get(2).getAuthor(), tjRadios
								.get(position).get(2).getRadioAddress());
				// 添加收听数量
				new AddListenNumber().addNumber(tjRadios.get(position).get(0)
						.getRadioId(), Paths.ADD_LISTEN_NUMBER);
			}
		});
		viewHolder.moreTextView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ctx, CategoryItemActivity.class);
				intent.putExtra("category", tjRadios.get(position).get(0)
						.getProgramId());
				ctx.startActivity(intent);
			}
		});
		return convertView;
	}

	class ViewHolder {
		TextView titleText;
		AllMbView allMbView1;
		AllMbView allMbView2;
		AllMbView allMbView3;
		TextView moreTextView;
	}

}
