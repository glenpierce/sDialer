package com.piercestudio.sdialer;

import android.app.Activity;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity
{

	FragmentManager fragmentManager = getFragmentManager();
	FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

	DialPadFragment dialpadFragment = new DialPadFragment();
	ContactsFragment contactsFragment = new ContactsFragment();


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button testButton = (Button) findViewById(R.id.test);

		Button dialButton = (Button) findViewById(R.id.dialbuttonTab);
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



	}
}
