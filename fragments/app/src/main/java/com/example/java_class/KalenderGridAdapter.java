package com.example.java_class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class KalenderGridAdapter extends ArrayAdapter {
    private List<Date> dates;
    private Calendar currentDate;
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

        int displayDay = dateCalendar.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCalendar.get(Calendar.MONTH) + 1;
        int displayYear = dateCalendar.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH) + 1;
        int currentYear = currentDate.get(Calendar.YEAR);

        /*
        String dayNumberFill;
        String monthNumberFill;
        String yearNumberFill;


        if (displayDay < 10) dayNumberFill = "0" + displayDay;
        else dayNumberFill = "" + displayDay;

        if (displayMonth < 10) monthNumberFill = "0" + displayMonth;
        else monthNumberFill = "" + displayMonth;

        if (displayYear < 10) yearNumberFill = "000" + displayYear;
        else if (displayYear < 100) yearNumberFill = "00" + displayYear;
        else if (displayYear < 1000) yearNumberFill = "0" + displayYear;
        else yearNumberFill = "" + displayYear;

        SimpleDateFormat fullDate = new SimpleDateFormat("yyyy-MM-dd");
        Date fullDate;
        String notConvertedDate = yearNumberFill + "-" + monthNumberFill + "-" + dayNumberFill;
        fullDate = convertStringToDate(notConvertedDate);
         */

        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_single_cell_layout, parent, false);
        }

        TextView dayCell = view.findViewById(R.id.calendar_day);
        TextView eventOne = view.findViewById(R.id.events_id);

        if (displayMonth != currentMonth || displayYear != currentYear) {
            dayCell.setTextColor(getContext().getResources().getColor(R.color.Gray));
        }

        for (Schnittstelle.TerminEintrag termin : Schnittstelle.terminListe) {
            if (
                    termin.beginn.jahr <= displayYear
                            && termin.beginn.monat <= displayMonth
                            && termin.beginn.tag <= displayDay
                            && termin.ende.jahr >= displayYear
                            && termin.ende.monat >= displayMonth
                            && termin.ende.tag >= displayDay
            ) {
                switch (termin.farbe) {
                    case "rot":
                        eventOne.setBackgroundColor(getContext().getResources().getColor(R.color.red));
                        break;
                    case "gelb":
                        eventOne.setBackgroundColor(getContext().getResources().getColor(R.color.yellow));
                        break;
                    case "blau":
                        eventOne.setBackgroundColor(getContext().getResources().getColor(R.color.blue));
                        break;
                    case "grün":
                        eventOne.setBackgroundColor(getContext().getResources().getColor(R.color.green));
                        break;
                }
                eventOne.setText(termin.name);
            }
        }

        dayCell.setText(String.valueOf(displayDay));

        //eventOne.setText(notConvertedDate);
        //eventOne.setText(String.valueOf(monthDate));
        //dayCell.setText(notConvertedDate);
        return view;
    }
    /*
    private Date convertStringToDate(String eventDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMAN);
        Date date = null;
        try {
            date = format.parse(eventDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
     */

    @Override
    public int getPosition(@Nullable Object item) {
        return dates.indexOf(item);
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return dates.get(position);
    }


    KalenderGridAdapter(@NonNull Context context, List<Date> dates, Calendar currentDate) {
        super(context, R.layout.fragment_single_cell_layout);

        this.dates = dates;
        this.currentDate = currentDate;
        inflater = LayoutInflater.from(context);
    }
}