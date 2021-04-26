package com.example.java_class;

import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Locale;
import java.util.Random;
import java.lang.String;
import android.widget.TextView;
import java.util.Calendar;

public class Startseite extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                ((MainActivity) getActivity()).bottomNav.setSelectedItemId(((MainActivity) getActivity()).lastItem);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        View v = inflater.inflate(R.layout.fragment_startseite, container, false);

        Schnittstelle.load();

        FristStartseite(v);
        TerminStartseite(v);
        ToDoStartseite(v);
        LiteraturStartseite(v);
        TippsStartseite(v);

        gotoFristen(v);
        gotoTermine(v);
        gotoToDo(v);
        gotoLiteratur(v);

        ((MainActivity) getActivity()).setActionBarTitle("Managy");

        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_startseite, container, false);
        return v;
    }

    private void FristStartseite(View v) {
        TextView FristStart = v.findViewById(R.id.FristStart);
        if (Schnittstelle.fristenListe.size() == 0) {
            String Frist = "Keine Fristen";
            FristStart.setText(Frist);
        } else {
            Calendar tmpCalendar = Calendar.getInstance(Locale.GERMAN);

            int currentJahr = tmpCalendar.get(Calendar.YEAR);
            int currentMonat = tmpCalendar.get(Calendar.MONTH) + 1;
            int currentTag = tmpCalendar.get(Calendar.DAY_OF_MONTH);

            int i = 0;
            boolean current = false;

            for (; i < Schnittstelle.fristenListe.size() && !current;) {
                if (Schnittstelle.fristenListe.get(i).termin.jahr > currentJahr) {
                    current = true;
                } else if (Schnittstelle.fristenListe.get(i).termin.jahr == currentJahr
                        && Schnittstelle.fristenListe.get(i).termin.monat > currentMonat) {
                    current = true;
                } else if (Schnittstelle.fristenListe.get(i).termin.jahr == currentJahr
                        && Schnittstelle.fristenListe.get(i).termin.monat == currentMonat
                        && Schnittstelle.fristenListe.get(i).termin.tag >= currentTag) {
                    current = true;
                } else {
                    i++;
                }
            }
            if (i == Schnittstelle.fristenListe.size()) {
                String Frist = "Keine anstehenden Fristen";
                FristStart.setText(Frist);
            } else {
                String Frist = Schnittstelle.fristenListe.get(i).name + "\n\n"
                        + Schnittstelle.fristenListe.get(i).termin;
                FristStart.setText(Frist);
            }

        }
    }

    private void TerminStartseite(View v) {
        TextView TerminStart = v.findViewById(R.id.TerminStart);
        if (Schnittstelle.terminListe.size() == 0) {
            String Termin = "Keine Termine";
            TerminStart.setText(Termin);
        } else {
            Calendar tmpCalendar = Calendar.getInstance(Locale.GERMAN);
            int currentJahr = tmpCalendar.get(Calendar.YEAR);
            int currentMonat = tmpCalendar.get(Calendar.MONTH) + 1;
            int currentTag = tmpCalendar.get(Calendar.DAY_OF_MONTH);
            int currentStunde = tmpCalendar.get(Calendar.HOUR_OF_DAY);
            int currentMinute = tmpCalendar.get(Calendar.MINUTE);

            int i = 0;
            boolean current = false;
            for (; i < Schnittstelle.terminListe.size() && !current;) {
                if (Schnittstelle.terminListe.get(i).beginn.jahr > currentJahr) {
                    current = true;
                } else if (Schnittstelle.terminListe.get(i).beginn.jahr == currentJahr
                        && Schnittstelle.terminListe.get(i).beginn.monat > currentMonat) {
                    current = true;
                } else if (Schnittstelle.terminListe.get(i).beginn.jahr == currentJahr
                        && Schnittstelle.terminListe.get(i).beginn.monat == currentMonat
                        && Schnittstelle.terminListe.get(i).beginn.tag > currentTag) {
                    current = true;
                } else if (Schnittstelle.terminListe.get(i).beginn.jahr == currentJahr
                        && Schnittstelle.terminListe.get(i).beginn.monat == currentMonat
                        && Schnittstelle.terminListe.get(i).beginn.tag == currentTag
                        && Schnittstelle.terminListe.get(i).beginnZeit.stunden > currentStunde) {
                    current = true;
                } else if (Schnittstelle.terminListe.get(i).beginn.jahr == currentJahr
                        && Schnittstelle.terminListe.get(i).beginn.monat == currentMonat
                        && Schnittstelle.terminListe.get(i).beginn.tag == currentTag
                        && Schnittstelle.terminListe.get(i).beginnZeit.stunden == currentStunde
                        && Schnittstelle.terminListe.get(i).beginnZeit.minuten > currentMinute) {
                    current = true;
                } else {
                    i++;
                }
            }
            if (i == Schnittstelle.terminListe.size()) {
                String Termin = "Keine anstehenden Termine";
                TerminStart.setText(Termin);
            } else {
                String Termin = Schnittstelle.terminListe.get(i).name + "\n\n"
                        + Schnittstelle.terminListe.get(i).beginn;
                TerminStart.setText(Termin);
            }
        }
    }

    private void ToDoStartseite(View v) {
        TextView ToDoStart = v.findViewById(R.id.ToDoStart);
        if (Schnittstelle.toDoListe.size() == 0) {
            String ToDo = "0/0";
            ToDoStart.setText(ToDo);
        } else {
            int counter = 0;
            for (int i = 0; i < Schnittstelle.toDoListe.size(); i++) {
                if (Schnittstelle.toDoListe.get(i).erledigt) {
                    counter++;
                }
            }
            String ToDo = counter + "/" + Schnittstelle.toDoListe.size();
            ToDoStart.setText(ToDo);
        }
    }

    private void LiteraturStartseite(View v) {
        TextView LiteraturStart = v.findViewById(R.id.LiteraturStart);
        if (Schnittstelle.literaturListe.size() == 0) {
            String Literatur = "0/0";
            LiteraturStart.setText(Literatur);
        } else {
            int counter = 0;
            for (int i = 0; i < Schnittstelle.literaturListe.size(); i++) {
                if (Schnittstelle.literaturListe.get(i).gelesen) {
                    counter++;
                }
            }
            String Literatur = counter + "/" + Schnittstelle.literaturListe.size();
            LiteraturStart.setText(Literatur);
        }
    }

    private void TippsStartseite(View v) {
        TextView TippsStartseite = v.findViewById(R.id.TippsStart);

        int randomIndex = new Random().nextInt((getResources().getStringArray(R.array.TippsArray)).length);
        String randomTipp = (getResources().getStringArray(R.array.TippsArray))[randomIndex];
        TippsStartseite.setText(randomTipp);
    }

    public void gotoFristen(View v) {
        TextView FristStart = v.findViewById(R.id.FristStart);
        FristStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).bottomNav.setSelectedItemId(R.id.navigation_abgabe);
            }
        });
    }

    public void gotoTermine(View v) {
        TextView TerminStart = v.findViewById(R.id.TerminStart);
        TerminStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).bottomNav.setSelectedItemId(R.id.navigation_kalender);
            }
        });
    }

    public void gotoToDo(View v) {
        TextView ToDoStart = v.findViewById(R.id.ToDoStart);
        ToDoStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).bottomNav.setSelectedItemId(R.id.navigation_to_do);
            }
        });
    }

    public void gotoLiteratur(View v) {
        TextView LiteraturStart = v.findViewById(R.id.LiteraturStart);
        LiteraturStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).bottomNav.setSelectedItemId(R.id.navigation_literatur);
            }
        });
    }
}
