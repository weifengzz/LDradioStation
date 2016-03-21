package cn.edu.lyu.ldradiostation.listeners;

import cn.edu.lyu.ldradiostation.views.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class HorizontalScrollViewOnClick implements OnClickListener {
	private LinearLayout linearLayout = null;
	private ViewPager viewPager = null;
	private DrawerLayout drawerLayout = null;

	public HorizontalScrollViewOnClick(LinearLayout linearLayout,
			ViewPager viewPager, DrawerLayout drawerLayout) {
		super();
		this.linearLayout = linearLayout;
		this.viewPager = viewPager;
		this.drawerLayout = drawerLayout;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub

		for (int i = 0; i < linearLayout.getChildCount(); i++) {
			((NavigationView) (linearLayout.getChildAt(i))).setFlag(false);
		}
		((NavigationView) view).setFlag(true);
		viewPager.setCurrentItem(linearLayout.indexOfChild(view));
	}
}
