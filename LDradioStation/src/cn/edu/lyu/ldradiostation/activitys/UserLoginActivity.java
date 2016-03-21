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
 * ��¼����
 * 
 * */
public class UserLoginActivity extends Activity {
	@ViewInject(R.id.user_login)
	private LoginBtnView loginBtnView;// ��¼��ť
	@ViewInject(R.id.et_user)
	private EditText unText;// �û���
	@ViewInject(R.id.et_pwd)
	private EditText pwText;// ����
	private HttpUtils httpUtils;// ����
	@ViewInject(R.id.login_rl1)
	private RelativeLayout login_rl1;// δ��½���صĲ���
	@ViewInject(R.id.login_rl2)
	private RelativeLayout login_rl2;// �ѵ�¼���صĲ���
	@ViewInject(R.id.login_rl3)
	private RelativeLayout login_rl3;// �ѵ�¼���صĲ���
	@ViewInject(R.id.login_loading)
	private ImageView login_loading;// londing��ͼƬ
	@ViewInject(R.id.login_sina)
	private ImageView login_sina;// ���˵�¼��ť
	@ViewInject(R.id.login_wechat)
	private ImageView login_wechat;// ��Ѷ��¼��ť
	@ViewInject(R.id.login_taobao)
	private ImageView login_taobao;// �Ա���¼��ť
	@ViewInject(R.id.img_login_head)
	private ImageView img_login_head;// ��¼����ʾ��ͼƬ

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userlogin);
		// ����ע��
		ViewUtils.inject(this);
		httpUtils = new HttpUtils();
		ShareSDK.initSDK(UserLoginActivity.this);
		Intent intent = getIntent();
		String result = intent.getStringExtra("isLogin");
		if ("OK".equals(result)) {
			login_rl1.setVisibility(View.VISIBLE);
			login_rl2.setVisibility(View.INVISIBLE);
			File file = new File(getFilesDir(), "head.jpg");
			// ���ͼƬ���ھͻ�ȡͼƬ
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
	 * �����¼��ť��Ӧ���¼�
	 * */
	@OnClick(R.id.user_login)
	public void userLogin(View view) {
		String user = unText.getText().toString().trim();
		String pwd1 = pwText.getText().toString().trim();
		// MD5����
		String pwd = MD5Utils.getMD5(pwd1);
		// �ж��û��������Ƿ�Ϊ��
		if ("OK".equals(unOrPwdIsNull(user, pwd1))) {
			playImg();
			RequestParams params = new RequestParams();
			params.addBodyParameter("username", user);
			params.addBodyParameter("password1", pwd);
			httpUtils.send(HttpRequest.HttpMethod.POST, Paths.USER_LOGIN,
					params, new RequestCallBack<String>() {
						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							// �ȴ�����ʧ
							Log.e("=====", "==���󵽵Ľ����===" + responseInfo.result);
							// �����ַ���
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
											"�û����������", Toast.LENGTH_LONG)
											.show();
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						@Override
						public void onFailure(HttpException error, String msg) {
							// �ȴ�����ʧ
						}

						@Override
						public void onStart() {
							super.onStart();
							// ��ʾ�ȴ�����ʾ�û���ʼ����������
							Log.e("=====", "==��ʼ����������==");
						}

						@Override
						public void onLoading(long total, long current,
								boolean isUploading) {
							super.onLoading(total, current, isUploading);
							Log.e("=====", "==�������ݵĹ���==" + current * 100
									/ total);
						}
					});
		} else {
			Toast.makeText(UserLoginActivity.this, unOrPwdIsNull(user, pwd1),
					Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * �ж��û��������Ƿ�Ϊ��
	 * */
	private String unOrPwdIsNull(String un, String pwd) {
		String canLogin = "".equals(un) ? "�û�������Ϊ��"
				: ("".equals(pwd) ? "���벻��Ϊ��" : "OK");
		return canLogin;
	}

	/**
	 * �������¼�������
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
		// �����еĿؼ�ʧȥ����
		// for (int i = 0; i < login_rl2.getChildCount(); i++) {
		// login_rl2.getChildAt(i).setFocusable(false);
		// }
		loginBtnView.setClickable(false);

	}

	/**
	 * �����û�����ϸ��Ϣ����������Ϣ�������ĵ�
	 * */
	private void saveMessage(final String userName) {
		// �õ��û��ĵ���ϸ��Ϣ
		RequestParams params = new RequestParams();
		params.addBodyParameter("username", userName);
		httpUtils.send(HttpRequest.HttpMethod.POST, Paths.USER_DETAIL, params,
				new RequestCallBack<String>() {
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						SharedPreferences sp = getSharedPreferences("login",
								Context.MODE_PRIVATE);
						SharedPreferences.Editor editor = sp.edit();
						// �ȴ�����ʧ
						Log.e("=====", "==���󵽵Ľ����===" + responseInfo.result);
						// �����ַ���
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
										"��¼�ɹ���" + "�û�����" + userName,
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
						// �ȴ�����ʧ
					}

					@Override
					public void onStart() {
						super.onStart();
						// ��ʾ�ȴ�����ʾ�û���ʼ����������
						Log.e("=====", "==��ʼ����������==");
					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
						super.onLoading(total, current, isUploading);
						Log.e("=====", "==�������ݵĹ���==" + current * 100 / total);
					}
				});

	}

	/**
	 * ������˵�¼
	 * */
	@OnClick(R.id.login_wechat)
	public void sinaLogin(View view) {
		loginUser(TencentWeibo.NAME);
	}

	/**
	 * ��ȡ�û�����
	 * */
	private void loginUser(String name) {

		/**
		 * 
		 * ��ȡָ��ƽ̨�Ķ���
		 * */
		/**
		 * 1.��ȡָ��ƽ̨�Ķ��� 2.ʹ��ShowUser()���е�¼ 3.���õ�¼����ص�
		 */
		playImg();
		Platform p = ShareSDK.getPlatform(UserLoginActivity.this, name);
		if (name.equals(SinaWeibo.NAME)) {
			// ���������΢���ͽ���sso��¼
			p.SSOSetting(true);
		}
		p.showUser(null);
		p.removeAccount();
		p.setPlatformActionListener(new PlatformActionListener() {

			@Override
			public void onError(Platform arg0, int arg1, Throwable arg2) {
				// TODO Auto-generated method stub
				Log.e("====", "==��¼������Ϣ��==" + arg2.getMessage());
			}

			@Override
			public void onComplete(Platform arg0, int arg1,
					HashMap<String, Object> arg2) {
				// TODO Auto-generated method stub
				// 1.��ȡƽ̨����arg0.getName(),��ȡ����ͷ��ʹ�÷��ص�hashmap��������ͬƽ̨��Ҫif/else�ж�
				// 2.�����ͷ��������û��Ҫ����sharesdk�ṩ�����ݿ�ȡֵ������Ҫƽ̨�ж�
				// ��ȡָ��ƽ̨��¼���û����ǳ�arg0.getDb().getUserName();
				// ��ȡ�û�ͷ��arg0.getDb().getUserIcon()
				if (arg0.getName().equals(SinaWeibo.NAME)) {

				} else if (arg0.getName().equals(TencentWeibo.NAME)) {
					// Bitmap bitmap = (Bitmap)
					// arg2.get(arg0.getDb().getUserIcon());
					// // �����ݱ��浽����
					// SaveFile.saveHeadimg(bitmap, new File(getFilesDir(),
					// "head.jpg"));
					final String nick = arg2.get("nick").toString();
					final String userName = arg0.getDb().getUserId();
					// ע���û�
					RequestParams requestParams = new RequestParams();
					requestParams.addBodyParameter("username", userName);
					requestParams.addBodyParameter("nick", nick);
					Log.e("====", "==��¼�ɹ���ȡ������Ϣ��==" + arg2.toString());
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
				// ʹ��ת��ͼƬ��ʧ
				login_rl3.setVisibility(View.INVISIBLE);
			}
		});
	}

	// /**
	// * ��������userName��ȡ����
	// * */
	// @OnClick(R.id.et_user)
	// public void text1GetFocus(View view){
	// unText.setFocusable(true);
	// }
	// /**
	// * ��������pwd��ȡ����
	// * */
	// @OnClick(R.id.et_pwd)
	// public void text2GetFocus(View view){
	// unText.setFocusable(true);
	// }
}
