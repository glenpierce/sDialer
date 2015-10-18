package com.piercestudio.sdialer;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;


public class MainViewPagerActivity extends FragmentActivity
{

	static Typeface robotoThin;

//	FragmentManager fragmentManager = getFragmentManager();
//	FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//	DialPadFragment dialpadFragment = new DialPadFragment();
//	ContactsFragment contactsFragment = new ContactsFragment();


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager_activity_main);

		robotoThin = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");

		ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
		pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

//		Button dialButton = (Button) findViewById(R.id.dialbuttonTab);
//		dialButton.setTypeface(robotoThin);
//		dialButton.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				FragmentManager fragmentManager = getFragmentManager();
//				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//				DialPadFragment dialpadFragment = new DialPadFragment();
//				fragmentTransaction.replace(R.id.phonescreen, dialpadFragment);
//				fragmentTransaction.commit();
//			}
//		});
//
//		Button contactsButton = (Button) findViewById(R.id.contactsButtonTab);
//		contactsButton.setTypeface(robotoThin);
//		contactsButton.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				FragmentManager fragmentManager = getFragmentManager();
//				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//				ContactsFragment contactsFragment = new ContactsFragment();
//				fragmentTransaction.replace(R.id.phonescreen, contactsFragment);
//				fragmentTransaction.commit();
//			}
//		});
//
//		FragmentManager fragmentManager = getFragmentManager();
//		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//		DialPadFragment dialpadFragment = new DialPadFragment();
//
//		fragmentTransaction.replace(R.id.phonescreen, dialpadFragment);
//		fragmentTransaction.commit();

	}



	private class MyPagerAdapter extends FragmentPagerAdapter
	{

		public MyPagerAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
		}

		@Override
		public android.support.v4.app.Fragment getItem(int pos) {
			switch(pos) {
				case 0: return new ContactsFragment();
				case 1: return new DialPadFragment();
			}
			return null;
		}

		@Override
		public int getCount() {
			return 2;
		}
	}
}
