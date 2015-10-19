package com.piercestudio.sdialer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;


public class ConfirmCallDialogFragment extends DialogFragment
{

	String contactName;
	String contactNumber;
	String contactNumberType;

	static ConfirmCallDialogFragment newInstance(Contact contact) {
		ConfirmCallDialogFragment confirmCallDialogFragment = new ConfirmCallDialogFragment();

		Bundle bundle = new Bundle();
		bundle.putString("name", contact.getName());
		bundle.putSerializable("number", contact.getPhoneNumber());
		bundle.putString("numbertype", contact.getType());
		confirmCallDialogFragment.setArguments(bundle);

		return confirmCallDialogFragment;
	}


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		contactName = getArguments().getString("name");
		contactNumber = getArguments().getString("number");
		contactNumberType = getArguments().getString("numbertype");
	}


	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT);
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View dialogView = inflater.inflate(R.layout.confirm_call_dialog, null);

		TextViewWithFont name = (TextViewWithFont) dialogView.findViewById(R.id.contactName);
		name.setText(contactName);
		TextViewWithFont type = (TextViewWithFont) dialogView.findViewById(R.id.contactNumberType);
		type.setText(contactNumberType);
		TextViewWithFont number = (TextViewWithFont) dialogView.findViewById(R.id.contactNumber);
		number.setText(contactNumber);


		ImageButton callButton = (ImageButton) dialogView.findViewById(R.id.callImageButton);
		callButton.setOnClickListener(callOnClick);

		ImageButton cancelButton = (ImageButton) dialogView.findViewById(R.id.cancelImageButton);
		cancelButton.setOnClickListener(cancelOnClick);


		builder.setView(dialogView);

		builder.setMessage(null)
		.setPositiveButton(null, null)
		.setNegativeButton(null, null);

		Dialog dialog = builder.create();
		return dialog;
	}

	View.OnClickListener callOnClick = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contactNumber)));
			dismiss();
		}
	};

	View.OnClickListener cancelOnClick = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			dismiss();
		}
	};

} 