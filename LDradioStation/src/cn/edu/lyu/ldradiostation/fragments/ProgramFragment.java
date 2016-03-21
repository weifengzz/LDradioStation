package cn.edu.lyu.ldradiostation.fragments;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import cn.edu.lyu.ldradiostation.R;
import cn.edu.lyu.ldradiostation.activitys.CategoryItemActivity;
import cn.edu.lyu.ldradiostation.adapters.ProgramAdapter;
import cn.edu.lyu.ldradiostation.model.Paths;
import cn.edu.lyu.ldradiostation.model.Radios;
import cn.edu.lyu.ldradiostation.utils.AddListenNumber;
import cn.edu.lyu.ldradiostation.utils.GetWidthHeightUtil;
import cn.edu.lyu.ldradiostation.utils.HistoryRadio;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ProgramFragment extends Fragment {
	private LinearLayout category_ll = null;// 头部类别
	private ListView listView = null;// 列表
	private HttpUtils httpUtils = null;
	private TextView tv1 = null;
	private TextView tv2 = null;
	private TextView tv3 = null;
	private TextView tv4 = null;
	private TextView tv5 = null;
	private TextView tv6 = null;
	private TextView tv7 = null;
	private TextView tv8 = null;
	private TextView tv9 = null;
	private TextView tv10 = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_program, container,
				false);
		initView(view);
		initViewPagerHeight();

		return view;
	}

	/**
	 * 初始化
	 * 
	 * */
	public void initView(View view) {
		category_ll = (LinearLayout) view.findViewById(R.id.category_ll);
		listView = (ListView) view.findViewById(R.id.program_lv);
		httpUtils = new HttpUtils();
		tv1 = (TextView) view.findViewById(R.id.tv1);
		tv2 = (TextView) view.findViewById(R.id.tv2);
		tv3 = (TextView) view.findViewById(R.id.tv3);
		tv4 = (TextView) view.findViewById(R.id.tv4);
		tv5 = (TextView) view.findViewById(R.id.tv5);
		tv6 = (TextView) view.findViewById(R.id.tv6);
		tv7 = (TextView) view.findViewById(R.id.tv7);
		tv8 = (TextView) view.findViewById(R.id.tv8);
		tv9 = (TextView) view.findViewById(R.id.tv9);
		tv10 = (TextView) view.findViewById(R.id.tv10);
		onclickItem(tv1, "5");
		onclickItem(tv2, "1");
		onclickItem(tv3, "2");
		onclickItem(tv4, "4");
		onclickItem(tv5, "6");
		onclickItem(tv6, "7");
		onclickItem(tv7, "9");
		onclickItem(tv8, "10");
		onclickItem(tv9, "12");
		onclickItem(tv10, "13");
		getProgramList("5");
		
	}

	/**
	 * 设置布局
	 * */
	private void initViewPagerHeight() {
		DisplayMetrics dm = GetWidthHeightUtil.getDisplayMetrics(getActivity());
		// 得到屏幕的高度
		int h = dm.heightPixels;
		// 设置viewpager的高度
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, (int) (h * 0.3f + 0.5f));
		params.setMargins(4, 4, 4, 4);
		category_ll.setLayoutParams(params);

	}

	/**
	 * 获取数据
	 * */
	private void getProgramList(String id) {
		Log.e("======================", Paths.PROGRAM_CATEGORY_BY_ID);
		RequestParams params = new RequestParams();
		params.addBodyParameter("id", id);
		httpUtils.send(HttpRequest.HttpMethod.POST,
				Paths.PROGRAM_CATEGORY_BY_ID, params,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// TODO Auto-generated method stub

						JSONObject object;
						try {
							object = new JSONObject(responseInfo.result);
							JSONArray jsonArray = object.getJSONArray("data");

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
							listView.setAdapter(new ProgramAdapter(
									getActivity(), infos));
							listViewOnclick(infos);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// TODO Auto-generated method stub

					}
				});
	}

	private void onclickItem(TextView view, final String id) {
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				getProgramList(id);
			}
		});

	}

	/**
	 * listView的响应事件
	 * */

	private void listViewOnclick(final List<Radios> list) {
		listView.setOnItemClickListener(new OnItemClickListener() {

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
				getActivity().sendBroadcast(intent);
				new AddListenNumber().addNumber(list.get(0).getRadioId(),
						Paths.ADD_LISTEN_NUMBER);
				// 将数据存储到数据库
				HistoryRadio saveRadio = new HistoryRadio(getActivity(), null);
				saveRadio.saveRadio(list.get(position).getRadioId(),
						list.get(position).getRadioTitle(), list.get(position)
								.getRadioContent(), list.get(position)
								.getAuthor(), list.get(position)
								.getRadioAddress());
			}
		});
	}

}
