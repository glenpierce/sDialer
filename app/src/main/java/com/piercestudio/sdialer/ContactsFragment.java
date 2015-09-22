package com.piercestudio.sdialer;

    import android.app.Fragment;
    import android.os.Bundle;
    import android.view.View;
    import android.view.ViewGroup;
    import android.view.LayoutInflater;

public class ContactsFragment extends Fragment{

        View v;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            v = inflater.inflate(R.layout.contacts_layout, container, false);



            return v;
        }
    }