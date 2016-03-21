package cn.edu.lyu.ldradiostation.listeners;



import cn.edu.lyu.ldradiostation.views.NavigationView;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class MainViewPageChangeListener implements OnPageChangeListener {
	private HorizontalScrollView horizontalScrollView = null;
	private LinearLayout linearLayout = null;
	private DrawerLayout drawerLayout = null;

	public MainViewPageChangeListener(
			HorizontalScrollView horizontalScrollView,
			LinearLayout linearLayout, DrawerLayout drawerLayout) {
		this.horizontalScrollView = horizontalScrollView;
		this.linearLayout = linearLayout;
		this.drawerLayout = drawerLayout;
	}
	int curPage;
	int preState;
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		// if (arg0 == linearLayout.getChildCount() - 1) {
		// drawerLayout.openDrawer(Gravity.END);
		// }
		if (preState == 1 && arg0 == 0
				&& curPage == linearLayout.getChildCount() - 1) {
			drawerLayout.openDrawer(Gravity.END);
		}
		preState = arg0;
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		curPage = arg0;
	}



	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		horizontalScrollView.smoothScrollTo(90 * arg0, 0);
		for (int i = 0; i < linearLayout.getChildCount(); i++) {
			((NavigationView) (linearLayout.getChildAt(i))).setFlag(false);
		}
		((NavigationView) (linearLayout.getChildAt(arg0))).setFlag(true);
		
	}

}
