package cn.edu.lyu.ldradiostation.services;

import cn.edu.lyu.ldradiostation.model.HistoryRadios;
import cn.edu.lyu.ldradiostation.model.MyMediaPlayer;
import cn.edu.lyu.ldradiostation.model.Paths;
import cn.edu.lyu.ldradiostation.model.Radios;
import cn.edu.lyu.ldradiostation.model.TJRadios;
import cn.edu.lyu.ldradiostation.utils.HistoryRadio;
import cn.edu.lyu.ldradiostation.utils.Player;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class RadioPlayService extends Service {
	private Player player;
	private RadioRecevier radioRecevier;// �㲥
	private TJRadios tjRadios;
	private Radios radios;
	private HistoryRadios historyRadios;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
				player = Player.getInstance();
				player.playUrl(tjRadios.getRadioAddress());
			}
		};
	};

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		registerBroadCast();// ע��㲥
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return Service.START_NOT_STICKY;
	}

	/**
	 * ע��㲥
	 * */
	public void registerBroadCast() {
		radioRecevier = new RadioRecevier();
		IntentFilter intentFilter = new IntentFilter("myradioreceiver");
		registerReceiver(radioRecevier, intentFilter);
	}

	/**
	 * �㲥���ܵ�������
	 * */
	public class RadioRecevier extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if (intent.getAction().equals("myradioreceiver")) {

				Bundle bundle = intent.getExtras();
				if ((tjRadios = (TJRadios) bundle.getSerializable("playRadios")) != null) {
					player = Player.getInstance();
					player.playUrl(tjRadios.getRadioAddress());
				} else if ((radios = (Radios) bundle
						.getSerializable("playProgramRadio")) != null) {
					player = Player.getInstance();
					player.playUrl(Paths.RADIO_PATH + radios.getRadioAddress());
				}else if ((historyRadios = (HistoryRadios) bundle
						.getSerializable("playHistoryRadios")) != null) {
					player = Player.getInstance();
					player.playUrl( historyRadios.getRadioAddress());
				}
				final MyMediaPlayer mymediaPlayer = MyMediaPlayer.getInstance();
				/**
				 * ��ʱ���5���û�в����������������һ��
				 * */
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub

						while (true) {

							int a = mymediaPlayer.getCurrentPosition();
							try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							int b = mymediaPlayer.getCurrentPosition();
							if (a == b) {
								handler.sendEmptyMessage(0);
							}
						}
					}
				});
			}
		}
	}

	/**
	 * 
	 * ���ٵ�ʱ��ɾ���㲥
	 * */
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(radioRecevier);
	}
}
