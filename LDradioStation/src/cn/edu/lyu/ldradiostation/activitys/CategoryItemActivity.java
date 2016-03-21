package cn.edu.lyu.ldradiostation.activitys;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import cn.edu.lyu.ldradiostation.R;
import cn.edu.lyu.ldradiostation.adapters.CategoryItemAdapter;
import cn.edu.lyu.ldradiostation.model.MyMediaPlayer;
import cn.edu.lyu.ldradiostation.model.Paths;
import cn.edu.lyu.ldradiostation.model.Radios;
import cn.edu.lyu.ldradiostation.model.TJRadios;
import cn.edu.lyu.ldradiostation.utils.AddListenNumber;
import cn.edu.lyu.ldradiostation.utils.HistoryRadio;
import cn.edu.lyu.ldradiostation.utils.ListUtils;
import cn.edu.lyu.ldradiostation.utils.Player;
import cn.edu.lyu.ldradiostation.views.CategoryItemView;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * 实现界面每一种类别的详细界面
 * */
public class CategoryItemActivity extends Activity {
	private PullToRefreshScrollView pullToRefreshScrollView;// 声明shScrollView
	private ListView item_lv;// 声明listview
	private int page = 1;// page页
	private HttpUtils httpUtils = null;// HttpUtils
	private CategoryItemAdapter categoryItemAdapter = null;
	private String programId = "";
	private ImageView acd_img = null;// 图片
	private TextView acd_title = null;// 标题文字
	private TextView acd_content = null;// 内容文字
	private BitmapUtils bitmapUtils = null;// 下载图片
	private ImageView acd_down = null;// 退出按钮
	private LinearLayout vp = null;// 头部布局
	private ImageView acd_play_img = null;// 播放按钮的图片

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_detail);
		getWindow().setLayout(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		initView();
		Intent intent = getIntent();
		programId = intent.getStringExtra("category");
		categoryItemAdapter = new CategoryItemAdapter(CategoryItemActivity.this);
		item_lv.setAdapter(categoryItemAdapter);
		viewOnclicks();
		getData(page, programId, 0);
	}

	// 请求所有数据
	public void getData(int page, String programId, int type) {
		getListViewData(page, programId, 0);
		pullToRefreshScrollView.onRefreshComplete();
	}

	/**
	 * 初始化view
	 * */
	private void initView() {
		pullToRefreshScrollView = (PullToRefreshScrollView) findViewById(R.id.categoryscrollView);
		pullToRefreshScrollView
				.setMode(com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.BOTH);
		item_lv = (ListView) findViewById(R.id.item_lv);
		httpUtils = new HttpUtils();
		ILoadingLayout layout = pullToRefreshScrollView.getLoadingLayoutProxy();
		layout.setPullLabel("下拉刷新");
		layout.setRefreshingLabel("正在加载中...");
		layout.setReleaseLabel("放开刷新");
		acd_img = (ImageView) findViewById(R.id.acd_img);
		acd_title = (TextView) findViewById(R.id.acd_title);
		acd_content = (TextView) findViewById(R.id.acd_content);
		bitmapUtils = new BitmapUtils(this);
		vp = (LinearLayout) findViewById(R.id.vp);
		vp.setFocusable(true);
		vp.setFocusableInTouchMode(true);
		vp.requestFocus();
		acd_down = (ImageView) findViewById(R.id.acd_down);
		acd_down.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				CategoryItemActivity.this.finish();
			}
		});
		acd_play_img = (ImageView) findViewById(R.id.acd_play_img);
		acd_play_img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				MyMediaPlayer mediaPlayer = MyMediaPlayer.getInstance();
				Player player = Player.getInstance();
				player.setContext(getApplicationContext());
				if (mediaPlayer.isPlaying()) {
					player.pause();
				} else {
					player.play();
				}
			}
		});
	}

	/**
	 * 响应事件
	 * */
	private void viewOnclicks() {
		pullToRefreshScrollView
				.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
					@Override
					public void onPullDownToRefresh(
							PullToRefreshBase<ScrollView> refreshView) {
						page++;
						getData(page, programId, 0);
					}

					@Override
					public void onPullUpToRefresh(
							PullToRefreshBase<ScrollView> refreshView) {
						page++;
						getData(page, programId, 1);
					}
				});
	}

	// 请求ListView所有数据
	public void getListViewData(int page, String programId, final int type) {

		// 配置当前HttpCache的到期时间
		httpUtils.configCurrentHttpCacheExpiry(33330000);
		// 配置http缓存大小
		httpUtils.configHttpCacheSize(10 * 1024 * 1024);
		// 在请求失败的情况下可以重复发起几次请求
		httpUtils.configRequestRetryCount(3);
		// 配置请求线程池的数量
		httpUtils.configRequestThreadPoolSize(5);
		httpUtils.send(HttpMethod.GET, Paths.CATEGROY_ITEMS_PATH_STRING
				+ "?id=" + programId + "&pageNum=" + page,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// TODO Auto-generated method stub
						try {
							JSONObject object = new JSONObject(
									responseInfo.result);
							JSONArray jsonArray = object.getJSONArray("data");
							setContent(jsonArray.getJSONObject(0).getString(
									"ProgramImg"));// 设置图片
							acd_title.setText(jsonArray.getJSONObject(0)
									.getString("Category"));// 设置标题
							acd_content.setText(jsonArray.getJSONObject(0)
									.getString("Detail"));// 设置内容
							List<Radios> infos = new ArrayList<>();
							for (int i = 0; i < jsonArray.length(); i++) {
								Radios radios = new Radios();
								radios.setCategory(jsonArray.getJSONObject(i)
										.getString("Category"));
								radios.setRadioId(jsonArray.getJSONObject(i)
										.getString("RadioId"));
								radios.setRadioTitle(jsonArray.getJSONObject(i)
										.getString("RadioTitle"));
								radios.setRadioContent(jsonArray.getJSONObject(
										i).getString("RadioContent"));
								radios.setImage(jsonArray.getJSONObject(i)
										.getString("Image"));
								radios.setDate(jsonArray.getJSONObject(i)
										.getString("Date"));
								radios.setRadioAddress(jsonArray.getJSONObject(
										i).getString("RadioAddress"));
								radios.setDeptName(jsonArray.getJSONObject(i)
										.getString("DeptName"));
								radios.setDate1(jsonArray.getJSONObject(i)
										.getString("Date1"));
								radios.setAuthor(jsonArray.getJSONObject(i)
										.getString("Author"));
								radios.setProgramId(jsonArray.getJSONObject(i)
										.getString("ProgramId"));
								radios.setListenNum(jsonArray.getJSONObject(i)
										.getString("ListenNum"));
								radios.setId(jsonArray.getJSONObject(i)
										.getString("Id"));
								radios.setDept(jsonArray.getJSONObject(i)
										.getString("Dept"));
								radios.setDetail(jsonArray.getJSONObject(i)
										.getString("Detail"));
								radios.setProgramImg(jsonArray.getJSONObject(i)
										.getString("ProgramImg"));
								infos.add(radios);
							}
							Log.e("-------------------------",
									jsonArray.length() + "");
							// 方式2:
							categoryItemAdapter.setData(infos, type);
							categoryItemAdapter.notifyDataSetChanged();
							ListUtils.setListViewHeightBasedOnChildren(item_lv);
							listViewOnclick(infos);
							// 方式1:
							// adapter = new MyAdapter(infos,
							// MainActivity.this);
							// // 数据加载
							// pullToRefreshListView.setAdapter(adapter);
							// // 调用刷新数据到列表上
							// adapter.notifyDataSetChanged();
							// // 通知刷新完毕
							// pullToRefreshListView.onRefreshComplete();
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// TODO Auto-generated method stub

					}
				});
	}

	/**
	 * listView的响应事件
	 * */

	private void listViewOnclick(final List<Radios> list) {
		item_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				// 给service发送广播通知播放歌曲
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putSerializable("playProgramRadio", list.get(position));
				intent.putExtras(bundle);
				intent.setAction("myradioreceiver");
				CategoryItemActivity.this.sendBroadcast(intent);
				new AddListenNumber().addNumber(list.get(0).getRadioId(), Paths.ADD_LISTEN_NUMBER);
				// 将数据存储到数据库
				HistoryRadio saveRadio = new HistoryRadio(
						CategoryItemActivity.this, null);
				saveRadio.saveRadio(list.get(position).getRadioId(),
						list.get(position).getRadioTitle(), list.get(position)
								.getRadioContent(), list.get(position)
								.getAuthor(), list.get(position)
								.getRadioAddress());
			}
		});
	}

	private void setContent(String name) {
		bitmapUtils.display(acd_img, Paths.PROGRAM_IMG_PATH + name,
				new BitmapLoadCallBack<ImageView>() {

					@Override
					public void onLoadCompleted(ImageView container,
							String uri, Bitmap bitmap,
							BitmapDisplayConfig config, BitmapLoadFrom from) {
						// TODO Auto-generated method stub
						acd_img.setImageBitmap(bitmap);
					}

					@Override
					public void onLoadFailed(ImageView container, String uri,
							Drawable drawable) {
						// TODO Auto-generated method stub

					}

				});
	}
}
