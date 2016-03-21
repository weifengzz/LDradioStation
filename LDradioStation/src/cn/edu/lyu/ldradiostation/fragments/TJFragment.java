package cn.edu.lyu.ldradiostation.fragments;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.squareup.picasso.Picasso;

import cn.edu.lyu.ldradiostation.R;
import cn.edu.lyu.ldradiostation.activitys.asynctasks.TJAsyncTasks;
import cn.edu.lyu.ldradiostation.adapters.TJAdapter;
import cn.edu.lyu.ldradiostation.adapters.TJHeaderPageViewAdapter;
import cn.edu.lyu.ldradiostation.model.Paths;
import cn.edu.lyu.ldradiostation.model.TJRadios;
import cn.edu.lyu.ldradiostation.model.TJTitle;
import cn.edu.lyu.ldradiostation.views.TJHeadViewPager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * 推荐页的fragment
 * 
 * @author宋熙明
 * 
 * */
public class TJFragment extends Fragment {
	private ListView listView = null;// listView
	private HttpUtils httpUtils = null;// HttpUtils
	private TJHeadViewPager tjHeadViewPager = null;// viewwpagerr
	private Thread thread;// 异步
	private List<ImageView> list_dots = null; // 小点的图片
	private boolean state = true;
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0) {
				int pos = (int) msg.obj;
				Log.e("====", "====" + pos);
				if (pos < 2) {
					tjHeadViewPager.setCurrentItem(pos + 1);
				} else {
					tjHeadViewPager.setCurrentItem(0);
				}
			}
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_tj, container, false);
		initView(view);
		httpUtils = new HttpUtils();
		// 请求数据
		getDataList(0);
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (state) {
					try {
						Thread.sleep(8000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Message message = new Message();
					message.what = 0;
					message.obj = tjHeadViewPager.getCurrentItem();
					handler.sendMessage(message);
				}
			}
		});
		return view;
	}

	/**
	 * 初始化viewpager
	 * 
	 * */
	private void initView(View view) {

		listView = (ListView) view.findViewById(R.id.tj_lv);
	}

	/**
	 * 
	 * 获取数据
	 * */
	private void getDataList(int num) {
		// 配置当前HttpCache的到期时间
		httpUtils.configCurrentHttpCacheExpiry(33330000);
		// 配置http缓存大小
		httpUtils.configHttpCacheSize(10 * 1024 * 1024);
		// 在请求失败的情况下可以重复发起几次请求
		httpUtils.configRequestRetryCount(3);
		// 配置请求线程池的数量
		httpUtils.configRequestThreadPoolSize(5);
		httpUtils.send(HttpMethod.GET, Paths.TOP3_PATH,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// TODO Auto-generated method stub
						List<List<TJRadios>> list = getRadioUtils(responseInfo.result);
						listView.setOnItemClickListener(new OnItemClickListener() {
							@Override
							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) {
								view.setBackgroundColor(Color.WHITE);

							}
						});

						final View headervView = View.inflate(getActivity(),
								R.layout.mb_headerview, null);// 头部内容
						initPoint(headervView);
						listView.addHeaderView(headervView);// 添加头部
						listView.setAdapter(new TJAdapter(getActivity(), list));
						list_dots.get(0).setSelected(true);
						final List<TJTitle> tjTitles = new ArrayList<>();
						httpUtils.send(HttpMethod.GET, Paths.TJ_HEAD_PATH,
								new RequestCallBack<String>() {

									@Override
									public void onSuccess(
											ResponseInfo<String> responseInfo) {
										// TODO Auto-generated method stub
										try {
											JSONObject jsonObject = new JSONObject(
													responseInfo.result);
											JSONArray jsonArray = jsonObject
													.getJSONArray("data");
											for (int i = 0; i < jsonArray
													.length(); i++) {
												TJTitle tjTitle = new TJTitle();
												tjTitle.setId(jsonArray
														.getJSONObject(i)
														.getString("id"));
												tjTitle.setSmallIm(jsonArray
														.getJSONObject(i)
														.getString("smallImg"));
												tjTitle.setRadio(jsonArray
														.getJSONObject(i)
														.getString("radio"));
												tjTitle.setBigImg(jsonArray
														.getJSONObject(i)
														.getString("bigImg"));
												tjTitle.setText(jsonArray
														.getJSONObject(i)
														.getString("text"));
												tjTitles.add(tjTitle);
											}
											List<View> list1 = new ArrayList<>();
											for (int i = 0; i < tjTitles.size(); i++) {
												ImageView img = new ImageView(
														getActivity());
												img.setScaleType(ImageView.ScaleType.CENTER_CROP);
												// centerCrop()使用必须设置宽和高
												Picasso.with(getActivity())
														.load(Paths.TJ_HEAD_IMGS
																+ tjTitles
																		.get(i)
																		.getBigImg())
														.placeholder(
																R.drawable.ic_launcher)
														.into(img);
												list1.add(img);
											}
											tjHeadViewPager = (TJHeadViewPager) headervView
													.findViewById(R.id.header_viewpager);
											tjHeadViewPager
													.setAdapter(new TJHeaderPageViewAdapter(
															list1));
											tjHeadViewPager
													.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
														@Override
														public void onPageScrolled(
																int position,
																float positionOffset,
																int positionOffsetPixels) {

														}

														@Override
														public void onPageSelected(
																int position) {
															tjHeadViewPager
																	.setCurrentItem(position);
															for (int i = 0; i < list_dots
																	.size(); i++) {
																list_dots
																		.get(i)
																		.setSelected(
																				false);
															}
															list_dots
																	.get(position)
																	.setSelected(
																			true);
														}

														@Override
														public void onPageScrollStateChanged(
																int state) {

														}
													});

										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									}

									@Override
									public void onFailure(HttpException error,
											String msg) {
										// TODO Auto-generated method stub

									}
								});

						thread.start();
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// TODO Auto-generated method stub

					}
				});
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		state = false;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		state = true;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		state = false;
	}

	/**
	 * 解析数据
	 * */
	public List<List<TJRadios>> getRadioUtils(String string) {
		List<List<TJRadios>> list = new ArrayList<List<TJRadios>>();
		try {
			JSONObject jsonObject = new JSONObject(string);
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			List<TJRadios> list1 = new ArrayList<TJRadios>();
			List<TJRadios> list2 = new ArrayList<TJRadios>();
			List<TJRadios> list3 = new ArrayList<TJRadios>();
			List<TJRadios> list4 = new ArrayList<TJRadios>();
			List<TJRadios> list5 = new ArrayList<TJRadios>();
			List<TJRadios> list6 = new ArrayList<TJRadios>();
			List<TJRadios> list7 = new ArrayList<TJRadios>();
			List<TJRadios> list8 = new ArrayList<TJRadios>();
			List<TJRadios> list9 = new ArrayList<TJRadios>();
			List<TJRadios> list10 = new ArrayList<TJRadios>();
			TJRadios tjRadios = null;
			for (int i = 0; i < jsonArray.length(); i++) {
				tjRadios = new TJRadios();
				tjRadios.setCategory(jsonArray.getJSONObject(i).getString(
						"Category"));
				tjRadios.setRadioId(jsonArray.getJSONObject(i).getString(
						"RadioId"));
				tjRadios.setRadioTitle(jsonArray.getJSONObject(i).getString(
						"RadioTitle"));
				tjRadios.setRadioContent(jsonArray.getJSONObject(i).getString(
						"RadioContent"));
				tjRadios.setImage(jsonArray.getJSONObject(i).getString("Image"));
				tjRadios.setRadioAddress(jsonArray.getJSONObject(i).getString(
						"Radio"));
				tjRadios.setDeptName(jsonArray.getJSONObject(i).getString(
						"DeptName"));
				tjRadios.setDate(jsonArray.getJSONObject(i).getString("Date"));
				tjRadios.setAuthor(jsonArray.getJSONObject(i).getString(
						"Author"));
				tjRadios.setProgramId(jsonArray.getJSONObject(i).getString(
						"ProgramId"));

				if ("书影随行".equals(jsonArray.getJSONObject(i).getString(
						"Category"))) {
					list1.add(tjRadios);

				} else if ("文苑撷英".equals(jsonArray.getJSONObject(i).getString(
						"Category"))) {
					list2.add(tjRadios);

				} else if ("漫游记".equals(jsonArray.getJSONObject(i).getString(
						"Category"))) {
					list3.add(tjRadios);

				} else if ("耳朵去旅行".equals(jsonArray.getJSONObject(i).getString(
						"Category"))) {
					list4.add(tjRadios);

				} else if ("巅峰荣耀".equals(jsonArray.getJSONObject(i).getString(
						"Category"))) {
					list5.add(tjRadios);

				} else if ("天堂电影院".equals(jsonArray.getJSONObject(i).getString(
						"Category"))) {
					list6.add(tjRadios);

				} else if ("杂货铺子".equals(jsonArray.getJSONObject(i).getString(
						"Category"))) {
					list7.add(tjRadios);

				} else if ("灵犀".equals(jsonArray.getJSONObject(i).getString(
						"Category"))) {
					list8.add(tjRadios);

				} else if ("城市稻草人".equals(jsonArray.getJSONObject(i).getString(
						"Category"))) {
					list9.add(tjRadios);

				} else if ("流年爱无边".equals(jsonArray.getJSONObject(i).getString(
						"Category"))) {
					list10.add(tjRadios);
				}

			}

			list.add(list1);
			list.add(list2);
			list.add(list3);
			list.add(list4);
			list.add(list5);
			list.add(list6);
			list.add(list7);
			list.add(list8);
			list.add(list9);
			list.add(list10);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	// 初始化小点
	private void initPoint(View view) {
		list_dots = new ArrayList<ImageView>();
		ImageView imageview1 = (ImageView) view
				.findViewById(R.id.imageView_dots1);
		ImageView imageview2 = (ImageView) view
				.findViewById(R.id.imageView_dots2);
		ImageView imageview3 = (ImageView) view
				.findViewById(R.id.imageView_dots3);
		list_dots.add(imageview1);
		list_dots.add(imageview2);
		list_dots.add(imageview3);
	}

}
