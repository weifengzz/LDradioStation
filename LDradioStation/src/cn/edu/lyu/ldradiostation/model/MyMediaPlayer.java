package cn.edu.lyu.ldradiostation.model;

import android.media.MediaPlayer;

public class MyMediaPlayer extends MediaPlayer {
	private static MyMediaPlayer instance = new MyMediaPlayer();

	private MyMediaPlayer() {
	}

	public static MyMediaPlayer getInstance() {
		return instance;
	}
}
