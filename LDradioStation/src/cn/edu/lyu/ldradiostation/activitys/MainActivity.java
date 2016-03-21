package cn.edu.lyu.ldradiostation.activitys;

import java.io.File;
import java.util.List;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import cn.edu.lyu.ldradiostation.R;
import cn.edu.lyu.ldradiostation.adapters.MainViewPageAdapter;
import cn.edu.lyu.ldradiostation.listeners.HorizontalScrollViewOnClick;
import cn.edu.lyu.ldradiostation.listeners.MainViewPageChangeListener;
import cn.edu.lyu.ldradiostation.model.HistoryRadios;
import cn.edu.lyu.ldradiostation.model.MyMediaPlayer;
import cn.edu.lyu.ldradiostation.model.Paths;
import cn.edu.lyu.ldradiostation.utils.HistoryRadio;
import cn.edu.lyu.ldradiostation.utils.Player;
import cn.edu.lyu.ldradiostation.utils.SDCardHelper;
import cn.edu.lyu.ldradiostation.utils.SaveFile;
import cn.edu.lyu.ldradiostation.views.NavigationView;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * 主界面
 * 
 * @author宋熙明
 * */
public class MainActivity extends FragmentActivity {
	private ImageView cirImg = null;// 播放旋转的图片
	private ViewPager viewPager = null;// viewwpagerr
	private HorizontalScrollView horizontalScrollView = null;// 滚动标题栏
	private LinearLayout hsvLayout = null;// HorizontalScrollView中的布局
	private DrawerLayout drawerLayout = null;// 抽屉控件
	private ImageView imageView = null;// 头像
	private File filepath;// 保存头像的文件
	private RadioRecevier radioRecevier;// 广播 开始
	private RadioRecevierStop radioRecevierStop;// 广播停止
	private ImageView play_start;// 开始图标
	private ImageView play_pause;// 暂停图标
	private Intent intentservice = null;// 广播
	private ImageView play_pre = null;// 上一曲
	private ImageView play_next = null;// 下一曲
	private List<HistoryRadios> play_list = null;// 歌曲列表
	private static int num = 0;// 记录当前播放到哪一曲
	private DbUtils dbUtils;// 数据库工具

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		initView();
		playImg();
		initPath();
		File file = new File(getFilesDir(), "head.jpg");
		// 如果图片存在就获取图片
		if (file.exists()) {
			byte[] bt = SDCardHelper.loadFileFromSDCard(file);
			Bitmap bitmap = BitmapFactory.decodeByteArray(bt, 0, bt.length);
			imageView.setImageBitmap(bitmap);
		}
		MainViewPageAdapter mainViewPageAdapter = new MainViewPageAdapter(
				getSupportFragmentManager());
		viewPager.setAdapter(mainViewPageAdapter);
		// 给viewpager添加响应事件
		viewPager.setOnPageChangeListener(new MainViewPageChangeListener(
				horizontalScrollView, hsvLayout, drawerLayout));
		// 默认是第一个头条选中
		((NavigationView) (hsvLayout.getChildAt(0))).setFlag(true);
		// 给HorizontalScrollView的每一个子控件添加点击事件
		for (int i = 0; i < hsvLayout.getChildCount(); i++) {
			((NavigationView) (hsvLayout.getChildAt(i)))
					.setOnClickListener(new HorizontalScrollViewOnClick(
							hsvLayout, viewPager, drawerLayout));
		}
		registerBroadCast();// 注册开始的广播
		registerBroadCastStop();// 注册停止的广播
		// 开启服务
		if (!isServiceRunning(
				"cn.edu.lyu.ldradiostation.services.RadioPlayService", this)) {
			Log.e("ddddd", "heheh");
			intentservice = new Intent(
					"cn.edu.lyu.ldradiostation.services.RadioPlayService");
			this.startService(intentservice);
		}
		// 判断第一次进来歌曲的播放状态
		MyMediaPlayer mediaPlayer = MyMediaPlayer.getInstance();
		Player player = Player.getInstance();
		player.setContext(this);
		if (mediaPlayer.isPlaying()) {
			play_pause.setVisibility(View.VISIBLE);
			play_start.setVisibility(View.INVISIBLE);
		} else {
			player.pause();
		}
	}

	/**
	 * 判断服务的状态
	 * */
	public static boolean isServiceRunning(String servicename, Context context) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningServiceInfo> infos = am.getRunningServices(100);
		for (RunningServiceInfo info : infos) {
			if (servicename.equals(info.service.getClassName())) {
				return true;
			}
		}
		return false;
	}

	// 读取所有数据
	public List<HistoryRadios> getAllRadio() {
		List<HistoryRadios> list = null;
		try {
			dbUtils.findAll(Selector.from(HistoryRadios.class));
			list = dbUtils.findAll(Selector.from(HistoryRadios.class));
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 初始化view
	 * 
	 * */
	private void initView() {
		dbUtils = DbUtils.create(this);
		cirImg = (ImageView) findViewById(R.id.cir_img);
		horizontalScrollView = (HorizontalScrollView) findViewById(R.id.hsv);
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		hsvLayout = (LinearLayout) findViewById(R.id.ll);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		imageView = (ImageView) findViewById(R.id.login_lv);
		play_start = (ImageView) findViewById(R.id.play_start);
		play_pause = (ImageView) findViewById(R.id.play_pause);
		cirImg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				MyMediaPlayer mediaPlayer = MyMediaPlayer.getInstance();
				Player player = Player.getInstance();
				player.setContext(MainActivity.this);
				if (mediaPlayer.isPlaying()) {
					player.pause();
					play_pause.setVisibility(View.INVISIBLE);
					play_start.setVisibility(View.VISIBLE);
				} else {
					player.play();
					play_pause.setVisibility(View.VISIBLE);
					play_start.setVisibility(View.INVISIBLE);
				}
			}
		});
		play_next = (ImageView) findViewById(R.id.play_next);
		play_next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				play_list = getAllRadio();
				if (num == play_list.size() - 1) {
					Toast.makeText(MainActivity.this, "已经是最后一首",
							Toast.LENGTH_SHORT).show();
				} else {
					Intent intent = new Intent();
					Bundle bundle = new Bundle();
					bundle.putSerializable("playHistoryRadios",
							play_list.get(num++));
					intent.putExtras(bundle);
					intent.setAction("myradioreceiver");
					MainActivity.this.sendBroadcast(intent);
				}

			}
		});
		play_pre = (ImageView) findViewById(R.id.play_pre);
		play_pre.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				play_list = getAllRadio();
				// 如果播放到第一首则不能再播放上一曲
				if (num == 0) {
					Toast.makeText(MainActivity.this, "已经是第一首",
							Toast.LENGTH_SHORT).show();
				} else {
					Intent intent = new Intent();
					Bundle bundle = new Bundle();
					bundle.putSerializable("playHistoryRadios",
							play_list.get(num--));
					intent.putExtras(bundle);
					intent.setAction("myradioreceiver");
					MainActivity.this.sendBroadcast(intent);
				}
			}
		});
	}

	/**
	 * 初始化path
	 * */
	private void initPath() {
		filepath = new File(getFilesDir(), "head.jpg");
	}

	/**
	 * 当点击某一个按钮实现播放界面的旋转
	 * 
	 * */
	private void playImg() {
		Animation operatingAnim = AnimationUtils
				.loadAnimation(this, R.anim.tip);
		LinearInterpolator lin = new LinearInterpolator();
		operatingAnim.setInterpolator(lin);
		cirImg.startAnimation(operatingAnim);
	}

	/**
	 * 点击底部的图标相应事件
	 * 
	 * @param View
	 * 
	 * */
	public void showAction(View view) {
		switch (view.getId()) {
		case R.id.img_detail1:
			startActivity(new Intent(MainActivity.this, HistoryActivity.class));
			break;
		case R.id.img_detail2:
			startActivity(new Intent(MainActivity.this,
					PlayDetailActivity.class));
			break;
		default:
			break;
		}
	}

	/**
	 * 点击按钮进入登录界面
	 * 
	 * */
	public void showLogin(View view) {
		if (hasLogin()) {
			Intent intent = new Intent(this, UserLoginActivity.class);
			intent.putExtra("isLogin", "OK");

			startActivity(intent);
		} else {
			Intent intent = new Intent(this, UserLoginActivity.class);
			intent.putExtra("isLogin", "NO");
			startActivityForResult(intent, 1001);
		}
	}

	/**
	 * 登录之前判断是否已经登陆 *
	 * 
	 */

	private boolean hasLogin() {
		SharedPreferences sp = getSharedPreferences("login",
				Context.MODE_PRIVATE);
		String name = sp.getString("UserName", "");
		if (!"".equals(name)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * 回传的值
	 * */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		// 如果登录成功
		if (requestCode == 1001 && resultCode == 1002) {
			SharedPreferences sp = getSharedPreferences("login",
					Context.MODE_PRIVATE);
			String imgurl = sp.getString("Image", "");
			initHeadIMG(imgurl);
		}
		// 如果使用的第三方登录
		if (requestCode == 1001 && resultCode == 1003) {
			/*
			 * byte[] bt = SDCardHelper.loadFileFromSDCard(filepath); Bitmap
			 * bitmap = BitmapFactory.decodeByteArray(bt, 0, bt.length);
			 * imageView.setImageBitmap(bitmap);
			 */
		}
	}

	/**
	 * 下载头像
	 * */

	private void initHeadIMG(String imgurl) {
		if (!"".equals(imgurl)) {
			BitmapUtils bmBitmapUtils = new BitmapUtils(MainActivity.this);
			bmBitmapUtils.display(imageView, Paths.USER_HEAD_IMG + imgurl,
					new BitmapLoadCallBack<ImageView>() {

						@Override
						public void onLoadCompleted(ImageView container,
								String uri, Bitmap bitmap,
								BitmapDisplayConfig config, BitmapLoadFrom from) {
							// TODO Auto-generated method stub
							imageView.setImageBitmap(bitmap);
							// 保存头像
							SaveFile.saveHeadimg(bitmap, filepath);
						}

						@Override
						public void onLoadFailed(ImageView container,
								String uri, Drawable drawable) {
							// TODO Auto-generated method stub

						}

					});
		}
	}

	/**
	 * 注册广播
	 * */
	public void registerBroadCast() {
		radioRecevier = new RadioRecevier();
		IntentFilter intentFilter = new IntentFilter("myradio");
		registerReceiver(radioRecevier, intentFilter);
	}

	/**
	 * 广播接受到的数据
	 * */
	public class RadioRecevier extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if (intent.getAction().equals("myradio")) {
				if (intent != null) {
					// Bundle bundle = intent.getExtras();
					// TJRadios tjRadios = (TJRadios) bundle.get("playRadio");
					play_pause.setVisibility(View.VISIBLE);
					play_start.setVisibility(View.INVISIBLE);
				}
			}
		}
	}

	/**
	 * 注册广播
	 * */
	public void registerBroadCastStop() {
		radioRecevierStop = new RadioRecevierStop();
		IntentFilter intentFilter = new IntentFilter("myradioStop");
		registerReceiver(radioRecevierStop, intentFilter);
	}

	/**
	 * 广播接受到的数据
	 * */
	public class RadioRecevierStop extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if (intent.getAction().equals("myradioStop")) {
				if (intent != null) {
					// Bundle bundle = intent.getExtras();
					// TJRadios tjRadios = (TJRadios) bundle.get("playRadio");
					play_pause.setVisibility(View.INVISIBLE);
					play_start.setVisibility(View.VISIBLE);
				}
			}
		}
	}

	/**
	 * 
	 * 销毁的时候删除广播
	 * */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(radioRecevier);
		unregisterReceiver(radioRecevierStop);
	}

}
