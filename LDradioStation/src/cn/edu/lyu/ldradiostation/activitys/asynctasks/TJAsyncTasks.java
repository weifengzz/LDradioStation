package cn.edu.lyu.ldradiostation.activitys.asynctasks;

import java.util.List;
import java.util.Map;

import cn.edu.lyu.ldradiostation.adapters.TJListViewAdapter;
import cn.edu.lyu.ldradiostation.model.TJRadios;
import cn.edu.lyu.ldradiostation.utils.HttpUrlConnectionHelper;
import cn.edu.lyu.ldradiostation.utils.RadioUtils;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

/**
 * 本类是推荐页的异步任务类
 * 以抛弃
 * @author宋熙明
 * 
 * */
public class TJAsyncTasks extends AsyncTask<String, Void, List<List<TJRadios>>> {
	private List<List<TJRadios>> list = null;
	private Context context = null;
	private ListView listView = null;

	public TJAsyncTasks(Context context, ListView listView) {
		super();
		this.context = context;
		this.listView = listView;
	}

	@Override
	protected List<List<TJRadios>> doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		String string = HttpUrlConnectionHelper.getTxt(arg0[0]);
		list = RadioUtils.getRadioUtils(string);
		return list;
	}

	@Override
	protected void onPostExecute(List<List<TJRadios>> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		TJListViewAdapter tjListViewAdapter = new TJListViewAdapter(result,
				context);
		listView.setAdapter(tjListViewAdapter);
		listView.setItemsCanFocus(true); 
		
	}
}
