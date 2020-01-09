package com.example.java_class;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class FristenAdapter extends ArrayAdapter<Schnittstelle.FristenEintrag> { //<____________________________________________________________________________________________________________________

    private Context mContext;
    private int mResource;
    static int aufgerufen = -1;


    /*
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
     */


    public FristenAdapter(@NonNull Context context, int resource, ArrayList<Schnittstelle.FristenEintrag> objects) { // Ändern für jedes dokument
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        String titel = getItem(position).name;
        Datum termin = getItem(position).termin;
        boolean bool = getItem(position).erinnern;

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tyName = (TextView) convertView.findViewById(R.id.fristen_titel);
        TextView tyTermin = (TextView) convertView.findViewById(R.id.fristen_termin);

        tyName.setText(titel);
        tyTermin.setText(termin.toString());
        int black = Color.BLACK; // if abfrage ob es haken gesetzt ist wenn ja dan grau machen     //ändern für jedes dokument
        int grey = Color.GRAY;
        if (bool) {
            tyName.setTextColor(grey);
        } else
            tyName.setTextColor(black);

        //Aktion listener für view
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aufgerufen = position;
                Log.d("Onklick", "View");

                ((MainActivity) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new FristenBearbeiten()).commit();

            }
        });


        return convertView;
    }
}
