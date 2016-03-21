package cn.edu.lyu.ldradiostation.activitys;

import cn.edu.lyu.ldradiostation.R;
import cn.edu.lyu.ldradiostation.adapters.DetailViewPageAdapter;
import cn.edu.lyu.ldradiostation.utils.BGFastBlurUtil;
import cn.edu.lyu.ldradiostation.utils.GetWidthHeightUtil;
import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 播放详情页面
 * 
 * @author宋熙明
 * */
public class PlayDetailActivity extends FragmentActivity {
	private ImageView ivbg = null;// 界面背景
	private ViewPager viewPager = null;// viewpager
	private ImageView detaildown = null;// 关闭图片
	private TextView detailtv = null;// 标题文字
	private ImageView detailmore = null;// 更多图片
	private LinearLayout detailll = null;// 底部布局
	private RelativeLayout detailtrl = null;// 头部布局
	private DetailViewPageAdapter detailViewPageAdapter = null;// 中间ViewPager适配器
	private ImageView detailPlayCircle = null;//播放的旋转图片
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.playdetail_activity);
		initView();
		// 设置毛玻璃
		BGFastBlurUtil.initBg(ivbg, this, R.drawable.play_mrbg);
		initViewPagerHeight();
		initAdapter();
		// 去除屏幕两边的间隙
		getWindow().setLayout(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		// 添加适配器
		viewPager.setAdapter(detailViewPageAdapter);
		viewPager.setCurrentItem(1);
		//播放图片的旋转
		detailPlayCircleScroll();

	}

	/**
	 * 初始化view
	 * */
	private void initView() {
		ivbg = (ImageView) findViewById(R.id.ivbg);
		viewPager = (ViewPager) findViewById(R.id.detail_vp);
		detaildown = (ImageView) findViewById(R.id.detail_down);
		detailmore = (ImageView) findViewById(R.id.detail_more);
		detailll = (LinearLayout) findViewById(R.id.detail_ll);
		detailtv = (TextView) findViewById(R.id.detail_tv);
		detailtrl = (RelativeLayout) findViewById(R.id.detail_trl);
		detailPlayCircle = (ImageView) findViewById(R.id.detail_play_circle);
	}

	/**
	 * 初始化适配器
	 * */
	private void initAdapter() {
		detailViewPageAdapter = new DetailViewPageAdapter(
				getSupportFragmentManager());
	}

	/**
	 * 设置布局
	 * */
	private void initViewPagerHeight() {
		DisplayMetrics dm = GetWidthHeightUtil.getDisplayMetrics(this);
		// 得到屏幕的高度
		int h = dm.heightPixels;
		// 设置viewpager的高度
		RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, (int) (h * 0.1f + 0.5f));
		RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, (int) (h * 0.7f + 0.5f));
		RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, (int) (h * 0.2f + 0.5f));
		params2.setMargins(0, (int) (h * 0.1f + 0.5f), 0, 0);
		params3.setMargins(0, (int) (h * 0.8f + 0.5f), 0, 0);
		detailtrl.setLayoutParams(params1);
		viewPager.setLayoutParams(params2);
		detailll.setLayoutParams(params3);

	}

	/**
	 * 点击头部down按钮响应的事件
	 * */
	public void detailDown(View view) {
		this.finish();
	}
	/**
	 * 播放图片的旋转
	 * */
	private void detailPlayCircleScroll(){
		Animation operatingAnim = AnimationUtils
				.loadAnimation(this, R.anim.tip);
		LinearInterpolator lin = new LinearInterpolator();
		operatingAnim.setInterpolator(lin);
		detailPlayCircle.startAnimation(operatingAnim);
	}
}
