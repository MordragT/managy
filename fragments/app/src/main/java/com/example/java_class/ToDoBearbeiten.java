package com.example.java_class;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.XmlRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class ToDoBearbeiten extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_to_do_bearbeiten, container, false);

        //setzt den text ins fenster (titel)
        final EditText et = (EditText) v.findViewById(R.id.editText_todo_bearbeiten);
        String s = Schnittstelle.toDoListe.get(ToDoAdapter.aufgerufen).name;
        Log.d("aufgerufen",s);
        et.setText(s);



        //onklick listener löchen
        Button loechen = (Button) v.findViewById(R.id.to_do_bearbeiten_loechen);
        loechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToDoSicherheitsabfrage n = new ToDoSicherheitsabfrage();
                n.show(getFragmentManager(),"sicherheitsabfrage");
            }
        });


        Button speichern = (Button) v.findViewById(R.id.to_do_bearbeiten_Speichern);//für onklicklistener
        final EditText titel = (EditText) v.findViewById(R.id.to_do_add_EditText);//um eingetragene werte zu bekommen
        speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //neuen eintrag schreiben
                String s2 = et.getText().toString();
                if(s2.length() >0){
                Schnittstelle.toDoListe.get(ToDoAdapter.aufgerufen).name = s2 ;


                //speichere änderungen
                Schnittstelle.saveToDo();

                //seite neu laden
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new ToDo());
                fr.commit();
            }
                else
            {
                int error = getResources().getColor(R.color.Error);
                et.setBackgroundColor(error);
                et.setHint("darf nicht leer sein");
            }
            }
        });


        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment, new ToDo());
                fr.commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
        return v;
    }

}
