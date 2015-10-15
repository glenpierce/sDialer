package com.piercestudio.sdialer;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.database.Cursor;
import java.net.URL;
import android.content.ContentResolver;
import android.provider.ContactsContract;
import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Collections;


public class ContactsFragment extends Fragment
{

    ArrayList<String> contactsArray = new ArrayList<>();
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
        cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            //String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            //Change String name to a custom class with an embeded onclick listner. Therefore contactsArray would be ArrayList<ContactItem>
            //add the name and phone number to the custom ContactItem, figure out how to get the OnClickListener to link to the correct contact

            contactsArray.add(name);
        }
        cursor.close();

        Collections.sort(contactsArray);

        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, contactsArray);

        listView.setAdapter(adapter);

        return v;
    }

}
