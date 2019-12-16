package com.example.java_class;

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

import java.util.ArrayList;

public class to_do_adapter extends ArrayAdapter<Test> { //<____________________________________________________________________________________________________________________

    private static final String TAG = "to_do_adapter";
    private Context mContext;
    int mRecorce;
    static int aufgerufen = -1;//kann beim aufruf der bearbeiten seite dkeinen intwert übergeben darum hier als static speichern und darauf zugreifen



     public void to_to_onclick_checkbox(View vv){                                             //checkbox klick event
        //bekomme raus welche box geklickt wurde
        ListView lv = vv.findViewById(R.id.todo_items_list);


        int position = lv.getPositionForView(vv);


        //change checbox status in den satein
        Test tmp = to_do.todo_items.get(position);
        tmp.setB(!tmp.getB());
        to_do.todo_items.set(position,tmp );

        //seite neu laden                           vv <-> this
        to_do_adapter adapter = new to_do_adapter(vv.getContext() , R.layout.fragment_to_do_adapter, to_do.todo_items);
        to_do.todo_items_list.setAdapter(adapter);
        //to_do_adapter fragment_to_do_adapter = new to_do_adapter(this , R.layout.fragment_to_do_adapter , todo_items);

        //test konsolenausgabe
        String t  =  String.valueOf(position);
        Log.d("checkbox",t);
        //test ausgabe 2
        String t2 = String.valueOf(to_do.todo_items.get(position).getB());
        Log.d("checkbox",t2);
    }








    public to_do_adapter(@NonNull Context context, int resource, ArrayList<Test> objects) { // Ändern für jedes dokument
        super(context , resource , objects);


        this.mContext = context;
        this.mRecorce = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String Titel = getItem(position).getTitel();                                    //Ändern für jedes dokument
        boolean boool = getItem(position).getB();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mRecorce , parent , false);

        TextView tyName = (TextView) convertView.findViewById(R.id.todo_titel);
        CheckBox tyDone = (CheckBox) convertView.findViewById(R.id.to_do_checkbox);

        tyName.setText(Titel);
        int black = Color.BLACK; // if abfrage ob es haken gesetzt ist wenn ja dan grau machen     //ändern für jedes dokument
        int grey = Color.GRAY;
        if(boool) {
            tyName.setTextColor(grey);
            tyDone.setChecked(true);
        }
        else
            tyName.setTextColor(black);

        //Aktion lisener für den checkbox buton                                     //ändern für jedes dokument
        tyDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to_do.to_to_onclick_checkbox(v , position);
            }
        });
        //Aktion listener für view
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to_do.to_to_onclick_titel(v , position);
                Log.d("Onklick","View");
            }
        });



        return convertView;
    }
}
