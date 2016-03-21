package cn.edu.lyu.ldradiostation.utils;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import cn.edu.lyu.ldradiostation.model.MyMediaPlayer;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Handler;
import android.util.Log;
import android.widget.SeekBar;

public class Player implements OnBufferingUpdateListener, OnCompletionListener,
		OnPreparedListener {
	private Context context = null;
	private static Player instance = new Player();

	public static Player getInstance() {
		return instance;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public MyMediaPlayer mediaPlayer; // 媒体播放器
	private SeekBar seekBar = null; // 拖动条
	private Timer mTimer = null; // 计时器
	private int state = 0;

	public void setSeekBar(SeekBar seekBar) {
		this.seekBar = seekBar;
	}

	public SeekBar getSeekBar() {
		return seekBar;
	}

	// 初始化播放器
	private Player() {
		super();
		this.seekBar = getSeekBar();
		try {
			mediaPlayer = MyMediaPlayer.getInstance();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置媒体流类型
			mediaPlayer.setOnBufferingUpdateListener(this);
			mediaPlayer.setOnPreparedListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (seekBar != null) {
			setBarProgress();
		}
	}

	public void setBarProgress() {
		// 每一秒触发一次
		if (mTimer == null) {
			mTimer = new Timer();
			mTimer.schedule(timerTask, 0, 1000);
		}
	}

	// 计时器
	TimerTask timerTask = new TimerTask() {

		@Override
		public void run() {
			if (mediaPlayer == null)
				return;
			if (mediaPlayer.isPlaying() && seekBar.isPressed() == false) {
				handler.sendEmptyMessage(0); // 发送消息
			}
		}
	};

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			int position = mediaPlayer.getCurrentPosition();
			int duration = mediaPlayer.getDuration();
			if (duration > 0) {
				// 计算进度（获取进度条最大刻度*当前音乐播放位置 / 当前音乐时长）
				long pos = seekBar.getMax() * position / duration;
				seekBar.setProgress((int) pos);
			}
		};
	};

	// 得到 当前的状态
	public int getState() {
		return state;
	}

	public void play() {
		mediaPlayer.start();
		state = 1;
		Intent intent = new Intent();
		intent.setAction("myradio");
		context.sendBroadcast(intent);
	}

	/**
	 * 
	 * @param url
	 *            url地址
	 */
	public void playUrl(String url) {

		try {
			mediaPlayer.reset();
			mediaPlayer.setDataSource(url); // 设置数据源
			mediaPlayer.prepareAsync(); // prepare自动播放
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 暂停
	public void pause() {
		Intent intent = new Intent();
		intent.setAction("myradioStop");
		context.sendBroadcast(intent);
		mediaPlayer.pause();
		state = 2;
	}

	// 停止
	public void stop() {
		Intent intent = new Intent();
		intent.setAction("myradioStop");
		context.sendBroadcast(intent);
		state = 0;
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		mp.start();
		Log.e("mediaPlayer", "onPrepared");
		Intent intent = new Intent();
		intent.setAction("myradio");
		context.sendBroadcast(intent);
	}
	@Override
	public void onCompletion(MediaPlayer mp) {
		Log.e("mediaPlayer", "onCompletion");
		Intent intent = new Intent();
		intent.setAction("myradioStop");
		context.sendBroadcast(intent);
	}

	/**
	 * 缓冲更新
	 */
	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {
		if (seekBar != null) {
			seekBar.setSecondaryProgress(percent);
			int currentProgress = seekBar.getMax()
					* mediaPlayer.getCurrentPosition()
					/ mediaPlayer.getDuration();
			Log.e(currentProgress + "% play", percent + " buffer");
		}
	}

}
