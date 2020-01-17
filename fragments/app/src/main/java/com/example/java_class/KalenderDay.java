package com.example.java_class;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;

public class KalenderDay extends LinearLayout {

    LinearLayout hourList;
    //ArrayList<Integer> hourArray = new ArrayList<>();
    ArrayList<Schnittstelle.TerminEintrag> terminArray = new ArrayList<>();

    public KalenderDay(Context context) {
        super(context);
    }

    public KalenderDay(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public KalenderDay(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.fragment_kalender_day, this);
        FrameLayout frameLayout = v.findViewById(R.id.termine);
        ScrollView scrollView = v.findViewById(R.id.scroll_view);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels - getPx(16);
        scrollView.setMinimumWidth(width);

        //for(int i = 0; i < 24; i++) hourArray.add(i);
        hourList = v.findViewById(R.id.hour_list);
        //KalenderDayAdapter adapter = new KalenderDayAdapter(this.getContext(), hourArray);
        //hourList.setAdapter(adapter);
        for (int i = 0; i < 24; i++) {
            final TextView textView = new TextView(context);
            textView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 120));
            textView.setText(i + ":00");
            textView.setTextSize(30);
            View view = new View(context);
            view.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 8));
            view.setBackgroundResource(R.color.black);
            hourList.addView(textView);
            hourList.addView(view);
        }

        for (Schnittstelle.TerminEintrag termin : Schnittstelle.terminListe) {
            if (termin.beginn.compareTo(Schnittstelle.current) <= 0 && termin.ende.compareTo(Schnittstelle.current) >= 0) {
                terminArray.add(termin);
            }
        }

        ZeitSlot zeitSlot = new ZeitSlot(terminArray.size());


        Collections.sort(terminArray, new Comparator<Schnittstelle.TerminEintrag>() {
            @Override
            public int compare(Schnittstelle.TerminEintrag obj01, Schnittstelle.TerminEintrag obj02)
            {
                Integer height01 = getHeight2(obj01.height,100);
                Integer height02 = getHeight2(obj02.height,100);
                return height01.compareTo(height02);
            }
        });


        for (final Schnittstelle.TerminEintrag termin : terminArray) {

            if (termin.ganztagig) termin.height = getHeight(new Zeit(0, 0), new Zeit(23, 59));
            else if (termin.beginn.equals(termin.ende)) {
                termin.height = getHeight(termin.beginnZeit, termin.endeZeit);
                termin.margin = termin.beginnZeit;
            } else if (Schnittstelle.current.equals(termin.beginn)) {
                termin.height = getHeight(termin.beginnZeit, new Zeit(23, 59));
                termin.margin = termin.beginnZeit;
            } else if (Schnittstelle.current.equals(termin.ende))
                termin.height = getHeight(new Zeit(0, 0), termin.endeZeit);
            else termin.height = getHeight(new Zeit(0, 0), new Zeit(23, 59));
            termin.offset = zeitSlot.setTerminOffset(termin);

            int height = 0;
            int margin = 0;
            if (termin.ganztagig) height = getHeight2(termin.height, 128);
            else if (termin.beginn.equals(termin.ende)) {
                height = getHeight2(termin.height, 128);
                margin = getMarginTop(termin.beginnZeit, 128);
            } else if (Schnittstelle.current.equals(termin.beginn)) {
                height = getHeight2(termin.height, 128);
                margin = getMarginTop(termin.beginnZeit, 128);
            } else if (Schnittstelle.current.equals(termin.ende))
                height = getHeight2(termin.height, 128);
            else height = getHeight2(termin.height, 128);

            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(getPx(106), height);
            layoutParams.setMarginStart(getPx(80 + termin.offset * 110));

            layoutParams.setMargins(getPx(80 + termin.offset * 110), margin, 0, 0);
            TextView textView = new TextView(context);
            textView.setLayoutParams(layoutParams);
            textView.setText(termin.name);
            textView.setTextSize(20);
            textView.setPadding(32, 32, 32, 32);
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
            frameLayout.addView(textView);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new KalenderBearbeiten(Schnittstelle.terminListe.indexOf(termin))).commit();
                }
            });
            //Log.d("OFFSET", "Offset: " + termin.offset);
        }
        //Log.d("TERMINARRAY",terminArray.toString());
    }

    class ZeitSlot {
        List<boolean[]> rowList = new ArrayList<boolean[]>();
        ZeitSlot(int maxOffset) {
            for(int i = 0; i < maxOffset; i++) {
                rowList.add(new boolean[24]);
            }

        }

        int setTerminOffset(Schnittstelle.TerminEintrag termin) {
            int offset = 0;
            Log.d("TERMIN","termin: " + termin.name + " termin-height: " + termin.height + " termin-margin: " + termin.margin);
            for(boolean[] hours : rowList) {
                for(int i = termin.margin.stunden; i < (termin.height.stunden + termin.margin.stunden) && i < 24; i++) {
                    if(hours[i]) {
                        offset++;
                        break;
                    }
                }
                for(int i = termin.margin.stunden; i < (termin.height.stunden + termin.margin.stunden) && i < 24; i++) {
                    hours[i] = true;
                }
                return offset;
            }
            return -1;
        }
    }

    int getHeight2(Zeit heightTime, int height) {
        return (height * heightTime.stunden + heightTime.minuten * height/60);
    }

    Zeit getHeight(Zeit beginn, Zeit ende) {
        int stunden = 0;
        int minuten = 0;
        if(beginn.stunden > ende.stunden) stunden = beginn.stunden - ende.stunden;
        else stunden = ende.stunden - beginn.stunden;
        if(beginn.minuten > ende.minuten) minuten = beginn.minuten - ende.minuten;
        else minuten = ende.minuten - beginn.minuten;
        return new Zeit(stunden,minuten);
    }

    int getMarginTop(Zeit beginn, int height) {
        return (beginn.stunden * height) + (beginn.minuten * height / 60);
    }

    int getPx(int dp) {
        Resources r = getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                r.getDisplayMetrics()
        );
    }
}
