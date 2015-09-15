package com.piercestudio.sdialer;

import android.app.Activity;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;


public class MainActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

		DialPadFragment dialpadFragment = new DialPadFragment();
		fragmentTransaction.add(R.id.dialpadlayout, dialpadFragment);
		fragmentTransaction.commit();
	}
}
