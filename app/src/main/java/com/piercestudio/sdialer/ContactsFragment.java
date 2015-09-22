package com.piercestudio.sdialer;

    import android.app.Fragment;
    import android.content.Intent;
    import android.net.Uri;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
    import android.view.LayoutInflater;
    import android.widget.TextView;

    public class ContactsFragment extends Fragment{

        View v;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            v = inflater.inflate(R.layout.contactslayout, container, false);



            return v;
        }
    }