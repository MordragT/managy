package com.example.java_class;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;

public class KalenderWeekDay extends Fragment {

    TabLayout tabLayout;

    public KalenderWeekDay() {
        super();
    }

    public KalenderWeekDay(int day) {
        super();
        Schnittstelle.lastDay = Schnittstelle.currentDay;
        Schnittstelle.currentDay = day;
        Calendar cal = Calendar.getInstance();
        cal.set(Schnittstelle.current.jahr,Schnittstelle.current.monat - 1,Schnittstelle.current.tag);
        //Log.d("CAL", "Calendar: " + cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, (Schnittstelle.currentDay - Schnittstelle.lastDay));
        Schnittstelle.current = new Datum(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kalender_week_day, container, false);
        /*
        Log.d("DATUM","FirstDay: " + Schnittstelle.lastDay);
        Log.d("DATUM","CurrentDay: " + Schnittstelle.currentDay);
        Log.d("DATUM", "CurrentDatum: " + Schnittstelle.current);
         */
        tabLayout = ((MainActivity)getActivity()).tabLayout;
        tabLayout.setVisibility(View.VISIBLE);

        com.google.android.material.floatingactionbutton.FloatingActionButton fab = (com.google.android.material.floatingactionbutton.FloatingActionButton) v.findViewById(R.id.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to_do.to_to_onclick_titel(v , position);
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new KalenderAdd());
                fr.commit();
            }
        });

        ((MainActivity)getActivity()).setActionBarTitle("Kalender Wochen-/Tagesansicht");

        return v;
    }

    @Override
    public void onStop() {
        super.onStop();
        tabLayout.setVisibility(View.GONE);
    }
}
