package cn.edu.lyu.ldradiostation.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 获取数据的工具类
 * 
 * @author宋熙明
 * */
public class HttpUrlConnectionHelper {
	/**
	 * 获取文本
	 * 
	 * */
	public static String getTxt(String string) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			URL url = new URL(string);
			HttpURLConnection huc = (HttpURLConnection) url.openConnection();
			huc.setConnectTimeout(5000);
			huc.setDoInput(true);
			huc.setRequestMethod("GET");
			huc.connect();
			if (huc.getResponseCode() == 200) {
				InputStream inputStream = huc.getInputStream();
				int len = 0;
				byte[] bt = new byte[1024];
				while ((len = inputStream.read(bt)) != -1) {
					baos.write(bt, 0, len);
					baos.flush();
				}
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return baos.toString();
	}
}
