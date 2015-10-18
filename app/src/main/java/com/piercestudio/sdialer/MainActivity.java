package com.piercestudio.sdialer;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity
{

	static Typeface robotoThin;

	FragmentManager fragmentManager = getFragmentManager();
	FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

	DialPadFragment dialpadFragment = new DialPadFragment();
	ContactsFragment contactsFragment = new ContactsFragment();


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		robotoThin = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");

		Button dialButton = (Button) findViewById(R.id.dialbuttonTab);
		dialButton.setTypeface(robotoThin);
		dialButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

				DialPadFragment dialpadFragment = new DialPadFragment();
				fragmentTransaction.replace(R.id.phonescreen, dialpadFragment);
				fragmentTransaction.commit();
			}
		});

		Button contactsButton = (Button) findViewById(R.id.contactsButtonTab);
		contactsButton.setTypeface(robotoThin);
		contactsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

				ContactsFragment contactsFragment = new ContactsFragment();
				fragmentTransaction.replace(R.id.phonescreen, contactsFragment);
				fragmentTransaction.commit();
			}
		});

		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

		DialPadFragment dialpadFragment = new DialPadFragment();

		fragmentTransaction.replace(R.id.phonescreen, dialpadFragment);
		fragmentTransaction.commit();

	}
}
