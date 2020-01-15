package com.example.java_class;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class KalenderDay extends LinearLayout {

    LinearLayout hourList;
    //ArrayList<Integer> hourArray = new ArrayList<>();
    //ArrayList<Schnittstelle.TerminEintrag> terminArray = new ArrayList<>();

    public KalenderDay(Context context) { super(context); }

    public KalenderDay(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public KalenderDay(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.fragment_kalender_day, this);
        FrameLayout frameLayout = v.findViewById(R.id.termine);

        //for(int i = 0; i < 24; i++) hourArray.add(i);
        hourList = v.findViewById(R.id.hour_list);
        //KalenderDayAdapter adapter = new KalenderDayAdapter(this.getContext(), hourArray);
        //hourList.setAdapter(adapter);
        for(int i = 0; i < 24; i++) {
            TextView textView = new TextView(context);
            textView.setText(i + ":00");
            textView.setTextSize(30);
            View view = new View(context);
            view.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 8));
            view.setBackgroundResource(R.color.black);
            hourList.addView(textView);
            hourList.addView(view);
        }

        for(Schnittstelle.TerminEintrag termin : Schnittstelle.terminListe) {
            if(termin.beginn.compareTo(Schnittstelle.current) <= 0 && termin.ende.compareTo(Schnittstelle.current) >= 0) {
                //terminArray.add(termin);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(getDp(96),getHeight(termin.beginnZeit, termin.endeZeit));
                layoutParams.setMarginStart(getDp(192));
                layoutParams.setMargins(getDp(192),getMarginTop(termin.beginnZeit),0,0);
                TextView textView = new TextView(context);
                textView.setLayoutParams(layoutParams);
                textView.setText(termin.name);
                textView.setTextSize(24);
                switch (termin.farbe) {
                    case "rot":
                        textView.setBackgroundColor(getContext().getResources().getColor(R.color.red));
                        textView.setTextColor(getResources().getColor(R.color.white));
                        break;
                    case "gelb":
                        textView.setBackgroundColor(getContext().getResources().getColor(R.color.yellow));
                        textView.setTextColor(getResources().getColor(R.color.black));
                        break;
                    case "blau":
                        textView.setBackgroundColor(getContext().getResources().getColor(R.color.blue));
                        textView.setTextColor(getResources().getColor(R.color.white));
                        break;
                    case "grün":
                        textView.setBackgroundColor(getContext().getResources().getColor(R.color.green));
                        textView.setTextColor(getResources().getColor(R.color.black));
                        break;
                }
                //hourList.addView(textView);
                frameLayout.addView(textView);
            }
        }
        //Log.d("TERMINARRAY",terminArray.toString());
    }


    public int getHeight(Zeit beginn, Zeit ende) {
        return getDp(((ende.stunden - beginn.stunden) * 43) + ((ende.minuten - beginn.minuten) * 43/60));
    }
    public int getMarginTop(Zeit beginn) {
        return getDp((beginn.stunden * 43) + (beginn.minuten * 43/60));
    }

    public int getDp(int px) {
        Resources r = getContext().getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                px,
                r.getDisplayMetrics()
        );
    }
}
