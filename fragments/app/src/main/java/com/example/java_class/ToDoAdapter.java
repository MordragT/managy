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

public class ToDoAdapter extends ArrayAdapter<Schnittstelle.ToDoEintrag> { //<____________________________________________________________________________________________________________________

    private static final String TAG = "ToDoAdapter";
    private Context mContext;
    int mResource;
    static int aufgerufen = -1; //kann beim aufruf der bearbeiten seite dkeinen intwert übergeben darum hier als static speichern und darauf zugreifen


    public void getOnClickedBox(View vv) {
        //checkbox klick event
        //bekomme raus welche box geklickt wurde
        ListView lv = vv.findViewById(R.id.todo_items_list);


        int position = lv.getPositionForView(vv);


        //change checbox status in den satein
        Test tmp = ToDo.toDoItems.get(position);
        tmp.setB(!tmp.getB());
        ToDo.toDoItems.set(position, tmp);

        //seite neu laden                           vv <-> this
        ToDoAdapter adapter = new ToDoAdapter(vv.getContext(), R.layout.fragment_to_do_adapter, Schnittstelle.toDoListe);
        ToDo.toDoItemList.setAdapter(adapter);
        //ToDoAdapter fragment_ToDoAdapter = new ToDoAdapter(this , R.layout.fragment_ToDoAdapter , toDoItems);

        //test konsolenausgabe
        String t = String.valueOf(position);
        Log.d("checkbox", t);
        //test ausgabe 2
        String t2 = String.valueOf(ToDo.toDoItems.get(position).getB());
        Log.d("checkbox", t2);
    }


    public ToDoAdapter(@NonNull Context context, int resource, ArrayList<Schnittstelle.ToDoEintrag> objects) { // Ändern für jedes dokument
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        String Titel = getItem(position).name;                                    //Ändern für jedes dokument
        boolean boool = getItem(position).erledigt;

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tyName = (TextView) convertView.findViewById(R.id.todo_titel);
        CheckBox tyDone = (CheckBox) convertView.findViewById(R.id.to_do_checkbox);

        tyName.setText(Titel);
        int black = Color.BLACK; // if abfrage ob es haken gesetzt ist wenn ja dan grau machen     //ändern für jedes dokument
        int grey = Color.GRAY;
        if (boool) {
            tyName.setTextColor(grey);
            tyDone.setChecked(true);
        } else
            tyName.setTextColor(black);

        //Aktion lisener für den checkbox buton                                     //ändern für jedes dokument
        tyDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToDo.getOnClickedBox(v, position);
            }
        });
        //Aktion listener für view
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToDo.to_to_onclick_titel(v , position);
                aufgerufen = position;
                Log.d("Onklick", "View");

                ((MainActivity) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new ToDoBearbeiten()).commit();

            }
        });


        return convertView;
    }
}
