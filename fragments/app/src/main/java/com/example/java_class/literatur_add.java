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


public class literatur_add extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_literatur_add, container, false);

        //Onklick listener
        Button abbrechen = (Button) v.findViewById(R.id.literatur_add_abbrechen);
        abbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to_do.to_to_onclick_titel(v , position);
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new to_do());
                fr.commit();
            }
        });

        Button speichern = (Button) v.findViewById(R.id.literatur_add_speichern);//für onklicklistener
        final EditText titel = (EditText) v.findViewById(R.id.literatur_add_EditTitel);//um eingetragene werte zu bekommen
        final EditText autor =(EditText) v.findViewById(R.id.literatur_add_EditAutor);
        final EditText url=(EditText) v.findViewById(R.id.literatur_add_EditURL);
        final EditText notizen=(EditText) v.findViewById(R.id.literatur_add_EditNotizen);

        speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //eingestelte werte auslesen
                //Pflichtfelder
                String s1 = titel.getText().toString(); //Pflichtfeld
                String s2 =autor.getText().toString();  //Pflichtfeld
                String s3 =url.getText().toString();
                String s4 =notizen.getText().toString();


                if(s1.length()> 0 && s2.length()>0 ){
                    //neuen eintrag schreiben
                    schnitstelle.literatur_eintrag e = new  schnitstelle().new literatur_eintrag();
                    e.name = s1;
                    e.autor=s2;
                    e.url=s3;
                    e.notizen=s4;
                    schnitstelle.literatur_liste.add(e);

                    //speichere änderungen
                    schnitstelle.save_literatur();

                    //seite neu laden
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.nav_host_fragment,new literatur());
                    fr.commit();
                }
                else
                {
                    int error = getResources().getColor(R.color.Error);
                    titel.setBackgroundColor(error);
                    titel.setHint("Darf nicht leer sein!");
                    autor.setBackgroundColor(error);
                    autor.setHint("Darf nicht leer sein!");
                }
            }
        });


        return v;
    }

}
