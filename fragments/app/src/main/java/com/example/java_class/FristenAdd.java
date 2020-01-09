package com.example.java_class;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import com.google.android.material.snackbar.Snackbar;
import java.util.Calendar;


public class FristenAdd extends Fragment {

    class TerminListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            month++;
            String date = dayOfMonth + "." + month + "." + year;
            terminButton.setText(date);
            termin = new Datum(dayOfMonth, month, year);
            terminBool = true;
            validator();

        }
    }
    class ErinnerungsDatumListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            month++;
            String date = dayOfMonth + "." + month + "." + year;
            erinnerungsDatumButton.setText(date);
            erinnerungsDatum = new Datum(dayOfMonth, month, year);
            erinnerungsDatumBool = true;
            validator();

        }
    }

    private TerminListener terminListener = new TerminListener();
    private ErinnerungsDatumListener erinnerungsDatumListener = new ErinnerungsDatumListener();

    private Button speichern, terminButton, erinnerungsDatumButton;
    private EditText titel, beschreibung;
    private boolean titelBool = false, terminBool = false, erinnerungsDatumBool = false;
    private Datum termin, erinnerungsDatum = null;

    private boolean validator() {
        if (titelBool && terminBool) {
            speichern.setAlpha(1f);
            return true;
        }
        speichern.setAlpha(.5f);
        return false;
    }

    private void showDatePickerDialog(boolean termin) {
        DatePickerDialog datePickerDialog;
        if (termin) {
            datePickerDialog = new DatePickerDialog(
                    getActivity(),
                    terminListener,
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            );
        } else {
            datePickerDialog = new DatePickerDialog(
                    getActivity(),
                    erinnerungsDatumListener,
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            );
        }
        datePickerDialog.show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fristen_add, container, false);

        speichern = (Button) v.findViewById(R.id.speichern);
        titel = (EditText) v.findViewById(R.id.titel);
        terminButton = (Button) v.findViewById(R.id.termin);
        erinnerungsDatumButton = (Button) v.findViewById(R.id.erinnerungs_datum);
        beschreibung = (EditText) v.findViewById(R.id.beschreibung);
        validator();

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
                    //titel.setBackgroundColor(getResources().getColor(R.color.green));
                } else {
                    titelBool = false;
                    //titel.setBackgroundColor(getResources().getColor(R.color.red));
                }
                validator();
            }
        });
        terminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(true);
            }
        });
        erinnerungsDatumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(false);
            }
        });
        //onklick listener löschen
        Button abbrechen = (Button) v.findViewById(R.id.abbrechen);
        abbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment, new Fristen());
                fr.commit();
            }
        });
        speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validator() && (erinnerungsDatum==null|| termin.compareTo(erinnerungsDatum) >= 0) ) {

                    Schnittstelle.FristenEintrag e = new Schnittstelle().new FristenEintrag(
                            titel.getText().toString(),
                            termin,
                            erinnerungsDatum,
                            beschreibung.getText().toString()
                    );
                    Schnittstelle.fristenListe.add(e);
                    //speichere änderungen
                    Schnittstelle.saveFristen();

                    //Benachrichtigung für Frist
                    if (erinnerungsDatumBool) {
                        ((MainActivity) getActivity()).CreateNotification(titel.getText().toString(), erinnerungsDatum);
                        MainActivity.notificationNummer++;
                    }

                    //seite neu laden
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.nav_host_fragment, new Fristen());
                    fr.commit();
                } else {
                    /*
                    int error = getResources().getColor(R.color.Error);
                    titel1.setBackgroundColor(error);
                    titel1.setHint("Darf nicht leer sein!");
                    autor1.setBackgroundColor(error);
                    autor1.setHint("Darf nicht leer sein!");
                     */
                    Snackbar error = Snackbar.make(v, "Bitte überprüfe deine Eingaben", 1024);
                    error.show();
                }
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment, new Fristen());
                fr.commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
        return v;
    }

}
