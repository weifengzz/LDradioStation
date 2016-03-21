package cn.edu.lyu.ldradiostation.utils;

import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * 
 * 更新数据，每收听一首歌收听次数加1
 * */
public class AddListenNumber {
	private HttpUtils httpUtils = null;

	public AddListenNumber() {
		// TODO Auto-generated constructor stubHtt
		httpUtils = new HttpUtils();
	}

	public void addNumber(String radioId, String url) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("RadioId", radioId);
		httpUtils.send(HttpMethod.POST, url, params,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// TODO Auto-generated method stub
					}

				});
	}
}
