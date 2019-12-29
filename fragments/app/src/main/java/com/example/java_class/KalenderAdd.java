package com.example.java_class;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TimePicker;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.snackbar.Snackbar;
import java.util.Calendar;

public class KalenderAdd extends Fragment {

    class BeginnListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            month++;
            String date = dayOfMonth + "." + month + "." + year;
            beginnButton.setText(date);
            beginnButton.setBackgroundColor(getResources().getColor(R.color.green));
            beginn = new Datum(dayOfMonth, month, year);
            beginnBool = true;
            validator();

        }
    }

    class EndeListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            month++;
            String date = dayOfMonth + "." + month + "." + year;
            endeButton.setText(date);
            endeButton.setBackgroundColor(getResources().getColor(R.color.green));
            ende = new Datum(dayOfMonth, month, year);
            endeBool = true;
            validator();
        }
    }

    class BeginnHourListener implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String time = hourOfDay + ":" + minute;
            beginnHourButton.setText(time);
            beginnHourButton.setBackgroundColor(getResources().getColor(R.color.green));
            beginnHour = new Zeit(hourOfDay, minute);
            beginnHourBool = true;
            validator();
        }
    }

    class EndeHourListener implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String time = hourOfDay + ":" + minute;
            endeHourButton.setText(time);
            endeHourButton.setBackgroundColor(getResources().getColor(R.color.green));
            endeHour = new Zeit(hourOfDay, minute);
            endeHourBool = true;
            validator();
        }
    }

    private BeginnListener beginnListener = new BeginnListener();
    private EndeListener endeListener = new EndeListener();
    private BeginnHourListener beginnHourListener = new BeginnHourListener();
    private EndeHourListener endeHourListener = new EndeHourListener();

    private boolean titelBool = false,
            colorBool = false,
            beginnBool = false,
            endeBool = false,
            beginnHourBool = false,
            endeHourBool = false,
            ganztagigBool = false;

    View v;
    private Button abbrechen, speichern, beginnButton, endeButton, beginnHourButton, endeHourButton;
    private EditText titel, beschreibung;
    private RadioGroup colorGroup;
    private RadioButton colorButton;
    private Switch ganztagigSwitch;

    private Datum beginn, ende;
    private Zeit beginnHour, endeHour;

    private void showDatePickerDialog(boolean beginn) {
        DatePickerDialog datePickerDialog;
        if (beginn) {
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

    private void showTimePickerDialog(boolean beginn) {
        TimePickerDialog timePickerDialog;
        if (beginn) {
            timePickerDialog = new TimePickerDialog(
                    getActivity(),
                    beginnHourListener,
                    Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                    Calendar.getInstance().get(Calendar.MINUTE),
                    true
            );
        } else {
            timePickerDialog = new TimePickerDialog(
                    getActivity(),
                    endeHourListener,
                    Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                    Calendar.getInstance().get(Calendar.MINUTE),
                    true
            );
        }
        timePickerDialog.show();
    }

    private boolean validator() {
        if (titelBool && colorBool && beginnBool && endeBool && ((beginnHourBool && endeHourBool) || ganztagigBool)) {
            speichern.setClickable(true);
            speichern.setAlpha(1f);
            return true;
        }
        speichern.setClickable(false);
        speichern.setAlpha(.5f);
        return false;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_kalender_add, container, false);
        abbrechen = (Button) v.findViewById(R.id.abbrechen);
        speichern = (Button) v.findViewById(R.id.speichern);
        titel = (EditText) v.findViewById(R.id.titel);
        beschreibung = (EditText) v.findViewById(R.id.beschreibung);
        colorGroup = (RadioGroup) v.findViewById(R.id.color);
        beginnButton = (Button) v.findViewById(R.id.beginn);
        endeButton = (Button) v.findViewById(R.id.ende);
        beginnHourButton = (Button) v.findViewById(R.id.beginnHour);
        endeHourButton = (Button) v.findViewById(R.id.endeHour);
        ganztagigSwitch = (Switch) v.findViewById(R.id.ganztagig);

        speichern.setClickable(false);
        speichern.setAlpha(.5f);

        titel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!titel.getText().toString().isEmpty()) {
                    titelBool = true;
                    titel.setBackgroundColor(getResources().getColor(R.color.green));
                    validator();
                } else {
                    titelBool = false;
                    titel.setBackgroundColor(getResources().getColor(R.color.red));
                }
            }
        });
        colorGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                colorButton = (RadioButton) v.findViewById(checkedId);
                colorBool = true;
                colorGroup.setBackgroundColor(getResources().getColor(R.color.green));
                validator();
            }
        });
        ganztagigSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ganztagigBool = isChecked;
                if (isChecked) {
                    beginnHourButton.setVisibility(View.GONE);
                    endeHourButton.setVisibility(View.GONE);
                } else {
                    beginnHourButton.setVisibility(View.VISIBLE);
                    endeHourButton.setVisibility(View.VISIBLE);
                }
                validator();
            }
        });
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
        beginnHourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(true);
            }
        });
        endeHourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(false);
            }
        });


        abbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment, new Kalender());
                fr.commit();
            }
        });
        speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.e("SPEICHERN",Boolean.toString(beginn.compareTo(ende) < 0));
                if ((beginn.compareTo(ende) < 0) && (ganztagigBool || !ganztagigBool && beginnHour.compareTo(endeHour) < 0) && validator()) {
                    Schnittstelle.TerminEintrag t;
                    if (ganztagigBool) {
                        beginnHour = new Zeit(0, 0);
                        endeHour = new Zeit(23, 59);
                    }
                    if (!beschreibung.getText().toString().isEmpty()) {
                        t = new Schnittstelle().new TerminEintrag(
                                titel.getText().toString(),
                                colorButton.getText().toString(),
                                beginn,
                                ende,
                                beginnHour,
                                endeHour,
                                beschreibung.getText().toString()
                        );
                    } else {
                        t = new Schnittstelle().new TerminEintrag(
                                titel.getText().toString(),
                                colorButton.getText().toString(),
                                beginn,
                                ende,
                                beginnHour,
                                endeHour
                        );
                    }

                    Schnittstelle.terminListe.add(t);
                    Schnittstelle.saveTermine();
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.nav_host_fragment, new Kalender());
                    fr.commit();
                } else {
                    Snackbar error = Snackbar.make(v, "Bitte überprüfe deine Eingaben", 1024);
                    error.show();
                }
            }
        });
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment, new Kalender());
                fr.commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
        return v;
    }
}
