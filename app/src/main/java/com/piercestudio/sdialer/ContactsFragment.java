package com.piercestudio.sdialer;

import android.support.v4.app.Fragment;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        v = inflater.inflate(R.layout.contacts_list_layout, container, false);
        ListView listView = (ListView) v.findViewById(R.id.contacts_list_layout_id);

		contentResolver = getActivity().getContentResolver();
		//Stop being lazy and finish the .query method call null, null, null, null is not acceptable
		Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
//		String[] projection = new String[] {
//				ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
//				ContactsContract.CommonDataKinds.Phone.NUMBER};
        cursor = contentResolver.query(uri, null, null, null, null);

		while (cursor.moveToNext()) {
			Log.i(TAG, cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
			if(cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))>0) {
				if (!cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)).contains("YouMail")) {
					String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
					String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

					int numberType = cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
					String type = "";
					switch (numberType){
						case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
							type = "Home";
							break;
						case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
							type = "Mobile";
							break;
						case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
							type = "Work";
							break;
						case ContactsContract.CommonDataKinds.Phone.TYPE_CUSTOM:
							type = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.LABEL));
							break;
						default:
							break;
					}

					Contact contact = new Contact();
					contact.setName(name);
					contact.setPhoneNumber(phoneNumber);
					contact.setType(type);

					contactsList.add(contact);
					Log.i(TAG, contact.getName());
				}
			}
        }
		cursor.close();

		Collections.sort(contactsList);

		ContactsAdapter contatctsAdapter = new ContactsAdapter(getActivity(), contactsList);
		listView.setAdapter(contatctsAdapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Log.i(TAG, contactsList.get(position).getName());
				startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contactsList.get(position).getPhoneNumber())));
			}
		});

		return v;
    }

}