package cn.edu.lyu.ldradiostation.activitys;

import java.util.List;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import cn.edu.lyu.ldradiostation.R;
import cn.edu.lyu.ldradiostation.adapters.HistoryListViewAdapter;
import cn.edu.lyu.ldradiostation.model.HistoryRadios;
import cn.edu.lyu.ldradiostation.model.Paths;
import cn.edu.lyu.ldradiostation.utils.AddListenNumber;
import cn.edu.lyu.ldradiostation.utils.HistoryRadio;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class HistoryActivity extends Activity {
	private RelativeLayout layout;
	private int page = 1;
	private HistoryListViewAdapter historyListViewAdapter = null;
	private PullToRefreshListView pullToRefreshListView;
	private List<HistoryRadios> list = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		getWindow().setLayout(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);

		layout = (RelativeLayout) findViewById(R.id.pop_layout);
		// 添加选择窗口范围监听可以优先获取触点，即不再执行onTouchEvent()函数，点击其他地方时执行onTouchEvent()函数销毁Activity
		layout.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！",
						Toast.LENGTH_SHORT).show();
			}
		});

		pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.history_list);
		// 设置刷新模式(下拉刷新)
		pullToRefreshListView
				.setMode(com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.PULL_FROM_END);
		// 获取刷新加载的布局
		ILoadingLayout layout = pullToRefreshListView.getLoadingLayoutProxy();
		// 获取内容体中addView所添加的控件
		ListView listView = pullToRefreshListView.getRefreshableView();
		View view = LayoutInflater.from(HistoryActivity.this).inflate(
				R.layout.footer_layout, null);
		layout.setRefreshingLabel("正在加载中...");
		listView.addFooterView(view);
		// 设置刷新事件(单个模式刷新监听)
		pullToRefreshListView
				.setOnRefreshListener(new com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener<ListView>() {
					@Override
					public void onRefresh(
							PullToRefreshBase<ListView> refreshView) {
						// 下拉刷新的操作处理
						page++;
						getData(page);
						pullToRefreshListView.onRefreshComplete();
					}
				});
		pullToRefreshListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						if (position >= 1) {
							HistoryRadios info = (HistoryRadios) historyListViewAdapter
									.getItem(position - 1);
							Intent intent = new Intent();
							Bundle bundle = new Bundle();
							bundle.putSerializable("playHistoryRadios",
									list.get(position - 1));
							intent.putExtras(bundle);
							intent.setAction("myradioreceiver");
							HistoryActivity.this.sendBroadcast(intent);
							new AddListenNumber().addNumber(info.getRadioId(), Paths.ADD_LISTEN_NUMBER);
						} else {
							Intent intent = new Intent();
							Bundle bundle = new Bundle();
							bundle.putSerializable("playHistoryRadios",
									list.get(0));
							intent.putExtras(bundle);
							intent.setAction("myradioreceiver");
							HistoryActivity.this.sendBroadcast(intent);
							new AddListenNumber().addNumber(list.get(0).getRadioId(), Paths.ADD_LISTEN_NUMBER);
						}

					}
				});
		// 设置空的数据列表
		historyListViewAdapter = new HistoryListViewAdapter(
				HistoryActivity.this);
		pullToRefreshListView.setAdapter(historyListViewAdapter);
		// 默认进入加载第一页
		getData(page);
	}

	/**
	 * 实现onTouchEvent触屏函数但点击屏幕时销毁本Activity
	 * */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}

	/**
	 * 取消按钮
	 * */
	public void canclehistory(View view) {
		this.finish();
	}

	/**
	 * 获取数据
	 * */
	public void getData(int page) {
		list = new HistoryRadio(this, pullToRefreshListView).getRadio(page);
		if (list != null) {
			historyListViewAdapter.setData(list, 1);
			historyListViewAdapter.notifyDataSetChanged();
		}
	}
}
