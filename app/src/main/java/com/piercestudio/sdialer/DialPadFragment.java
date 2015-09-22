package com.piercestudio.sdialer;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.view.LayoutInflater;
import android.widget.TextView;

public class DialPadFragment extends Fragment{

    View v;


    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            v = inflater.inflate(R.layout.dialpadlayout, container, false);

            final TextView phoneNumberEditText = (TextView) v.findViewById(R.id.phonenumber);



            //numpad
            final ImageButton keyPad[] = new ImageButton[10];
            for (int i = 0; i <= 9; i++) {
                keyPad[i] = (ImageButton) v.findViewWithTag(Integer.toString(i));
                if (keyPad[i] == null) {
                    Log.i("asdf", "keypad" + Integer.toString(i) + " is null");
                } else {
                    keyPad[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            phoneNumberEditText.setText(phoneNumberEditText.getText().toString() + v.getTag().toString());
                        }
                    });
                }
            }


                //Delete button - clears the phone number
                ImageButton deleteButton = (ImageButton) v.findViewById(R.id.deletebutton);
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        phoneNumberEditText.setText("");
                    }

                });

                //Dial button - calls the phone intent
                ImageButton dialButton = (ImageButton) v.findViewById(R.id.dialbutton);
                dialButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumberEditText.getText().toString())));
                    }
                });

            return v;
        }
}