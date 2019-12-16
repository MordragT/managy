package com.example.java_class;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.XmlRes;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class to_do_bearbeiten extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_to_do_bearbeiten, container, false);

        //set text
        EditText et = (EditText) v.findViewById(R.id.editText_todo_bearbeiten);
        String s = String.valueOf(to_do_adapter.aufgerufen);
        Log.d("aufgerufen",s);
        et.setText(s);
        //et.setText("Test");




        return v;
    }

}
