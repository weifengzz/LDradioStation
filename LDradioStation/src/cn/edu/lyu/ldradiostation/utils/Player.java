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

	public MyMediaPlayer mediaPlayer; // ý�岥����
	private SeekBar seekBar = null; // �϶���
	private Timer mTimer = null; // ��ʱ��
	private int state = 0;

	public void setSeekBar(SeekBar seekBar) {
		this.seekBar = seekBar;
	}

	public SeekBar getSeekBar() {
		return seekBar;
	}

	// ��ʼ��������
	private Player() {
		super();
		this.seekBar = getSeekBar();
		try {
			mediaPlayer = MyMediaPlayer.getInstance();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// ����ý��������
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
		// ÿһ�봥��һ��
		if (mTimer == null) {
			mTimer = new Timer();
			mTimer.schedule(timerTask, 0, 1000);
		}
	}

	// ��ʱ��
	TimerTask timerTask = new TimerTask() {

		@Override
		public void run() {
			if (mediaPlayer == null)
				return;
			if (mediaPlayer.isPlaying() && seekBar.isPressed() == false) {
				handler.sendEmptyMessage(0); // ������Ϣ
			}
		}
	};

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			int position = mediaPlayer.getCurrentPosition();
			int duration = mediaPlayer.getDuration();
			if (duration > 0) {
				// ������ȣ���ȡ���������̶�*��ǰ���ֲ���λ�� / ��ǰ����ʱ����
				long pos = seekBar.getMax() * position / duration;
				seekBar.setProgress((int) pos);
			}
		};
	};

	// �õ� ��ǰ��״̬
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
	 *            url��ַ
	 */
	public void playUrl(String url) {

		try {
			mediaPlayer.reset();
			mediaPlayer.setDataSource(url); // ��������Դ
			mediaPlayer.prepareAsync(); // prepare�Զ�����
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

	// ��ͣ
	public void pause() {
		Intent intent = new Intent();
		intent.setAction("myradioStop");
		context.sendBroadcast(intent);
		mediaPlayer.pause();
		state = 2;
	}

	// ֹͣ
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
	 * �������
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
