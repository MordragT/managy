package com.example.java_class;

import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.AttributeSet;
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
import java.util.Calendar;
import java.util.Locale;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class CustomCalendarWeekView extends LinearLayout {
    ImageButton previousButton;
    TextView currentDate;
    GridView gridView;

    private static final int maxCalendarDays = 7;

    Calendar calendar = Calendar.getInstance(Locale.GERMAN);

    Context context;
    List<Date> dates = new ArrayList<>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.GERMAN);

    SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.GERMAN);
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.GERMAN);
    SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.GERMAN);

    KalenderGridAdapter kalenderGridAdapter;


    public CustomCalendarWeekView(Context context) {
        super(context);
    }

    public CustomCalendarWeekView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomCalendarWeekView(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        InitializeLayout();
        setUpCalendar();

        previousButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) context;
                FragmentTransaction fr = activity.getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment, new Kalender());
                fr.commit();

            }
        });

        //OnClick für den Tag
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String day = dayFormat.format(dates.get(position));
                String month = monthFormat.format(dates.get(position));
                String year = yearFormat.format(dates.get(position));
                int takeOverDay = Integer.parseInt(day);
                int takeOverMonth = Integer.parseInt(month)-1;
                int takeOverYear = Integer.parseInt(year);

                Bundle bundle = new Bundle();
                bundle.putInt("takeOverDay", takeOverDay);
                bundle.putInt("takeOverMonth", takeOverMonth);
                bundle.putInt("takeOverYear", takeOverYear);


                AppCompatActivity activity = (AppCompatActivity) context;
                FragmentTransaction fr = activity.getSupportFragmentManager().beginTransaction();
                KalenderAdd kalenderAdd = new KalenderAdd();
                kalenderAdd.setArguments(bundle);
                fr.replace(R.id.nav_host_fragment, kalenderAdd);
                fr.commit();


            }
        });

    }
    public void reformLayoutByDate(int takeOverYear, int takeOverMonth, int takeOverDay) {
        calendar.set(takeOverYear, takeOverMonth, takeOverDay);

        InitializeLayout();
        setUpCalendar();

        previousButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) context;
                FragmentTransaction fr = activity.getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment, new Kalender());
                fr.commit();

            }
        });

        //OnClick für den Tag
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String day = dayFormat.format(dates.get(position));
                String month = monthFormat.format(dates.get(position));
                String year = yearFormat.format(dates.get(position));
                int takeOverDay = Integer.parseInt(day);
                int takeOverMonth = Integer.parseInt(month)-1;
                int takeOverYear = Integer.parseInt(year);

                Bundle bundle = new Bundle();
                bundle.putInt("takeOverDay", takeOverDay);
                bundle.putInt("takeOverMonth", takeOverMonth);
                bundle.putInt("takeOverYear", takeOverYear);


                AppCompatActivity activity = (AppCompatActivity) context;
                FragmentTransaction fr = activity.getSupportFragmentManager().beginTransaction();
                //KalenderAdd kalenderAdd = new KalenderAdd();
                //kalenderAdd.setArguments(bundle);
                //fr.replace(R.id.nav_host_fragment, kalenderAdd);
                Tag tag = new Tag();
                tag.setArguments(bundle);
                fr.replace(R.id.nav_host_fragment, tag);
                fr.commit();


            }
        });
    }

    public void InitializeLayout() {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_kalender_week_layout, this);

        previousButton = view.findViewById(R.id.returnToMonthBtn);
        currentDate = view.findViewById(R.id.current_Date_Week);
        gridView = view.findViewById(R.id.gridview_week);
    }

    private void setUpCalendar() {

        String currentDateString = dateFormat.format(calendar.getTime());
        currentDate.setText(currentDateString);
        dates.clear();
        Calendar weekCalendar = (Calendar) calendar.clone();
        weekCalendar.set(Calendar.DAY_OF_WEEK,0);
        int FirstDayofWeek = weekCalendar.get(Calendar.DAY_OF_WEEK)-2;
        weekCalendar.add(Calendar.DAY_OF_WEEK, -FirstDayofWeek);

        while (dates.size() < maxCalendarDays) {
            dates.add(weekCalendar.getTime());
            weekCalendar.add(Calendar.DAY_OF_WEEK, 1);
        }

        kalenderGridAdapter = new KalenderGridAdapter(context, dates, calendar);
        gridView.setAdapter(kalenderGridAdapter);
    }
}
