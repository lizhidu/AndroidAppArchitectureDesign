package com.lizhidu.androidapparchitecturedesign.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.widget.LinearLayout;

import com.lizhidu.androidapparchitecturedesign.R;
import com.lizhidu.androidapparchitecturedesign.ui.fragment.BlankFragment;
import com.lizhidu.androidapparchitecturedesign.ui.fragment.SearchFragment;

public class MainActivity1 extends FragmentActivity{
	private ViewPager viewPager;
	private LinearLayout blankLinear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main1);

		initView();
		initData();
		setListener();
	}

	private void initView() {
		// TODO Auto-generated method stub
		viewPager = (ViewPager) findViewById(R.id.viewPager1);
		blankLinear = (LinearLayout) findViewById(R.id.blank_linear);
	}

	private void initData() {
		// TODO Auto-generated method stub
		viewPager.setAdapter(adapter);
		viewPager.setCurrentItem(1, false);
		viewPager.setOnPageChangeListener(changeListener);
	}

	private void setListener() {
		// TODO Auto-generated method stub

	}

	private FragmentPagerAdapter adapter = new FragmentPagerAdapter(
			getSupportFragmentManager()) {

		public int getCount() {
			return 2;
		}

		public Fragment getItem(int position) {
			Fragment fragment = null;
			switch (position) {
				case 0:
					fragment = new BlankFragment();
					break;
				case 1:
					fragment = new SearchFragment();
					break;
			}
			return fragment;
		}
	};

	private SimpleOnPageChangeListener changeListener = new SimpleOnPageChangeListener() {

		public void onPageScrolled(int position, float positionOffset,
								   int positionOffsetPixels) {
			//修改背景透明度
			blankLinear.getBackground().setAlpha((int) (positionOffset * 500));
			//滑动到
			if (position == 0 && positionOffset == 0.0) {
				finish();
			}
		};
	};

	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
	};
}