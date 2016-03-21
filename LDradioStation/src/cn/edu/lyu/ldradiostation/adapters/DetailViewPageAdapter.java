package cn.edu.lyu.ldradiostation.adapters;

import cn.edu.lyu.ldradiostation.fragments.DetailFragmentCenter;
import cn.edu.lyu.ldradiostation.fragments.DetailFragmentLeft;
import cn.edu.lyu.ldradiostation.fragments.DetailFragmentRight;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/**
 * 这是播放详细界面ViewPager 的适配器
 * */
public class DetailViewPageAdapter extends  FragmentPagerAdapter{

	public DetailViewPageAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		Fragment fragment = null;
		switch (arg0) {
		case 0:
			fragment = new DetailFragmentLeft();
			break;
		case 1:
			fragment = new DetailFragmentCenter();
			break;
		case 2:
			fragment = new DetailFragmentRight();
			break;

		default:
			break;
		}
		return fragment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		super.destroyItem(container, position, object);
	}

}
