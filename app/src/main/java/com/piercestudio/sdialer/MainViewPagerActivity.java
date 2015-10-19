package com.piercestudio.sdialer;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;


public class MainViewPagerActivity extends FragmentActivity
{

	String TAG = "glen";

	static Typeface robotoThin;
	ViewPager viewPager;
	MyPagerAdapter myPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager_activity_main);

		robotoThin = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");

		viewPager = (ViewPager) findViewById(R.id.viewPager);
		myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(myPagerAdapter);

		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
		{

			@Override
			public void onPageSelected(int index)
			{
				if(index == 0){
					Log.i(TAG, "contacts fragement selected");
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2)
			{
			}

			@Override
			public void onPageScrollStateChanged(int arg0)
			{
			}
		});

	}

	@Override
	public void onResume(){
		Log.i(TAG, "onResume");
		super.onResume();
	}



	private class MyPagerAdapter extends FragmentPagerAdapter
	{

		public MyPagerAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
		}

		@Override
		public android.support.v4.app.Fragment getItem(int pos) {
			switch(pos) {
				case 0:
					Log.i(TAG, "Contacts page");
					return new ContactsFragment();
				case 1:
					Log.i(TAG, "Dial page");
					return new DialPadFragment();
			}
			return null;
		}

		@Override
		public int getCount() {
			return 2;
		}
	}
}
