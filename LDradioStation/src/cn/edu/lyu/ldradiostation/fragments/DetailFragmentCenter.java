package cn.edu.lyu.ldradiostation.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import cn.edu.lyu.ldradiostation.R;
import cn.edu.lyu.ldradiostation.model.MyMediaPlayer;
import cn.edu.lyu.ldradiostation.utils.BGFastBlurUtil;
import cn.edu.lyu.ldradiostation.utils.Player;
import cn.edu.lyu.ldradiostation.views.DetailCenterView;

/**
 * ViewPager 中间部分分
 * 
 * @author宋熙明
 * */
public class DetailFragmentCenter extends Fragment {
	private DetailCenterView detailCenterView = null;// 自定义view
	private ImageView ivbg = null;
	private SeekBar seekBar = null;// seekBar
	private Player player = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View view = inflater.inflate(R.layout.fragment_detail_center,
				container, false);
		initView(view);
		player = Player.getInstance();
		player.setContext(getActivity());
		player.setSeekBar(seekBar);
		// if (player.getState() != 1) {
		//
		// player.playUrl("http://10.0.153.236:1111/Source/Radios/201510041110238544.mp3");
		// player.play();
		// }
		player.setBarProgress();
		seekBar.setOnSeekBarChangeListener(new SeekBarChangeEvent());
		return view;
	}

	/**
	 * 初始化view
	 * */
	private void initView(View view) {
		// 自定义view
		detailCenterView = (DetailCenterView) view.findViewById(R.id.dcv);
		ivbg = (ImageView) getActivity().findViewById(R.id.ivbg);
		seekBar = (SeekBar) view.findViewById(R.id.progresss);
	}

	class SeekBarChangeEvent implements OnSeekBarChangeListener {
		int progress;

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// 原本是(progress/seekBar.getMax())*player.mediaPlayer.getDuration()
			this.progress = progress * player.mediaPlayer.getDuration()
					/ seekBar.getMax();
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {

		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// seekTo()的参数是相对与影片时间的数字，而不是与seekBar.getMax()相对的数字
			player.mediaPlayer.seekTo(progress);
		}

	}

}
