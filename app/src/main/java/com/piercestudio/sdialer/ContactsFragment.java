package com.piercestudio.sdialer;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class ContactsFragment extends Fragment {

	String TAG = "glen";

    ArrayList<Contact> contactsList = new ArrayList<Contact>();
	ContentResolver contentResolver;
    Cursor cursor;
    View v;
	private ContactsAdapter contactsAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onResume()
	{
		super.onResume();
		contactsList = getUpdatedContactList(getActivity());
		contactsAdapter.notifyDataSetChanged();
	}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        v = inflater.inflate(R.layout.contacts_list_layout, container, false);
        ListView listView = (ListView) v.findViewById(R.id.contacts_list_layout_id);

		 contactsAdapter = new ContactsAdapter(getActivity(), contactsList);
		listView.setAdapter(contactsAdapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Log.i(TAG, contactsList.get(position).getName());
				DialogFragment confirmCallDialogFragment = ConfirmCallDialogFragment.newInstance(contactsList.get(position));
				confirmCallDialogFragment.show(getFragmentManager(), "string");
			}
		});

		return v;
    }

	public ArrayList<Contact> getUpdatedContactList(Context context)
	{
		contentResolver = context.getContentResolver();
		//Stop being lazy and finish the .query method call null, null, null, null is not acceptable
		Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
//		String[] projection = new String[] {
//				ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
//				ContactsContract.CommonDataKinds.Phone.NUMBER};
		cursor = contentResolver.query(uri, null, null, null, null);
		contactsList.clear();

		while (cursor.moveToNext())
		{
			Log.i(TAG, cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
			if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0
					&& !cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)).contains("YouMail"))
			{
				String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
				String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				int numberType = cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));

				Contact contact = new Contact();
				contact.setName(name);
				contact.setPhoneNumber(phoneNumber);
				contact.setType(getNumberType(numberType));

				contactsList.add(contact);
				Log.i(TAG, contact.getName());
			}
		}
		cursor.close();

		Collections.sort(contactsList);

		return contactsList;
	}

	private String getNumberType(int numberType){
		switch (numberType)
		{
			case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
				return "Home";
			case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
				return "Mobile";
			case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
				return "Work";
			case ContactsContract.CommonDataKinds.Phone.TYPE_CUSTOM:
				return cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.LABEL));
			default:
				return "Other";
		}
	}

}