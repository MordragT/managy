package com.example.java_class;


import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
//import androidx.fragment.app.Fragment;


import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class CustomCalendarView extends LinearLayout {
    ImageButton nextButton, previousButton;
    TextView currentDate;
    GridView gridView;

    private static final int maxCalendarDays = 42;

    Calendar calendar = Calendar.getInstance(Locale.GERMAN);

    Context context;
    List<Date> dates = new ArrayList<>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.GERMAN);

    SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.GERMAN);
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.GERMAN);
    SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.GERMAN);

    KalenderGridAdapter kalenderGridAdapter;


    public CustomCalendarView(Context context) {
        super(context);
    }

    public CustomCalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomCalendarView(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        InitializeLayout();
        setUpCalendar();

        previousButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                calendar.add(Calendar.MONTH, -1);

                setUpCalendar();
            }
        });
        nextButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                calendar.add(Calendar.MONTH, 1);

                setUpCalendar();
            }
        });
        //OnClick für den Tag
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String day = dayFormat.format(dates.get(position));
                String month = monthFormat.format(dates.get(position));
                String year = yearFormat.format(dates.get(position));

                Schnittstelle.current = new Datum(Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year));
                Calendar cal = Calendar.getInstance();
                cal.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));
                Date tmpDate = cal.getTime();
                String tmp = new SimpleDateFormat("EEEE", Locale.GERMAN).format(tmpDate);
                Schnittstelle.currentDay = Schnittstelle.dayToInt(tmp);
                Schnittstelle.lastDay = Schnittstelle.currentDay;
                AppCompatActivity activity = (AppCompatActivity) context;
                ((MainActivity)activity).tabLayout.getTabAt(Schnittstelle.currentDay).select();
                FragmentTransaction fr = activity.getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment, new KalenderWeekDay(Schnittstelle.currentDay));
                fr.commit();
                /*
                int takeOverDay = Integer.parseInt(day);
                int takeOverMonth = Integer.parseInt(month)-1;
                int takeOverYear = Integer.parseInt(year);

                Bundle bundle = new Bundle();
                bundle.putInt("takeOverDay", takeOverDay);
                bundle.putInt("takeOverMonth", takeOverMonth);
                bundle.putInt("takeOverYear", takeOverYear);

                AppCompatActivity activity = (AppCompatActivity) context;
                FragmentTransaction fr = activity.getSupportFragmentManager().beginTransaction();
                KalenderWeekDay kalenderWeekDay = new KalenderWeekDay();
                kalenderWeekDay.setArguments(bundle);
                fr.replace(R.id.nav_host_fragment, kalenderWeekDay);
                fr.commit();
                 */
            }
        });

    }

    public void InitializeLayout() {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_kalender_layout, this);
        nextButton = view.findViewById(R.id.nextBtn);
        previousButton = view.findViewById(R.id.previousBtn);
        currentDate = view.findViewById(R.id.current_Date);
        gridView = view.findViewById(R.id.gridview);
    }

    private void setUpCalendar() {

        String currentDateString = dateFormat.format(calendar.getTime());
        currentDate.setText(currentDateString);
        dates.clear();
        Calendar monthCalendar = (Calendar) calendar.clone();
        monthCalendar.set(Calendar.DAY_OF_MONTH, 0);
        int FirstDayofMonth = monthCalendar.get(Calendar.DAY_OF_WEEK) - 2;
        monthCalendar.add(Calendar.DAY_OF_MONTH, -FirstDayofMonth);

        while (dates.size() < maxCalendarDays) {
            dates.add(monthCalendar.getTime());
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        kalenderGridAdapter = new KalenderGridAdapter(context, dates, calendar);
        gridView.setAdapter(kalenderGridAdapter);
    }
}