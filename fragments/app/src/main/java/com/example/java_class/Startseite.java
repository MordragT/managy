package com.example.java_class;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.Random;
import java.lang.String;
import java.lang.reflect.Array;

import android.view.View;
import android.widget.TextView;



public class Startseite extends Fragment {

    //TextView FristStart = getActivity().findViewById(R.id.FristStart);
    //TextView TerminStart = getActivity().findViewById(R.id.TerminStart);
    //TextView ToDoStart = getActivity().findViewById(R.id.ToDoStart);
    //TextView LiteraturStart = getActivity().findViewById(R.id.LiteraturStart);
    //TextView TippsStart = getActivity().findViewById(R.id.TippsStart);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Test 1
        // siehe ... https://stackoverflow.com/questions/17076663/problems-with-settext-in-a-fragment-in-oncreateview

        inflater = getActivity().getLayoutInflater();
        View view =  inflater.inflate(R.layout.fragment_startseite, container, false); //pass the correct layout name for the fragment

        TextView TippsStartseite = view.findViewById(R.id.TippsStart);
        String test = "klappt";
        TippsStartseite.setText(test);

        // Test 1 Ende 


        //FristStartseite();
        //TerminStartseite();
        //ToDoStartseite();
        //LiteraturStartseite();
        //TippsStartseite();
//
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_startseite, container, false);
    }

    public void FristStartseite() {
        TextView FristStart = getActivity().findViewById(R.id.FristStart);
    }

    public void TerminStartseite() {
        TextView TerminStart = getActivity().findViewById(R.id.TerminStart);
        TerminStart.setText(Schnittstelle.terminListe.size());
    }

    public void ToDoStartseite() {
        TextView ToDoStart = getActivity().findViewById(R.id.ToDoStart);
        ToDoStart.setText(Schnittstelle.toDoListe.size());
    }

    public void LiteraturStartseite(){
        TextView LiteraturStart = getActivity().findViewById(R.id.LiteraturStart);
        LiteraturStart.setText(Schnittstelle.literaturListe.size());
    }

    public void TippsStartseite() {
        TextView TippsStartseite = getActivity().findViewById(R.id.TippsStart);
        TippsStartseite.setText("klappt");
        //TippsStart.setText(getResources().getString(R.string.TippStart));

        //int randomIndex = new Random().nextInt((getResources().getStringArray(R.array.TippsArray)).length);
        //String randomTipp = (getResources().getStringArray(R.array.TippsArray))[randomIndex];
        //TippsStart.setText(randomTipp);

        //Random r = new Random();
        //String myRandString = r.nextInt(R.array.TippsArray.);

    }


}
