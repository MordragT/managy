package com.example.java_class;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class ToDoAdd extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_to_do_add, container, false);

        //Onklick listener
        Button abbrechen = (Button) v.findViewById(R.id.to_do_add_abbrechen);
        abbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToDo.to_to_onclick_titel(v , position);
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment, new ToDo());
                fr.commit();
            }
        });


        Button speichern = (Button) v.findViewById(R.id.to_do_add_speichern);//für onklicklistener
        final EditText titel = (EditText) v.findViewById(R.id.to_do_add_EditText);//um eingetragene werte zu bekommen
        speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //eingestelte werte auslesen
                String s = titel.getText().toString();
                if (s.length() > 0) {
                    //neuen eintrag schreiben
                    Schnittstelle.ToDoEintrag e = new Schnittstelle().new ToDoEintrag();
                    e.name = s;
                    Schnittstelle.toDoListe.add(e);

                    //speichere änderungen
                    Schnittstelle.saveToDo();

                    //seite neu laden
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.nav_host_fragment, new ToDo());
                    fr.commit();
                } else {
                    int error = getResources().getColor(R.color.Error);
                    titel.setBackgroundColor(error);
                    titel.setHint("darf nicht leer sein");
                }
            }
        });


        return v;
    }

}
