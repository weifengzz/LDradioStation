package cn.edu.lyu.ldradiostation.activitys;

import cn.edu.lyu.ldradiostation.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.widget.ImageView;

/**
 * 打开应用之前的欢迎界面
 * 
 * @author 宋熙明
 * 
 */
public class WelcomeActivity extends Activity {
	private ImageView imageView = null;// 欢迎图片

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		initView();
		show();
	}

	/**
	 * 初始化欢迎图片
	 */

	private void initView() {
		imageView = (ImageView) findViewById(R.id.welcome_img);
	}

	/**
	 * 为图片设置动画
	 */
	private void show() {
		// 透明度
		AlphaAnimation alphaAnimation1 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation1.setDuration(20);
		alphaAnimation1.setFillAfter(true);

		AnimationSet animationSet = new AnimationSet(true);
		animationSet.addAnimation(alphaAnimation1);

		animationSet.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationEnd(Animation arg0) {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				AlphaAnimation alphaAnimation2 = new AlphaAnimation(1.0f, 0.0f);
				alphaAnimation2.setDuration(20);
				alphaAnimation2.setFillAfter(true);
				AnimationSet animationSet1 = new AnimationSet(true);
				animationSet1.addAnimation(alphaAnimation2);
				imageView.startAnimation(animationSet1);
				animationSet1.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationEnd(Animation arg0) {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Intent intent = new Intent(WelcomeActivity.this,
								MainActivity.class);
						startActivity(intent);
						WelcomeActivity.this.finish();
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationStart(Animation arg0) {
						// TODO Auto-generated method stub

					}

				});
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub

			}

		});
		imageView.startAnimation(animationSet);

	}
}
