package com.example.java_class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class KalenderGridAdapter extends ArrayAdapter {
    List<Date> dates;
    Calendar currentDate;
    LayoutInflater inflater;

    @Override
    public int getCount() {
        return dates.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Date monthDate = dates.get(position);
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(monthDate);

        int DayNo = dateCalendar.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCalendar.get(Calendar.MONTH)+1;
        int displayYear = dateCalendar.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH) +1;
        int currentYear = currentDate.get(Calendar.YEAR);

        String dayNumberFill;
        String monthNumberFill;
        String yearNumberFill;


        if(DayNo < 10){
            dayNumberFill = "0"+DayNo;
        }
        else{
            dayNumberFill = ""+DayNo;
        }
        if(displayMonth < 10){
            monthNumberFill = "0"+displayMonth;
        }
        else{
            monthNumberFill = ""+displayMonth;
        }
        if(displayYear < 10){
            yearNumberFill = "000"+displayYear;
        }
        else if(displayYear < 100){
            yearNumberFill = "00"+displayYear;
        }
        else if(displayYear < 1000){
            yearNumberFill = "0"+displayYear;
        }
        else{
            yearNumberFill = ""+displayYear;
        }


        //SimpleDateFormat fullDate = new SimpleDateFormat("yyyy-MM-dd");
        Date fullDate;
        String notConvertedDate = yearNumberFill+"-"+monthNumberFill+"-"+dayNumberFill;
        fullDate = ConvertStringToDate(notConvertedDate);

        View view = convertView;
        if(view == null){
            view = inflater.inflate(R.layout.fragment_single_cell_layout,parent, false);
        }



        TextView Day_Number = view.findViewById(R.id.calendar_day);

        TextView EventOne = view.findViewById(R.id.events_id);

        if(displayMonth != currentMonth || displayYear != currentYear){
            Day_Number.setTextColor(getContext().getResources().getColor(R.color.Gray));
        }

        schnitstelle loadTermine = new schnitstelle();
        loadTermine.load();
        schnitstelle.termine_eintrag e1 = new schnitstelle().new termine_eintrag();
        e1.name = "Test1";
        e1.von = new schnitstelle().new my_date(20, 12, 2019);
        e1.bis = new schnitstelle().new my_date(21, 12, 2019);
        schnitstelle.termine_eintrag e2 = new schnitstelle().new termine_eintrag();
        e2.name = "Test2";
        e2.von = new schnitstelle().new my_date(12, 12, 2019);
        e2.bis = new schnitstelle().new my_date(12, 12, 2019);
        loadTermine.termine_liste.add(e1);
        loadTermine.termine_liste.add(e2);
        int c = 0;
        for(int i = 0; i<loadTermine.termine_liste.size() && c < 1; i++){
            if(loadTermine.termine_liste.get(i).von.jahr <= displayYear
                && loadTermine.termine_liste.get(i).von.monat <= displayMonth
                && loadTermine.termine_liste.get(i).von.tag <= DayNo
                && loadTermine.termine_liste.get(i).bis.jahr >= displayYear
                && loadTermine.termine_liste.get(i).bis.monat >= displayMonth
                && loadTermine.termine_liste.get(i).bis.tag >= DayNo){
                EventOne.setText(loadTermine.termine_liste.get(i).name);
            }
        }


        Day_Number.setText(String.valueOf(DayNo));
        //EventOne.setText(notConvertedDate);

        //EventOne.setText(String.valueOf(monthDate));
        //Day_Number.setText(notConvertedDate);



        return view;
    }

    private Date ConvertStringToDate(String eventDate){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMAN);
        Date date = null;
        try{
            date = format.parse(eventDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @Override
    public int getPosition(@Nullable Object item) {
        return dates.indexOf(item);
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return dates.get(position);
    }


    public KalenderGridAdapter(@NonNull Context context, List<Date> dates, Calendar currentDate) {
        super(context, R.layout.fragment_single_cell_layout);

        this.dates=dates;
        this.currentDate = currentDate;
        inflater = LayoutInflater.from(context);
    }
}