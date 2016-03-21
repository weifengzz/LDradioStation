package cn.edu.lyu.ldradiostation.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;

public class SaveFile {
	/**
	 * 保存头像到本地
	 * */
	public static void saveHeadimg(Bitmap bitmap,File filepath) {

		File myCaptureFile = filepath;
		BufferedOutputStream bos;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
			bos.flush();
			bos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
