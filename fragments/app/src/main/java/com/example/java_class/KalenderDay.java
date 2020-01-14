package com.example.java_class;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class KalenderDay extends LinearLayout {

    ListView hourList;
    ArrayList<Integer> hourArray = new ArrayList<>();
    ArrayList<Schnittstelle.TerminEintrag> terminArray = new ArrayList<>();

    public KalenderDay(Context context) { super(context); }

    public KalenderDay(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public KalenderDay(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.fragment_kalender_day, this);

        for(int i = 0; i < 24; i++) hourArray.add(i);

        for(Schnittstelle.TerminEintrag termin : Schnittstelle.terminListe) {
            if(termin.beginn.compareTo(Schnittstelle.current) <= 0 && termin.ende.compareTo(Schnittstelle.current) >= 0) {
                terminArray.add(termin);
            }
        }
        //Log.d("TERMINARRAY",terminArray.toString());
        hourList = v.findViewById(R.id.hour_list);
        KalenderDayAdapter adapter = new KalenderDayAdapter(this.getContext(), hourArray, terminArray);
        hourList.setAdapter(adapter);
    }
}
