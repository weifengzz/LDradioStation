package cn.edu.lyu.ldradiostation.activitys;

import java.io.File;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import cn.edu.lyu.ldradiostation.R;
import cn.edu.lyu.ldradiostation.model.Paths;
import cn.edu.lyu.ldradiostation.utils.MD5Utils;
import cn.edu.lyu.ldradiostation.utils.SDCardHelper;
import cn.edu.lyu.ldradiostation.views.LoginBtnView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.weibo.TencentWeibo;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 登录界面
 * 
 * */
public class UserLoginActivity extends Activity {
	@ViewInject(R.id.user_login)
	private LoginBtnView loginBtnView;// 登录按钮
	@ViewInject(R.id.et_user)
	private EditText unText;// 用户名
	@ViewInject(R.id.et_pwd)
	private EditText pwText;// 密码
	private HttpUtils httpUtils;// 工具
	@ViewInject(R.id.login_rl1)
	private RelativeLayout login_rl1;// 未登陆隐藏的布局
	@ViewInject(R.id.login_rl2)
	private RelativeLayout login_rl2;// 已登录隐藏的布局
	@ViewInject(R.id.login_rl3)
	private RelativeLayout login_rl3;// 已登录隐藏的布局
	@ViewInject(R.id.login_loading)
	private ImageView login_loading;// londing的图片
	@ViewInject(R.id.login_sina)
	private ImageView login_sina;// 新浪登录按钮
	@ViewInject(R.id.login_wechat)
	private ImageView login_wechat;// 腾讯登录按钮
	@ViewInject(R.id.login_taobao)
	private ImageView login_taobao;// 淘宝登录按钮
	@ViewInject(R.id.img_login_head)
	private ImageView img_login_head;// 登录后显示的图片

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userlogin);
		// 解析注解
		ViewUtils.inject(this);
		httpUtils = new HttpUtils();
		ShareSDK.initSDK(UserLoginActivity.this);
		Intent intent = getIntent();
		String result = intent.getStringExtra("isLogin");
		if ("OK".equals(result)) {
			login_rl1.setVisibility(View.VISIBLE);
			login_rl2.setVisibility(View.INVISIBLE);
			File file = new File(getFilesDir(), "head.jpg");
			// 如果图片存在就获取图片
			if (file.exists()) {
				byte[] bt = SDCardHelper.loadFileFromSDCard(file);
				Bitmap bitmap = BitmapFactory.decodeByteArray(bt, 0, bt.length);
				img_login_head.setImageBitmap(bitmap);
			}

		} else {
			login_rl2.setVisibility(View.VISIBLE);
			login_rl1.setVisibility(View.INVISIBLE);
		}
	}

	/**
	 * 点击登录按钮响应的事件
	 * */
	@OnClick(R.id.user_login)
	public void userLogin(View view) {
		String user = unText.getText().toString().trim();
		String pwd1 = pwText.getText().toString().trim();
		// MD5加密
		String pwd = MD5Utils.getMD5(pwd1);
		// 判断用户名密码是否为空
		if ("OK".equals(unOrPwdIsNull(user, pwd1))) {
			playImg();
			RequestParams params = new RequestParams();
			params.addBodyParameter("username", user);
			params.addBodyParameter("password1", pwd);
			httpUtils.send(HttpRequest.HttpMethod.POST, Paths.USER_LOGIN,
					params, new RequestCallBack<String>() {
						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							// 等待框消失
							Log.e("=====", "==请求到的结果是===" + responseInfo.result);
							// 解析字符串
							try {
								JSONObject jsonObject = new JSONObject(
										responseInfo.result);
								String result = jsonObject.getString("result");
								String userName = jsonObject
										.getString("returnUN");
								if ("OK".equals(result)) {
									saveMessage(userName);
								} else {
									loginBtnView.setClickable(true);
									login_rl3.setVisibility(View.INVISIBLE);
									Toast.makeText(UserLoginActivity.this,
											"用户名密码错误！", Toast.LENGTH_LONG)
											.show();
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						@Override
						public void onFailure(HttpException error, String msg) {
							// 等待框消失
						}

						@Override
						public void onStart() {
							super.onStart();
							// 显示等待框，提示用户开始请求数据了
							Log.e("=====", "==开始请求数据了==");
						}

						@Override
						public void onLoading(long total, long current,
								boolean isUploading) {
							super.onLoading(total, current, isUploading);
							Log.e("=====", "==请求数据的过程==" + current * 100
									/ total);
						}
					});
		} else {
			Toast.makeText(UserLoginActivity.this, unOrPwdIsNull(user, pwd1),
					Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * 判断用户名密码是否为空
	 * */
	private String unOrPwdIsNull(String un, String pwd) {
		String canLogin = "".equals(un) ? "用户名不能为空"
				: ("".equals(pwd) ? "密码不能为空" : "OK");
		return canLogin;
	}

	/**
	 * 当点击登录界面加载
	 * 
	 * */
	private void playImg() {
		login_rl3.setVisibility(View.VISIBLE);
		login_loading.setVisibility(View.VISIBLE);
		Animation operatingAnim = AnimationUtils.loadAnimation(this,
				R.anim.loginloading);
		LinearInterpolator lin = new LinearInterpolator();
		operatingAnim.setInterpolator(lin);
		login_loading.startAnimation(operatingAnim);
		// 让所有的控件失去焦点
		// for (int i = 0; i < login_rl2.getChildCount(); i++) {
		// login_rl2.getChildAt(i).setFocusable(false);
		// }
		loginBtnView.setClickable(false);

	}

	/**
	 * 保存用户的详细信息，并保存信息到共享文档
	 * */
	private void saveMessage(final String userName) {
		// 得到用户的的详细信息
		RequestParams params = new RequestParams();
		params.addBodyParameter("username", userName);
		httpUtils.send(HttpRequest.HttpMethod.POST, Paths.USER_DETAIL, params,
				new RequestCallBack<String>() {
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						SharedPreferences sp = getSharedPreferences("login",
								Context.MODE_PRIVATE);
						SharedPreferences.Editor editor = sp.edit();
						// 等待框消失
						Log.e("=====", "==请求到的结果是===" + responseInfo.result);
						// 解析字符串
						try {
							JSONObject jsonObject = new JSONObject(
									responseInfo.result);
							JSONArray jsonArray = jsonObject
									.getJSONArray("data");
							if (jsonArray.length() == 0) {
								editor.putString("UserState", "nodetail");
							} else {
								editor.putString("UserState", "hasdetail");
								editor.putString("UserId", jsonArray
										.getJSONObject(0).getString("UserId"));
								editor.putString("UserName", userName);
								editor.putString("Email", jsonArray
										.getJSONObject(0).getString("Email"));
								editor.putString("Image", jsonArray
										.getJSONObject(0).getString("Image"));
								editor.putString("Sex", jsonArray
										.getJSONObject(0).getString("Sex"));
								editor.putString("Say", jsonArray
										.getJSONObject(0).getString("Say"));
								editor.commit();
								loginBtnView.setClickable(true);
								login_rl3.setVisibility(View.INVISIBLE);
								Toast.makeText(UserLoginActivity.this,
										"登录成功！" + "用户名：" + userName,
										Toast.LENGTH_LONG).show();
								Intent intent = new Intent();
								setResult(1002, intent);
								UserLoginActivity.this.finish();
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// 等待框消失
					}

					@Override
					public void onStart() {
						super.onStart();
						// 显示等待框，提示用户开始请求数据了
						Log.e("=====", "==开始请求数据了==");
					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
						super.onLoading(total, current, isUploading);
						Log.e("=====", "==请求数据的过程==" + current * 100 / total);
					}
				});

	}

	/**
	 * 点击新浪登录
	 * */
	@OnClick(R.id.login_wechat)
	public void sinaLogin(View view) {
		loginUser(TencentWeibo.NAME);
	}

	/**
	 * 获取用户资料
	 * */
	private void loginUser(String name) {

		/**
		 * 
		 * 获取指定平台的对象
		 * */
		/**
		 * 1.获取指定平台的对象 2.使用ShowUser()进行登录 3.设置登录结果回调
		 */
		playImg();
		Platform p = ShareSDK.getPlatform(UserLoginActivity.this, name);
		if (name.equals(SinaWeibo.NAME)) {
			// 如果是新浪微博就禁掉sso登录
			p.SSOSetting(true);
		}
		p.showUser(null);
		p.removeAccount();
		p.setPlatformActionListener(new PlatformActionListener() {

			@Override
			public void onError(Platform arg0, int arg1, Throwable arg2) {
				// TODO Auto-generated method stub
				Log.e("====", "==登录错误信息是==" + arg2.getMessage());
			}

			@Override
			public void onComplete(Platform arg0, int arg1,
					HashMap<String, Object> arg2) {
				// TODO Auto-generated method stub
				// 1.获取平台名称arg0.getName(),获取高清头像使用返回的hashmap解析，不同平台需要if/else判断
				// 2.如果对头像清晰度没有要求用sharesdk提供的数据库取值，不需要平台判断
				// 获取指定平台登录的用户的昵称arg0.getDb().getUserName();
				// 获取用户头像arg0.getDb().getUserIcon()
				if (arg0.getName().equals(SinaWeibo.NAME)) {

				} else if (arg0.getName().equals(TencentWeibo.NAME)) {
					// Bitmap bitmap = (Bitmap)
					// arg2.get(arg0.getDb().getUserIcon());
					// // 将数据保存到本地
					// SaveFile.saveHeadimg(bitmap, new File(getFilesDir(),
					// "head.jpg"));
					final String nick = arg2.get("nick").toString();
					final String userName = arg0.getDb().getUserId();
					// 注册用户
					RequestParams requestParams = new RequestParams();
					requestParams.addBodyParameter("username", userName);
					requestParams.addBodyParameter("nick", nick);
					Log.e("====", "==登录成功获取到的信息是==" + arg2.toString());
					httpUtils.send(HttpRequest.HttpMethod.POST,
							Paths.USER_THREE_REGISTER, requestParams,
							new RequestCallBack<String>() {

								@Override
								public void onSuccess(
										ResponseInfo<String> responseInfo) {
									// TODO Auto-generated method stub
									Log.e("======success=======", "success");
									SharedPreferences sp = getSharedPreferences(
											"login", Context.MODE_PRIVATE);
									SharedPreferences.Editor editor = sp.edit();
									editor.putString("UserName", userName);
									editor.commit();
									Intent intent = new Intent();
									setResult(1003, intent);
									UserLoginActivity.this.finish();
								}

								@Override
								public void onFailure(HttpException error,
										String msg) {
									// TODO Auto-generated method stub
									Log.e("======defeat=======", "defeat");
									SharedPreferences sp = getSharedPreferences(
											"login", Context.MODE_PRIVATE);
									SharedPreferences.Editor editor = sp.edit();
									editor.putString("UserName", userName);
									editor.commit();
									Intent intent = new Intent();
									setResult(1003, intent);
									UserLoginActivity.this.finish();
								}
							});
				}

			}

			@Override
			public void onCancel(Platform arg0, int arg1) {
				// TODO Auto-generated method stub
				// 使旋转的图片消失
				login_rl3.setVisibility(View.INVISIBLE);
			}
		});
	}

	// /**
	// * 点击输入框userName获取焦点
	// * */
	// @OnClick(R.id.et_user)
	// public void text1GetFocus(View view){
	// unText.setFocusable(true);
	// }
	// /**
	// * 点击输入框pwd获取焦点
	// * */
	// @OnClick(R.id.et_pwd)
	// public void text2GetFocus(View view){
	// unText.setFocusable(true);
	// }
}
