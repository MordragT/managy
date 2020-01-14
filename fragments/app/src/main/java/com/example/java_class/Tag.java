package com.example.java_class;

import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Tag extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tag, container, false);


        CustomCalendarWeekView customCalendarWeekView = (CustomCalendarWeekView)v.findViewById(R.id.custom_calendar_week_view);

        int takeOverDay = getArguments().getInt("takeOverDay");
        int takeOverMonth = getArguments().getInt("takeOverMonth");
        int takeOverYear = getArguments().getInt("takeOverYear");

        customCalendarWeekView.reformLayoutByDate(takeOverYear, takeOverMonth, takeOverDay);
        return v;
    }
}
