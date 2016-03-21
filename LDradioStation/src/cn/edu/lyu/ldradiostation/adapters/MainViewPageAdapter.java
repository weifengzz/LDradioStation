package cn.edu.lyu.ldradiostation.adapters;

import cn.edu.lyu.ldradiostation.fragments.ProgramFragment;
import cn.edu.lyu.ldradiostation.fragments.MainFragment;
import cn.edu.lyu.ldradiostation.fragments.MineFragment;
import cn.edu.lyu.ldradiostation.fragments.TJFragment;
import cn.edu.lyu.ldradiostation.fragments.TimeFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/**
 * ´´½¨ÊÊÅäÆ÷
 * 
 * @author ËÎÎõÃ÷
 * 
 */
public class MainViewPageAdapter extends FragmentPagerAdapter {
	private final int PAGE_NO = 7;

	public MainViewPageAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		switch (arg0) {
		case 0:
			TJFragment tjFragment = new TJFragment();
			return tjFragment;
		case 1:
			ProgramFragment categoryFragment = new ProgramFragment();
			return categoryFragment;
		case 2:
			TimeFragment timeFragment = new TimeFragment();
			return timeFragment;
		case 6:
			MineFragment mineFragment = new MineFragment();
			return mineFragment;
		default:
			MainFragment fragment = new MainFragment();
			Bundle bundle = new Bundle();
			bundle.putString("pageno", String.valueOf(arg0));
			fragment.setArguments(bundle);
			return fragment;
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return PAGE_NO;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		super.destroyItem(container, position, object);
	}
}
