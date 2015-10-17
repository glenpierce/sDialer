package com.piercestudio.sdialer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class ContactsAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<Contact> mContactsList;
	String TAG = "glen";

	public ContactsAdapter(Context context, List<Contact> contacts){
		mInflater = LayoutInflater.from(context);
		mContactsList = contacts;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		ViewHolder viewHolder;
		if(convertView == null) {
			view = mInflater.inflate(R.layout.contact_item_layout, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.name = (TextView)view.findViewById(R.id.name);
			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder)view.getTag();
		}

		Contact contact = mContactsList.get(position);
		viewHolder.name.setText(contact.getName());

		return view;
	}

	private class ViewHolder {
		public TextView name;
	}

    @Override
    public int getCount(){
		return mContactsList.size();
    }


    @Override
    public long getItemId(int position){
		return position;
    }

    @Override
    public Object getItem(int position){
		return mContactsList.get(position);
    }
}