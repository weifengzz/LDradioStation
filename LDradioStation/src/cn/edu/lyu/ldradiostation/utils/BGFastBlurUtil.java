package cn.edu.lyu.ldradiostation.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;

/**
 * 是背景毛玻璃化
 * */
public class BGFastBlurUtil {
	/**
	 * 给背景初始化一张图片
	 * 
	 * */
	public static void initBg(ImageView imageView, Context context, int id) {
		AlphaAnimation alphaAnimation1 = new AlphaAnimation(0.4f, 1.0f);
		alphaAnimation1.setDuration(1000);
		alphaAnimation1.setFillAfter(true);
		AnimationSet animationSet = new AnimationSet(true);
		animationSet.addAnimation(alphaAnimation1);
		Bitmap bitmap = BitmapFactory
				.decodeResource(context.getResources(), id);
		Bitmap bitmap1 = BGImageUtil.fastblur(context, bitmap, 50);
		imageView.setImageBitmap(bitmap1);
		imageView.startAnimation(animationSet);
	}
}
