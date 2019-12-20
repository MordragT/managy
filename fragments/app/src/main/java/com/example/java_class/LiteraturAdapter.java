package com.example.java_class;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class LiteraturAdapter extends ArrayAdapter<Schnittstelle.LiteraturEintrag> { //<____________________________________________________________________________________________________________________

    private static final String TAG = "literatur_adapter";
    private Context mContext;
    int mRecorce;
    static int aufgerufen = -1;//kann beim aufruf der bearbeiten seite keinen intwert übergeben darum hier als static speichern und darauf zugreifen



    public void literatur_onclick_checkbox(View vv){                                             //checkbox klick event
        //bekomme raus welche box geklickt wurde
        ListView lv = vv.findViewById(R.id.literatur_items_list);

        int position = lv.getPositionForView(vv);


        //change checkbox status in den dateien
        Test tmp = Literatur.literatur_items.get(position);
        tmp.setB(!tmp.getB());
        Literatur.literatur_items.set(position,tmp );

        //seite neu laden                           vv <-> this
        LiteraturAdapter adapter = new LiteraturAdapter(vv.getContext() , R.layout.fragment_literatur_adapter, Schnittstelle.literaturListe);
        Literatur.literatur_items_list.setAdapter(adapter);

        //test konsolenausgabe
        String t  =  String.valueOf(position);
        Log.d("checkbox",t);
        //test ausgabe 2
        String t2 = String.valueOf(Literatur.literatur_items.get(position).getB());
        Log.d("checkbox",t2);
    }








    public LiteraturAdapter(@NonNull Context context, int resource, ArrayList<Schnittstelle.LiteraturEintrag> objects) { // Ändern für jedes dokument
        super(context , resource , objects);
        this.mContext = context;
        this.mRecorce = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        String Titel = getItem(position).name;
        String Autor = getItem(position).autor;
        boolean boool = getItem(position).gelesen;

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mRecorce , parent , false);

        //Autor muss noch hinzugefuegt werden
        TextView tyName = (TextView) convertView.findViewById(R.id.literatur_titel);
        TextView tyAutor = (TextView) convertView.findViewById(R.id.literatur_autor);
        CheckBox tyDone = (CheckBox) convertView.findViewById(R.id.literatur_checkbox);

        tyName.setText(Titel);
        tyAutor.setText(Autor);
        int black = Color.BLACK; // if abfrage ob es haken gesetzt ist wenn ja dan grau machen     //ändern für jedes dokument
        int grey = Color.GRAY;
        if(boool) {
            tyName.setTextColor(grey);
            tyAutor.setTextColor(grey);
            tyDone.setChecked(true);
        }
        else {
            tyName.setTextColor(black);
            tyAutor.setTextColor(black);
        }
        //Aktion listener für den checkbox button                                     //ändern für jedes dokument
        tyDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Literatur.literatur_onclick_checkbox(v , position);
            }
        });
        //Aktion listener für view
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aufgerufen = position;
                Log.d("Onklick","View");

                ((MainActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,new LiteraturBearbeiten()).commit();

            }
        });

        return convertView;
    }
}
