package com.example.java_class;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Calendar;

public class KalenderAdd extends Fragment {

    class BeginnListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String date = dayOfMonth + "." + month + "." + year;
            beginnButton.setText(date);
            beginn = new Datum(dayOfMonth,month,year);
        }
    }

    class EndeListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String date = dayOfMonth + "." + month + "." + year;
            endeButton.setText(date);
            ende = new Datum(dayOfMonth,month,year);
        }
    }

    BeginnListener beginnListener = new BeginnListener();
    EndeListener endeListener = new EndeListener();

    View v;
    Button abbrechen;
    Button speichern;
    EditText titel;
    EditText beschreibung;
    RadioGroup colorGroup;
    int checkedColor;
    RadioButton colorButton;
    Button beginnButton;
    Button endeButton;
    Datum beginn;
    Datum ende;

    private void showDatePickerDialog(boolean beginn) {
        DatePickerDialog datePickerDialog;
        if(beginn) {
            datePickerDialog = new DatePickerDialog(
                    getActivity(),
                    beginnListener,
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            );
        } else {
            datePickerDialog = new DatePickerDialog(
                    getActivity(),
                    endeListener,
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            );
        }
        datePickerDialog.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Onklick listener
        v = inflater.inflate(R.layout.fragment_kalender_add, container, false);
        abbrechen = (Button) v.findViewById(R.id.abbrechen);
        speichern = (Button) v.findViewById(R.id.speichern);
        titel = (EditText) v.findViewById(R.id.titel);
        beschreibung = (EditText) v.findViewById(R.id.beschreibung);
        colorGroup = (RadioGroup) v.findViewById(R.id.color);
        colorGroup.getCheckedRadioButtonId();
        colorButton = (RadioButton) v.findViewById(checkedColor);
        beginnButton = (Button) v.findViewById(R.id.beginn);
        endeButton = (Button) v.findViewById(R.id.ende);

        beginnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(true);
            }
        });
        endeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(false);
            }
        });
        abbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToDo.to_to_onclick_titel(v , position);
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment, new Kalender());
                fr.commit();
            }
        });
        speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //eingestelte werte auslesen
                String sTitel = titel.getText().toString();
                String sFarbe = colorButton.getText().toString();
                String sBeschreibung = beschreibung.getText().toString();

                if (sTitel.length() > 0) {
                    //neuen eintrag schreiben
                    Schnittstelle.TerminEintrag t = new Schnittstelle().new TerminEintrag();
                    t.name = sTitel;
                    t.farbe = sFarbe;
                    t.beginn = beginn;
                    t.ende = ende;
                    t.beschreibung = sBeschreibung;
                    Schnittstelle.terminListe.add(t);
                    //speichere änderungen
                    Schnittstelle.saveTermine();

                    //seite neu laden
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.nav_host_fragment, new Kalender());
                    fr.commit();
                } else {
                    int error = getResources().getColor(R.color.Error);
                    titel.setBackgroundColor(error);
                    titel.setHint("darf nicht leer sein");
                }
            }
        });

        return v;
    }
}
