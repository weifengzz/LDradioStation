package cn.edu.lyu.ldradiostation.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

public class GetWidthHeightUtil {
	public static DisplayMetrics getDisplayMetrics(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm;
	}
}
