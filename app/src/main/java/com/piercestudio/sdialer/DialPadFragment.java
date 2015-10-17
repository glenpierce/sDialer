package com.piercestudio.sdialer;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

public class DialPadFragment extends Fragment
{

	String TAG = "asdf";
	View v;
	EditText phoneNumberEditText;
	boolean thereIsALeading1 = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		v = inflater.inflate(R.layout.dialpadlayout, container, false);

		phoneNumberEditText = (EditText) v.findViewById(R.id.phonenumber);

		//numpad
		final ImageButton keyPad[] = new ImageButton[10];
		for (int i = 0;i <= 9;i++)
		{
			keyPad[i] = (ImageButton) v.findViewWithTag(Integer.toString(i));
			if (keyPad[i] == null)
			{
				Log.i("asdf", "keypad" + Integer.toString(i) + " is null");
			}else
			{


				keyPad[i].setOnClickListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						if (thisIsAPreceding1(v))
						{
							addDigit(v);
						}else
						{
							if (thereShouldBeADash(v))
							{
								addDash();
							}
							addDigit(v);
						}
					}
				});
			}
		}

		//Star and Hash keys
		final ImageButton starKey = (ImageButton) v.findViewWithTag("*");
		starKey.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
//				phoneNumberEditText.setText(phoneNumberEditText.getText().toString() + v.getTag().toString());
			}
		});

		final ImageButton hashKey = (ImageButton) v.findViewWithTag("#");
		hashKey.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				//phoneNumberEditText.setText(phoneNumberEditText.getText().toString() + v.getTag().toString());
			}
		});

		//Delete button
		final ImageButton deleteButton = (ImageButton) v.findViewById(R.id.deletebutton);
		deleteButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (phoneNumberIsNotNullAndNotZeroLength())
				{
					deleteLastDigit();
					if (phoneNumberIsNotNullAndNotZeroLength() && lastDigitIsDash())
					{
						Log.i(TAG, "lastdigitisdash");
						deleteLastDigit();
					}
				}
				if (phoneNumberEditText.getText().toString().length() == 0)
				{
					thereIsALeading1 = false;
				}
			}
		});

		//Dial button - calls the phone intent
		ImageButton dialButton = (ImageButton) v.findViewById(R.id.dialbutton);
		dialButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumberEditText.getText().toString())));
			}
		});

		return v;
	}

	private boolean thisIsAPreceding1(View v)
	{
		if (v.getTag().toString().equals("1") && phoneNumberEditText.getText().toString().length() == 0) {
			thereIsALeading1 = true;
			return true;
		}
		return false;
	}

	private boolean thereShouldBeADash(View v)
	{
		if (thereIsALeading1 && (phoneNumberEditText.getText().toString().length() == 1 || phoneNumberEditText.getText().toString().length() == 5 || phoneNumberEditText.getText().toString().length()
				== 9)) {
			return true;
		}
		if (!thereIsALeading1 && (phoneNumberEditText.getText().toString().length() == 3 || phoneNumberEditText.getText().toString().length() == 7)) {
			return true;
		}
		return false;
	}

	private boolean phoneNumberIsNotNullAndNotZeroLength()
	{
		return phoneNumberEditText.getText() != null && phoneNumberEditText.getText().toString().length() != 0;
	}

	private void addDash()
	{
		phoneNumberEditText.setText(phoneNumberEditText.getText().toString() + "-");
	}

	private void addDigit(View v)
	{
		phoneNumberEditText.setText(phoneNumberEditText.getText().toString() + v.getTag().toString());
	}

	private boolean lastDigitIsDash()
	{
		if (phoneNumberEditText.getText().toString().substring(phoneNumberEditText.getText().toString().length() - 1).equals("-"))
		{
			return true;
		}
		return false;
	}

	private void deleteLastDigit()
	{
		phoneNumberEditText.setText(phoneNumberEditText.getText().toString().substring(0, phoneNumberEditText.length() - 1));
	}
}