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


public class literatur_bearbeiten extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_literatur_bearbeiten, container, false);

        //setzt den text ins fenster
        final EditText titel = (EditText) v.findViewById(R.id.literatur_bearbeiten_EditTitel);//um eingetragene werte zu bekommen
        final EditText autor =(EditText) v.findViewById(R.id.literatur_bearbeiten_EditAutor);
        final EditText url=(EditText) v.findViewById(R.id.literatur_bearbeiten_EditURL);
        final EditText notizen=(EditText) v.findViewById(R.id.literatur_bearbeiten_EditNotizen);

        String s1 = schnitstelle.literatur_liste.get(literatur_adapter.aufgerufen).name;
        String s2 = schnitstelle.literatur_liste.get(literatur_adapter.aufgerufen).autor;
        String s3 = schnitstelle.literatur_liste.get(literatur_adapter.aufgerufen).url;
        String s4 = schnitstelle.literatur_liste.get(literatur_adapter.aufgerufen).notizen;
       Log.d("aufgerufen",s1);
       // Log.d("aufgerufen",s2);
        //Log.d("aufgerufen",s3);
       // Log.d("aufgerufen",s4);

        titel.setText(s1);
        autor.setText(s2);
        url.setText(s3);
        notizen.setText(s4);

        //onklick listener löschen
        Button loeschen = (Button) v.findViewById(R.id.literatur_bearbeiten_loeschen);
        loeschen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sicherheitsabfrage_literatur n = new sicherheitsabfrage_literatur();
                n.show(getFragmentManager(),"sicherheitsabfrage");
            }
        });


        Button speichern = (Button) v.findViewById(R.id.literatur_bearbeiten_speichern);//für onklicklistener
        final EditText titel1 = (EditText) v.findViewById(R.id.literatur_bearbeiten_EditTitel);//um eingetragene werte zu bekommen
        final EditText autor1 =(EditText) v.findViewById(R.id.literatur_bearbeiten_EditAutor);
        final EditText url1=(EditText) v.findViewById(R.id.literatur_bearbeiten_EditURL);
        final EditText notizen1=(EditText) v.findViewById(R.id.literatur_bearbeiten_EditNotizen);

        speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //neuen eintrag schreiben
                String s1 = titel1.getText().toString();
                String s2 = autor1.getText().toString();
                String s3 = url1.getText().toString();
                String s4 = notizen1.getText().toString();


                if(s1.length() >0 && s2.length()>0){
                    schnitstelle.literatur_liste.get(literatur_adapter.aufgerufen).name = s1 ;
                    schnitstelle.literatur_liste.get(literatur_adapter.aufgerufen).autor = s2 ;
                    schnitstelle.literatur_liste.get(literatur_adapter.aufgerufen).url = s3 ;
                    schnitstelle.literatur_liste.get(literatur_adapter.aufgerufen).notizen = s4 ;


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
                    titel1.setBackgroundColor(error);
                    titel1.setHint("Darf nicht leer sein!");
                    autor1.setBackgroundColor(error);
                    autor1.setHint("Darf nicht leer sein!");
                }
            }
        });

        return v;
    }

}
