package cn.edu.lyu.ldradiostation.utils;

import java.util.List;

import cn.edu.lyu.ldradiostation.model.HistoryRadios;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import android.content.Context;

/**
 * 
 * ����Ϣ�洢�����ݿ�
 * */
public class HistoryRadio {
	private DbUtils dbUtils;// xutils �洢���ݿ�
	private PullToRefreshListView pullToRefreshListView = null;

	public HistoryRadio(Context context,
			PullToRefreshListView pullToRefreshListView) {
		// TODO Auto-generated constructor stub
		dbUtils = DbUtils.create(context);
		this.pullToRefreshListView = pullToRefreshListView;
	}

	// �洢����
	public void saveRadio(String radioId, String radioTitle,
			String radioContent, String author, String radioAddress) {
		HistoryRadios historyRadios = new HistoryRadios();
		historyRadios.setId(1);
		historyRadios.setRadioId(radioId);
		historyRadios.setRadioContent(radioContent);
		historyRadios.setAuthor(author);
		historyRadios.setRadioTitle(radioTitle);
		historyRadios.setRadioAddress(radioAddress);
		try {
			dbUtils.save(historyRadios);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ��ȡ����
	public List<HistoryRadios> getRadio(int page) {
		List<HistoryRadios> list = null;
		try {
			dbUtils.findAll(Selector.from(HistoryRadios.class));
			list = dbUtils.findAll(Selector.from(HistoryRadios.class)
					.offset(10 * (page - 1)).limit(10 * (page)));
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pullToRefreshListView.onRefreshComplete();
		return list;
	}


}
