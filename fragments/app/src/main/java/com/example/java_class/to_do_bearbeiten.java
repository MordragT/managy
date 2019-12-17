package com.example.java_class;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.XmlRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class to_do_bearbeiten extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_to_do_bearbeiten, container, false);

        //setzt den text ins fenster (titel)
        final EditText et = (EditText) v.findViewById(R.id.editText_todo_bearbeiten);
        String s = schnitstelle.to_do_liste.get(to_do_adapter.aufgerufen).name;
        Log.d("aufgerufen",s);
        et.setText(s);



        //onklick listener löchen
        Button loechen = (Button) v.findViewById(R.id.to_do_bearbeiten_loechen);
        loechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //schen
                schnitstelle.to_do_liste.remove(to_do_adapter.aufgerufen);

                //speichere änderungen
                schnitstelle.save_to_do();

                //neue seite laden
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new to_do());
                fr.commit();
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
                schnitstelle.to_do_liste.get(to_do_adapter.aufgerufen).name = s2 ;


                //speichere änderungen
                schnitstelle.save_to_do();

                //seite neu laden
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new to_do());
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



        return v;
    }

}
