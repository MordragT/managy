package com.example.java_class;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class KalenderDayAdapter extends ArrayAdapter {

    LayoutInflater inflater;
    //ArrayList<Schnittstelle.TerminEintrag> terminArray;

    public KalenderDayAdapter(@NonNull Context context, ArrayList<Integer> objects) {// ArrayList<Schnittstelle.TerminEintrag> terminArray) {
        super(context, R.layout.fragment_kalender_day, objects);
        inflater = LayoutInflater.from(context);
        //this.terminArray = terminArray;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;
        if (v == null) v = inflater.inflate(R.layout.fragment_kalender_day_adapter, parent, false);

        TextView currentHour = v.findViewById(R.id.current_hour);
        TextView terminName = v.findViewById(R.id.termin_name);

        currentHour.setText(getItem(position).toString() + ":00");
/*
        Zeit itemHour = new Zeit(((int) getItem(position)), 0);
        for(Schnittstelle.TerminEintrag termin : terminArray) {
            Log.d("ZEIT", "Zeit: " + termin.beginnZeit);
            if(termin.beginnZeit.compareTo(itemHour) <= 0 && termin.endeZeit.compareTo(itemHour) >= 0) {
                switch (termin.farbe) {
                    case "rot":
                        terminName.setBackgroundColor(getContext().getResources().getColor(R.color.red));
                        break;
                    case "gelb":
                        terminName.setBackgroundColor(getContext().getResources().getColor(R.color.yellow));
                        break;
                    case "blau":
                        terminName.setBackgroundColor(getContext().getResources().getColor(R.color.blue));
                        break;
                    case "grün":
                        terminName.setBackgroundColor(getContext().getResources().getColor(R.color.green));
                        break;
                }
                terminName.setText(termin.name);
            }
         */
        return v;
    }
}
