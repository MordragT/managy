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
        int width = displayMetrics.widthPixels;
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
        int height = 128;

        Log.d("HEIGHT", "height: " + height);

        for (Schnittstelle.TerminEintrag termin : Schnittstelle.terminListe) {
            if (termin.beginn.compareTo(Schnittstelle.current) <= 0 && termin.ende.compareTo(Schnittstelle.current) >= 0) {
                terminArray.add(termin);
            }
        }
        for (Schnittstelle.TerminEintrag termin : terminArray) {
            for (Schnittstelle.TerminEintrag vergleich : terminArray) {
                if (
                        termin != vergleich && termin.offset == vergleich.offset
                                && ((termin.beginnZeit.compareTo(vergleich.beginnZeit) <= 0 && termin.endeZeit.compareTo(vergleich.beginnZeit) >= 0)
                                || (termin.beginnZeit.compareTo(vergleich.endeZeit) <= 0 && termin.endeZeit.compareTo(vergleich.endeZeit) >= 0))
                ) {
                    termin.offset++;
                }
            }
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(getPx(106), getHeight(termin.beginnZeit, termin.endeZeit, height));
            layoutParams.setMarginStart(getPx(80 + termin.offset * 106));
            layoutParams.setMargins(getPx(80 + termin.offset * 106), getMarginTop(termin.beginnZeit, height), 0, 0);
            TextView textView = new TextView(context);
            textView.setLayoutParams(layoutParams);
            textView.setText(termin.name);
            textView.setTextSize(24);
            switch (termin.farbe) {
                case "rot":
                    textView.setBackgroundColor(getContext().getResources().getColor(R.color.red));
                    textView.setTextColor(getResources().getColor(R.color.white));
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((MainActivity) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new KalenderBearbeiten()).commit();
                        }
                    });
                    break;
                case "gelb":
                    textView.setBackgroundColor(getContext().getResources().getColor(R.color.yellow));
                    textView.setTextColor(getResources().getColor(R.color.black));
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((MainActivity) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new KalenderBearbeiten()).commit();
                        }
                    });
                    break;
                case "blau":
                    textView.setBackgroundColor(getContext().getResources().getColor(R.color.blue));
                    textView.setTextColor(getResources().getColor(R.color.white));
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((MainActivity) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new KalenderBearbeiten()).commit();
                        }
                    });
                    break;
                case "grün":
                    textView.setBackgroundColor(getContext().getResources().getColor(R.color.green));
                    textView.setTextColor(getResources().getColor(R.color.black));
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((MainActivity) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new KalenderBearbeiten()).commit();
                        }
                    });
                    break;
            }
            frameLayout.addView(textView);
            //Log.d("OFFSET", "Offset: " + termin.offset);
        }
        //Log.d("TERMINARRAY",terminArray.toString());
    }


    public int getHeight(Zeit beginn, Zeit ende, int height) {
        return (height * (ende.stunden - beginn.stunden)) + ((ende.minuten - beginn.minuten) * height / 60);
    }

    public int getMarginTop(Zeit beginn, int height) {
        return (beginn.stunden * height) + (beginn.minuten * height / 60);
    }

    public int getPx(int dp) {
        Resources r = getResources();
        return  (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                r.getDisplayMetrics()
        );
    }
}
