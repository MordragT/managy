package com.example.java_class;

import android.app.Activity;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                ((MainActivity)getActivity()).bottomNav.setSelectedItemId(((MainActivity) getActivity()).lastItem);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        View v = inflater.inflate(R.layout.fragment_startseite, container, false);

        FristStartseite(v);
        TerminStartseite(v);
        ToDoStartseite(v);
        LiteraturStartseite(v);
        TippsStartseite(v);

//
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_startseite, container, false);
        return v ;
    }


    private void FristStartseite(View v) {
        TextView FristStart = v.findViewById(R.id.FristStart);

    }

    private void TerminStartseite(View v) {
        TextView TerminStart = v.findViewById(R.id.TerminStart);

        //int Termingröße = Schnittstelle.terminListe.size();
        //TerminStart.setText(Termingröße);
    }

    private void ToDoStartseite(View v) {
        TextView ToDoStart = v.findViewById(R.id.ToDoStart);
        if (Schnittstelle.toDoListe.size() == 0) {
            //String ToDo = "Du hast aktuell keine Aufgaben :D";
            String ToDo = "0/0";
            ToDoStart.setText(ToDo);

        } else {
            int counter = 0;
            for (int i=0; i < Schnittstelle.toDoListe.size(); i++){
                if(Schnittstelle.toDoListe.get(i).erledigt){
                    counter++;
                }
            }

            //if (counter == Schnittstelle.toDoListe.size()){
                //String ToDo = "Du hast alle Aufgaben erledigt :D";
                //ToDoStart.setText(ToDo);
            //}
            //else{
                String ToDo = counter + "/" + Schnittstelle.toDoListe.size();
                ToDoStart.setText(ToDo);
            //}
        }
    }

    private void LiteraturStartseite(View v) {
        TextView LiteraturStart = v.findViewById(R.id.LiteraturStart);
        if (Schnittstelle.literaturListe.size() == 0) {
            //String Literatur = "Du hast aktuell keine Literatur :D";
            String Literatur = "0/0";
            LiteraturStart.setText(Literatur);

        } else {

            int counter = 0;
            for (int i = 0; i < Schnittstelle.literaturListe.size(); i++) {
                if (Schnittstelle.literaturListe.get(i).gelesen) {
                    counter++;
                }
            }

            //if (counter == Schnittstelle.literaturListe.size()) {
            //String Literatur = "Du hast alles gelesen :D";
            //LiteraturStart.setText(Literatur);
            //} else {
                String Literatur = counter + "/" + Schnittstelle.literaturListe.size();
                LiteraturStart.setText(Literatur);
            //}
        }
    }

    private void TippsStartseite(View v) {
        TextView TippsStartseite = v.findViewById(R.id.TippsStart);

        int randomIndex = new Random().nextInt((getResources().getStringArray(R.array.TippsArray)).length);
        String randomTipp = (getResources().getStringArray(R.array.TippsArray))[randomIndex];
        TippsStartseite.setText(randomTipp);
    }


}
