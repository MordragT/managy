package com.example.java_class;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;
import java.lang.String;

import android.view.View;
import android.widget.TextView;


public class Startseite extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_startseite, container, false);
    }

    // Tests:

    //TextView testlol = (TextView) findViewById(R.id.TippsStart);
    //testlol.setText("klappt!")

    //String[] array = getResources().getStringArray(R.array.TippsArray);
    //String randomTipp = array[new Random().nextInt(array.length)];

    //String[] myArrayOfStrings = {"one", "two", "three" };
    //Random r = new Random();
    //String myRandString = r.nextInt(myArrayOfStrings.length);
}
