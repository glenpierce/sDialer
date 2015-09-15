package com.piercestudio.sdialer;

import android.app.Activity;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.text.Layout;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import org.w3c.dom.Text;

public class DialPadFragment extends Fragment
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		LinearLayout mlinearlayout = new LinearLayout(getActivity());
//		mlinearlayout getView().findViewById(R.id.linearlayout);
		final TextView phoneNumber = (TextView) container.findViewById(R.id.phonenumber);


		final ImageButton keyPad[] = new ImageButton[10];

		for (int i = 0; i <= 9; i++)
		{
			Log.i("asdf", Integer.toString(i));
			keyPad[i] = (ImageButton) container.findViewWithTag(Integer.toString(i));
			keyPad[i].setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					phoneNumber.setText(phoneNumber.getText().toString() + v.getTag().toString());
				}
		});
		}

		ImageButton deleteButton = (ImageButton) container.findViewById(R.id.deletebutton);
		deleteButton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v)
				{
					phoneNumber.setText("");
				}

		});

		ImageButton dialButton = (ImageButton) container.findViewById(R.id.dialbutton);
		dialButton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v)
				{
					startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber.getText())));
				}
		});

		return inflater.inflate(R.layout.dialpadlayout, container, false);
	}





//	OnClickListener keyPress = new OnClickListener()
//	{
//		public void onClick(View v)
//		{
//			if (v.getTag().equals("deletebutton"))
//			{
//				phoneNumber.setText("");
//			}else
//			{
//			if (v.getTag().equals("dialbutton"))
//			{
//				startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber.getText())));
//			}else {
////				if (phoneNumber.getText().toString().equals(getString(R.string.phonenumber)))
////				{
////					phoneNumber.setText(v.getTag().toString());
////				}else {
//					phoneNumber.setText(phoneNumber.getText().toString() + v.getTag().toString());
////				}
//				}
			}