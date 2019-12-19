package com.example.java_class;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class CustomCalendarView extends LinearLayout {
    ImageButton NextButton, PreviousButton;
    TextView CurrentDate;
    GridView gridView;

    private static final int MAX_CALENDAR_DAYS = 42;

    Calendar calendar = Calendar.getInstance(Locale.GERMAN);

    Context context;
    List<Date> dates = new ArrayList<>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.GERMAN);
    SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.GERMAN);
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.GERMAN);
    SimpleDateFormat eventDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMAN);

    Kalender_Grid_Adapter kalenderGridAdapter;


    public CustomCalendarView (Context context) {
        super(context);
    }

    public CustomCalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomCalendarView(final Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
        this.context = context;



        InitializeLayout();
        setUpCalendar();

        PreviousButton.setOnClickListener(new OnClickListener(){
            public void onClick(View v){

                calendar.add(Calendar.MONTH, -1);

                setUpCalendar();
            }
        });
        NextButton.setOnClickListener(new OnClickListener(){
            public void onClick(View v){

                calendar.add(Calendar.MONTH, 1);

                setUpCalendar();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String date = eventDateFormat.format(dates.get(position));
                String month = monthFormat.format(dates.get(position));
                String year = yearFormat.format(dates.get(position));
                //String dateComplete = date;

                //CurrentDate.setText(dateComplete);
                //setUpCalendar();
            }
        });
    }

    public void InitializeLayout(){


        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_kalender_layout, this);
        NextButton = view.findViewById(R.id.nextBtn);
        PreviousButton = view.findViewById(R.id.previousBtn);
        CurrentDate = view.findViewById(R.id.current_Date);
        gridView = view.findViewById(R.id.gridview);
    }
    private void setUpCalendar(){

        String currentDate = dateFormat.format(calendar.getTime());
        CurrentDate.setText(currentDate);
        dates.clear();
        Calendar monthCalendar = (Calendar) calendar.clone();
        monthCalendar.set(Calendar.DAY_OF_MONTH,0);
        int FirstDayofMonth = monthCalendar.get(Calendar.DAY_OF_WEEK)-2;
        monthCalendar.add(Calendar.DAY_OF_MONTH, -FirstDayofMonth);

        while(dates.size() < MAX_CALENDAR_DAYS){
            dates.add(monthCalendar.getTime());
            monthCalendar.add(Calendar.DAY_OF_MONTH,1);
        }

        kalenderGridAdapter = new Kalender_Grid_Adapter(context, dates, calendar);
        gridView.setAdapter(kalenderGridAdapter);
    }



}